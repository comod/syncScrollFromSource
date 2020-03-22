package ui;

import application.Constants;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.wm.impl.status.EncodingPanel;
import com.intellij.util.ui.EmptyIcon;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

public class MyStatusBar extends EncodingPanel {

    public static final String ACTION_CREATE_FILE = "Create File";
    public static final String ACTION_CREATE_FILE_WITH_CONTENT = "Copy File";

    private Consumer<String> createFileAction;
    private Consumer<String> createFileWithContentAction;

    private final Project project;
    private String popupTitle = "";
    private String widgetStateText = "";
    private String widgetStateToolTipText = "";
    private Boolean widgetActionEnabled = true;

    public String ID() {
        return Constants.APPLICATION_NAME;
    }

    public MyStatusBar(@NotNull Project project) {
        super(project);
        this.project = project;
    }

    public void setPath(String path) {
        this.widgetStateText = Constants.APPLICATION_NAME;
        this.popupTitle = path;
        this.widgetStateToolTipText = "Create/Copy " + path + " from Source";
        this.widgetActionEnabled = true;
        this.update();
    }

    public void setOff() {
        this.widgetStateText = "";
        this.popupTitle = "";
        this.widgetStateToolTipText = "";
        this.widgetActionEnabled = true;
        this.update();
    }

    @NotNull
    @Override
    protected WidgetState getWidgetState(@Nullable VirtualFile file) {
        return new WidgetState(this.widgetStateToolTipText, this.widgetStateText, this.widgetActionEnabled);
    }

    @Nullable
    public ListPopup createPopup(@NotNull DataContext dataContext) {

        DefaultActionGroup group = createActionGroup();

        return JBPopupFactory.getInstance().createActionGroupPopup(
                popupTitle,
                group,
                dataContext,
                JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
                this.widgetActionEnabled
        );
    }

    public DefaultActionGroup createActionGroup() {

        DefaultActionGroup group = new DefaultActionGroup();

        AnAction action = new DumbAwareAction(ACTION_CREATE_FILE, null, EmptyIcon.ICON_16) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e)
            {
                createFileAction.accept("");
                setOff();
            }

            @Override
            public void update(@NotNull AnActionEvent e) {
                super.update(e);
            }
        };
        group.add(action);

        AnAction anotherAction = new DumbAwareAction(ACTION_CREATE_FILE_WITH_CONTENT, null, EmptyIcon.ICON_16) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e)
            {
                createFileWithContentAction.accept("");
                setOff();
            }

            @Override
            public void update(@NotNull AnActionEvent e) {
                super.update(e);
            }
        };
        group.add(anotherAction);

        return group;

    }

    public void setCreateFileAction(Consumer<String> callBack) {
        this.createFileAction = callBack;
        //        callBack.accept("");
    }

    public void setCreateFileWithContentAction(Consumer<String> callBack) {
        this.createFileWithContentAction = callBack;
    }

}
