package helper;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.roots.ProjectRootManager;
import com.intellij.openapi.vfs.VfsUtil;
import com.intellij.openapi.vfs.VfsUtilCore;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class Editor {

    private Project project;

    public Editor(Project project) {
        this.project = project;
    }

    public void jumpToLine(VirtualFile virtualFile, Integer line) {

        try {
            if (virtualFile != null && virtualFile.exists()) {

                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        ApplicationManager.getApplication().runReadAction(new Runnable() {
                            public void run() {
                                new OpenFileDescriptor(project, virtualFile, line, 0, false).navigate(false);
                            }
                        });
                    }
                });

            }
        } catch (Exception e) {
            // System.out.println(e.getMessage());
        }
    }

    @NotNull
    public static String determineRootPath(Project project) {
        VirtualFile[] vFiles = ProjectRootManager.getInstance(project).getContentRoots();
        VirtualFile projectRoot = vFiles[0];
        return projectRoot.getPath();
    }

    @NotNull
    public static String determineRelativePath(VirtualFile virtualFile, String rootPath) {
        String filepath = virtualFile.getCanonicalPath();
        return filepath.replace(rootPath + "/", "");
    }

    public VirtualFile getVirtualFileByPath(String path) {
        return this.project.getBaseDir().findFileByRelativePath(path);
    }

    public static void copyFile(VirtualFile virtualFile, String newPath) {

        ApplicationManager.getApplication().runWriteAction(new Runnable() {
            @Override
            public void run()
            {
                try {

                    final VirtualFile newDir = VfsUtil.createDirectories(newPath);
                    if(newDir != null) {
                        VfsUtilCore.copyFile(this, virtualFile, newDir);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
