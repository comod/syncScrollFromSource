package listener;

import application.Constants;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.util.messages.Topic;

public interface ChangeActionNotifierInterface {

    Topic<ChangeActionNotifierInterface> CHANGE_ACTION_TOPIC = Topic.create(Constants.APPLICATION_NAME + "MyAction", ChangeActionNotifierInterface.class);

    void doAction(Project callerProject, VirtualFile virtualFile);

}