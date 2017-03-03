package io.shiouen.parallels.ui;

import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.*;
import com.intellij.ui.components.JBList;
import io.shiouen.parallels.ParallelsRunConfiguration;
import io.shiouen.parallels.ParallelsSettings;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

public class JParallelsSettingsPanel extends JPanel {
    private ParallelsRunConfiguration parallelsRunConfiguration;
    private JBList settingsControl;
    private CollectionListModel<RunConfiguration> settingsModel;

    public JParallelsSettingsPanel(ParallelsRunConfiguration parallelsRunConfiguration) {
        this.settingsModel = new CollectionListModel<>();

        this.settingsControl = new JBList(this.settingsModel);
        this.settingsControl.getEmptyText().setText("There are no configurations to run");
        this.settingsControl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.settingsControl.setCellRenderer(this.getListCellRenderer());
        this.settingsControl.setVisibleRowCount(15);

        this.parallelsRunConfiguration = parallelsRunConfiguration;

        ToolbarDecorator decorator = ToolbarDecorator
            .createDecorator(this.settingsControl)
            .setAddAction(anActionButton -> {
                JBList externals = new JBList(this.getExternalRunConfigurations());

                externals.setCellRenderer(this.getListCellRenderer());

                JBPopupFactory.getInstance()
                    .createListPopupBuilder(externals)
                    .setItemChoosenCallback(() -> {
                        int i = externals.getSelectedIndex();

                        RunConfiguration runConfiguration = (RunConfiguration) externals.getModel().getElementAt(i);
                        JParallelsSettingsPanel.this.settingsModel.add(runConfiguration);
                    })
                    .createPopup()
                    .show(anActionButton.getPreferredPopupPoint());
            })
            .setAddActionUpdater(anActionEvent -> !this.getExternalRunConfigurations().isEmpty())
            .setRemoveAction(anActionButtonRunnable -> ListUtil.removeSelectedItems(this.settingsControl));


        if (!SystemInfo.isMac) {
            decorator.setAsUsualTopToolbar();
        }

        JPanel decoratedPanel = decorator.createPanel();
        this.setLayout(new BorderLayout());
        this.add(decoratedPanel, BorderLayout.CENTER);
    }

    public ParallelsSettings getSettings() {
        Set<RunConfiguration> runConfigurations = new LinkedHashSet<>(this.settingsModel.getItems());

        ParallelsSettings settings = new ParallelsSettings(runConfigurations);

        return settings;
    }

    public void setSettings(ParallelsSettings parallelsSettings) {
        Set<RunConfiguration> runConfigurations = parallelsSettings.getRunConfigurations();

        this.settingsModel = new CollectionListModel<>();

        for (RunConfiguration runConfiguration : runConfigurations) {
            this.settingsModel.add(runConfiguration);
        }
    }

    private ListCellRenderer getListCellRenderer() {
        return new ColoredListCellRenderer() {
            @Override
            protected void customizeCellRenderer(@NotNull JList list, Object value, int index, boolean selected, boolean hasFocus) {
                RunConfiguration runConfiguration = JParallelsSettingsPanel.this.settingsModel.getElementAt(index);

                this.setIcon(runConfiguration.getType().getIcon());
                this.append(String.format("%s '%s'",
                        runConfiguration.getType().getDisplayName(),
                        runConfiguration.getName()));
            }
        };
    }
    private List<RunConfiguration> getExternalRunConfigurations() {
        return RunManager
                .getInstance(this.parallelsRunConfiguration.getProject())
                .getAllConfigurationsList()
                .stream()
                .filter(config -> !config.equals(this.parallelsRunConfiguration))
                .collect(toList());
    }
}
