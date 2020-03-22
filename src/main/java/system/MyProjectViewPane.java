package system;

import com.intellij.ide.SelectInTarget;
import com.intellij.ide.impl.ProjectPaneSelectInTarget;
import com.intellij.ide.projectView.impl.ProjectViewPane;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiElement;
import com.intellij.util.messages.MessageBus;
import listener.ChangeActionNotifierInterface;
import org.jetbrains.annotations.NotNull;

import static helper.DoubleClick.isDoubleClick;

class MyProjectViewPane extends ProjectViewPane {

    private static int Weight = 1;

    public MyProjectViewPane(Project project) {
        super(project);
    }

    @Override
    public int getWeight() {
        return Weight++;
    }

    @NotNull
    @Override
    public SelectInTarget createSelectInTarget() {
        return new ProjectPaneSelectInTarget(myProject) {
            @Override
            public void select(PsiElement element, boolean requestFocus) {
                super.select(element, requestFocus);

                if (isDoubleClick()) {

                    VirtualFile virtualFile = determineVirtualFile(element);

                    ProjectManager projectManager = ProjectManager.getInstance();
                    Project[] projects = projectManager.getOpenProjects();
                    for (Project project : projects) {

                        if (project == myProject) {
                            continue;
                        }

                        MessageBus messageBus = project.getMessageBus();

                        // PUBLISH to listener
                        ChangeActionNotifierInterface publisher = messageBus.syncPublisher(ChangeActionNotifierInterface.CHANGE_ACTION_TOPIC);
                        publisher.doAction(myProject, virtualFile);

                    }

                }

            }

            @NotNull
            private VirtualFile determineVirtualFile(PsiElement element) {
                return element.getContainingFile().getVirtualFile();
            }

        };
    }
}