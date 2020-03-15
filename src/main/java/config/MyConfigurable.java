package config;

import com.intellij.openapi.options.BaseConfigurable;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.ui.AddEditRemovePanel;
import com.intellij.util.ArrayUtilRt;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyConfigurable extends BaseConfigurable implements Configurable.NoScroll {
    private final ApplicationConfig myConfig;
    private JPanel myPanel;
    private List<MyNameLocationPair> myPairs;
    private List<String> myIgnoredUrls;
    private AddEditRemovePanel<MyNameLocationPair> myExtPanel;
    @Nullable private final Project myProject;
    private final List<? extends MyNameLocationPair> myNewPairs;

    @SuppressWarnings("UnusedDeclaration")
    public MyConfigurable(Project project) {
        this(project, Collections.emptyList());
    }

    public MyConfigurable(@Nullable Project project, List<? extends MyNameLocationPair> newResources) {

        myProject = project;
        myNewPairs = newResources;

         myConfig = ApplicationConfig.getInstance(project);
//        myConfig = ProjectManager.

    }

    @Override
    public String getDisplayName() {
        return "Custom Path Resolver";
    }

    @Override
    public JComponent createComponent() {
        myPanel = new JPanel(new GridBagLayout()) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(-1, 400);
            }
        };

        myExtPanel = new AddEditRemovePanel<MyNameLocationPair>(new ExtUrlsTableModel(), myPairs,"Custom Path Resolve Map") {
            @Override
            protected MyNameLocationPair addItem() {
                return addExtLocation();
            }

            @Override
            protected boolean removeItem(MyNameLocationPair o) {
                setModified(true);
                return true;
            }

            @Override
            protected MyNameLocationPair editItem(MyNameLocationPair o) {
                return editExtLocation(o);
            }

            @Override
            public boolean isUpDownSupported () {
                return true;
            }

        };
        myExtPanel.getTable().setShowColumns(true);

        JTable table = myExtPanel.getTable();

        table.getModel().addTableModelListener(e -> setModified(true));

        myPanel.add(myExtPanel,
                new GridBagConstraints(0, 0, 1, 1, 1, 1, GridBagConstraints.NORTH, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));

        return myPanel;
    }

    @Override
    public void apply() {

        myConfig.setMyPairs(myPairs);
        setModified(false);
    }

    @Override
    public void reset() {

        myPairs = myConfig.getMyPairs();

        if (myPairs == null) {
            myPairs = new ArrayList<>();
            myPairs.add(new MyNameLocationPair("", ""));
        }
        myExtPanel.setData(myPairs);

    }

    @Override
    public void disposeUIResources() {
        myPanel = null;
        myExtPanel = null;
    }

    @Override
    public String getHelpTopic() {
        return "";
    }

    @Nullable
    private MyNameLocationPair addExtLocation() {
        setModified(true);
        return new MyNameLocationPair("", "");
    }

    @Nullable
    private MyNameLocationPair editExtLocation(Object o) {
        MyNameLocationPair pair = (MyNameLocationPair)o;
        return pair;
    }

    private class ExtUrlsTableModel extends AddEditRemovePanel.TableModel<MyNameLocationPair> {
        final String[] myNames;

        {
            List<String> names = new ArrayList<>();
            names.add("Search");
            names.add("Replace");

            myNames = ArrayUtilRt.toStringArray(names);
        }

        @Override
        public int getColumnCount() {
            return myNames.length;
        }

        @Override
        public Object getField(MyNameLocationPair pair, int columnIndex) {
            switch (columnIndex) {
                case 0:
                    return pair.myName;
                case 1:
                    return pair.myLocation;
            }

            return "";
        }

        @Override
        public boolean isEditable(int column) {
            return true;
        }

        @Override
        public void setValue(Object aValue, MyNameLocationPair data, int columnIndex) {
            if (columnIndex == 0) {
                data.myName = (String) aValue;
            }
            if (columnIndex == 1) {
                data.myLocation = (String) aValue;
            }
        }

        @Override
        public String getColumnName(int column) {
            return myNames[column];
        }
    }
}
