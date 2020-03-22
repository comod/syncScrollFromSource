package listener;

import application.Constants;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.testFramework.LightVirtualFile;
import config.ApplicationConfig;
import config.MySearchReplacePair;
import helper.Editor;
import org.jetbrains.annotations.NotNull;
import ui.MyStatusBar;

import java.util.List;

public class ChangeActionNotifier implements ChangeActionNotifierInterface {

    private final Editor editor;
    private final List<MySearchReplacePair> myPairs;
    private final MyStatusBar myStatusBar;
    private final Project project;

    public ChangeActionNotifier(Project project) {

        this.project = project;
        this.editor = new Editor(project);
        ApplicationConfig config = ApplicationConfig.getInstance(project);
        this.myPairs = config.getMyPairs();
        this.myStatusBar = (MyStatusBar) WindowManager.getInstance().getStatusBar(project).getWidget(Constants.APPLICATION_NAME);

    }

    @Override
    public void doAction(Project callerProject, VirtualFile virtualFile) {

        String projectRootPath = Editor.determineRootPath(callerProject);
        String path = determineFilePath(virtualFile, projectRootPath);
        VirtualFile targetFile = this.editor.getVirtualFileByPath(path);

        // Jump to file if exists
        if (targetFile != null && targetFile.exists()) {
            editor.jumpToLine(targetFile, 0);
            return;
        }

        // Enable create actions
        myStatusBar.setPath(path);

        String newFile = Editor.determineRootPath(this.project) + "/" + path;
        String newPath = newFile.replace("/" + virtualFile.getName(), "");
        myStatusBar.setCreateFileAction(context -> {

            VirtualFile newVirtualFile = new LightVirtualFile(virtualFile.getName());

            Editor.copyFile(
                    newVirtualFile,
                    newPath
            );

        });

        myStatusBar.setCreateFileWithContentAction(context -> {
            Editor.copyFile(
                    virtualFile,
                    newPath
            );
        });

    }

    @NotNull
    private String determineFilePath(VirtualFile virtualFile, String projectRootPath) {

        String path = Editor.determineRelativePath(virtualFile, projectRootPath);

        if (myPairs != null) {
            for (MySearchReplacePair myPair : myPairs) {
                if (path.contains(myPair.getSearch())) {
                    path = path.replace(myPair.getSearch(), myPair.getReplace());
                }
            }
        }
        return path;
    }
}