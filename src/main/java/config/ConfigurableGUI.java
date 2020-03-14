package config;

import application.Actions;
import com.intellij.ui.ClickListener;

import javax.swing.*;

/**
 * GUI for the {@link Configurable}
 */
public class ConfigurableGUI {

    private ApplicationConfig mConfig = null;

    private JPanel rootPanel;

    private JTextArea scriptEditor;
    private JTextField testPath;
    private JButton testButton;
    private JTextField testResult;


    public void createUI() {

        // Test-Section
        testButton.addActionListener(e -> {
            testResult.setText(Actions.evalScript(scriptEditor.getText(), testPath.getText()));
        });

        this.mConfig = ApplicationConfig.getInstance();
//        this.host.setText(this.mConfig.getHost());
//        this.user.setText(this.mConfig.getUser());
//        this.pass.setText(this.mConfig.getPass());
    }

    public JPanel getRootPanel() {
        return this.rootPanel;
    }
//
//    public JTextField getHost() {
//        return this.host;
//    }

    public boolean isModified() {
        boolean modified = false;
//        modified |= !this.host.getText().equals(this.mConfig.getHost());
//        modified |= !this.user.getText().equals(this.mConfig.getUser());
//        modified |= !this.pass.getText().equals(this.mConfig.getPass());
        return modified;
    }

    public void apply() {
//        this.mConfig.setHost(this.host.getText());
//        this.mConfig.setUser(this.user.getText());
//        this.mConfig.setPass(this.pass.getText());
    }

    public void reset() {
//        this.host.setText(this.mConfig.getHost());
//        this.user.setText(this.mConfig.getUser());
//        this.pass.setText(this.mConfig.getPass());
    }

}
