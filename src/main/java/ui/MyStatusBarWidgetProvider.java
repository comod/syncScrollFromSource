package ui;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.openapi.wm.StatusBarWidgetProvider;
import org.jetbrains.annotations.NotNull;

public class MyStatusBarWidgetProvider implements StatusBarWidgetProvider {

    @NotNull
    @Override
    public StatusBarWidget getWidget(@NotNull Project project) {
        MyStatusBar statusBar = new MyStatusBar(project);
        return statusBar;
    }

    @NotNull
    @Override
    public String getAnchor() {
        return StatusBar.Anchors.before(StatusBar.StandardWidgets.ENCODING_PANEL);
    }
}
