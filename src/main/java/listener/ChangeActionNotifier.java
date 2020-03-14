package listener;

import com.intellij.openapi.project.Project;
import helper.Editor;

public class ChangeActionNotifier implements ChangeActionNotifierInterface {

    private final Editor editor;

    public ChangeActionNotifier(Project project) {
        editor = new Editor(project);
    }

    @Override
    public void doAction(String path) {

        editor.jumpToLine(path, 0);
        // Notification.notify("(" + registeredFor + ")", context);

    }
}