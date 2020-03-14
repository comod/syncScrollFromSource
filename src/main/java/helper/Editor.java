package helper;

import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;

public class Editor {

    private Project project;

    public Editor(Project project) {
        this.project = project;
    }

    public void jumpToLine(String path, Integer line) {


        // www/module/Hausrat/src/Hausrat/BusinessObject/DamageReport.php
        // www/module/Product/src/Product/?

        System.out.println("JUMP" + path + " in " + project.getName());
//        System.out.println(project.getBaseDir());
//        System.out.println(project.getBaseDir().findFileByRelativePath(path));

        try {

            VirtualFile file = project.getBaseDir().findFileByRelativePath(path);
            System.out.println("open: " + file);
            if (file != null && file.exists()) {

                ApplicationManager.getApplication().invokeLater(new Runnable() {
                    public void run() {
                        ApplicationManager.getApplication().runReadAction(new Runnable() {
                            public void run() {
                                System.out.println("!");
                                new OpenFileDescriptor(project, file, line, 0, false).navigate(true);
                            }
                        });
                    }
                });

            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
