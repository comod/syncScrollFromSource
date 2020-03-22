package system;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.util.messages.MessageBus;
import listener.ChangeActionNotifier;

public class ProjectStartListener implements ProjectManagerListener {

    public void projectOpened(Project project) {

        MessageBus messageBus = project.getMessageBus();

        // SUBSCRIBE custom Event to the MessageBusConnection
        messageBus.connect().subscribe(ChangeActionNotifier.CHANGE_ACTION_TOPIC, new ChangeActionNotifier(project));

    }
}