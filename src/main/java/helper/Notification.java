package helper;

import application.Constants;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;

import javax.swing.*;

public class Notification {

    /**
     * Notify - Real IDE native Notifications
     * @param title
     * @param message
     */
    public static void notify(String title, String message) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Notifications.Bus.notify(new com.intellij.notification.Notification(Constants.APPLICATION_NAME, title + ".", message + ".", NotificationType.INFORMATION));
            }
        });
    }

}