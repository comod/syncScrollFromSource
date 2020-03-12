package system;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.project.ProjectManagerListener;
import com.intellij.util.messages.MessageBus;
import listener.ChangeActionNotifier;
import listener.ChangeActionNotifierInterface;

public class ProjectStartListener implements ProjectManagerListener {

    public void projectOpened(Project project) {

        System.out.println("projectOpened (ProjectManagerListener)" + project.toString());

        MessageBus messageBus = project.getMessageBus();

        // SUBSCRIBE custom Event to the MessageBusConnection
        messageBus.connect().subscribe(ChangeActionNotifier.CHANGE_ACTION_TOPIC, new ChangeActionNotifier(project));

    }

    //    public void projectClosed(Project project) {
    //        System.out.println("projectClosed (ProjectManagerListener)" + project.toString());
    //    }

}