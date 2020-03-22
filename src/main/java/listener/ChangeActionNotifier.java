package listener;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import config.ApplicationConfig;
import config.MySearchReplacePair;
import helper.Editor;

import java.util.List;

public class ChangeActionNotifier implements ChangeActionNotifierInterface {

    private final Editor editor;
    private final List<MySearchReplacePair> myPairs;

    public ChangeActionNotifier(Project project) {

        editor = new Editor(project);
        ApplicationConfig config = ApplicationConfig.getInstance(project);

        myPairs = config.getMyPairs();

    }

    @Override
    public void doAction(String path) {

        if (myPairs != null) {
            for (MySearchReplacePair myPair : myPairs) {
                System.out.println("myPair" + myPair);
                if (path.contains(myPair.getSearch())) {
                    path = path.replace(myPair.getSearch(), myPair.getReplace());
                }
            }
        }

        VirtualFile file = editor.getVirtualFile(path);
        if (file != null && file.exists()) {
            editor.jumpToLine(path, 0);
        } else {
            System.out.println("Create File " + path);
        }

    }
}