package system;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.BaseComponent;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.util.messages.MessageBus;
import com.intellij.util.messages.MessageBusConnection;
import listener.ChangeActionNotifier;

public class ApplicationStart implements BaseComponent {

    public void initComponent() {

        System.out.println("Application started");

        Application application = ApplicationManager.getApplication();
        MessageBusConnection messageBusConnection = application.getMessageBus().connect();
        messageBusConnection.subscribe(ProjectManager.TOPIC, new ProjectStartListener());

    }

}