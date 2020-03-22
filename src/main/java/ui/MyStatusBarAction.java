package ui;

import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class MyStatusBarAction extends AnAction implements DumbAware {

    @Override
    public void update(@NotNull AnActionEvent e) {
        System.out.println("update MyStatusBarAction");
    }

    @Override
    public final void actionPerformed(@NotNull final AnActionEvent e) {
        DataContext dataContext = e.getDataContext();

        ListPopup popup = createPopup(dataContext);
        if (popup != null) {
            popup.showInBestPositionFor(dataContext);
        }
    }

    @Nullable
    public ListPopup createPopup(@NotNull DataContext dataContext) {

        DefaultActionGroup group = createActionGroup();

        return JBPopupFactory.getInstance().createActionGroupPopup(
            getTemplatePresentation().getText(),
            group,
            dataContext,
            JBPopupFactory.ActionSelectionAid.SPEEDSEARCH,
            true
        );
    }

    public DefaultActionGroup createActionGroup() {

        return new MyStatusBarChooseAction() {
            @Override
            public void update(@NotNull final AnActionEvent e) {
            }

            @NotNull
            @Override
            protected DefaultActionGroup createPopupActionGroup(JComponent button) {
                return createCharsetsActionGroup();
                // no 'clear'
            }
        }
        .createPopupActionGroup(null);

    }
}
