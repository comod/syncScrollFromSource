package ui;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.EditorFactory;
import com.intellij.openapi.editor.event.DocumentEvent;
import com.intellij.openapi.editor.event.DocumentListener;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.ui.popup.ListPopup;
import com.intellij.openapi.util.Pair;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileListener;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;
import com.intellij.openapi.vfs.encoding.ChangeFileEncodingAction;
import com.intellij.openapi.vfs.encoding.EncodingUtil;
import com.intellij.openapi.vfs.impl.BulkVirtualFileListenerAdapter;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.impl.status.EncodingPanel;
import com.intellij.ui.popup.list.ListPopupImpl;
import com.intellij.util.ObjectUtils;
import listener.ChangeActionNotifier;
import listener.ChangeActionNotifierInterface;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.charset.Charset;

public class MyStatusBar extends EncodingPanel {

    private final Project project;
    private String widgetStateText = "";
    private String widgetStateToolTipText = "";
    private Boolean widgetActionEnabled = false;

    public String ID() {
        return StatusBar.StandardWidgets.ENCODING_PANEL + "My";
    }

    public MyStatusBar(@NotNull Project project) {
        super(project);
        this.project = project;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
        super.install(statusBar);
        System.out.println("install");
        this.project.getMessageBus().connect().subscribe(ChangeActionNotifier.CHANGE_ACTION_TOPIC, path -> {
            System.out.println("path " + path);
            this.widgetStateText = path;
            this.widgetStateToolTipText = "Open " + path;
            this.widgetActionEnabled = true;
            this.update();
        });

    }

    @NotNull
    @Override
    protected WidgetState getWidgetState(@Nullable VirtualFile file) {
        System.out.println("getWidgetState");

        return new WidgetState(this.widgetStateToolTipText, this.widgetStateText, this.widgetActionEnabled);
    }

    @Nullable
    @Override
    protected ListPopup createPopup(DataContext context) {
        MyStatusBarAction action = new MyStatusBarAction();
        action.getTemplatePresentation().setText("My Action");
        return action.createPopup(context);
    }
}
