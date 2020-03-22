package ui;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.ex.ComboBoxAction;
import com.intellij.openapi.project.DumbAwareAction;
import com.intellij.util.ui.EmptyIcon;
import org.jetbrains.annotations.NotNull;

public abstract class MyStatusBarChooseAction extends ComboBoxAction {

    public static final String ACTION_CREATE_FILE = "Create File";
    public static final String ACTION_CREATE_FILE_WITH_CONTENT = "Another";

    @Override
    public abstract void update(@NotNull final AnActionEvent e);

    @NotNull
    protected DefaultActionGroup createCharsetsActionGroup() {
        DefaultActionGroup group = new DefaultActionGroup();

        AnAction action = new DumbAwareAction(ACTION_CREATE_FILE, null, EmptyIcon.ICON_16) {
            @Override
            public void actionPerformed(@NotNull AnActionEvent e)
            {
                System.out.println(e);

                // chosen(true);
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
                System.out.println(e);

                // chosen(true);
            }

            @Override
            public void update(@NotNull AnActionEvent e) {
                super.update(e);
            }
        };
        group.add(anotherAction);

        return group;
    }
}
