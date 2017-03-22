package io.shiouen.parallels.ui;

import com.intellij.execution.RunManager;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.*;
import com.intellij.ui.components.JBList;
import io.shiouen.parallels.configuration.ParallelsRunConfiguration;
import io.shiouen.parallels.settings.ParallelsSettings;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.awt.BorderLayout;
import java.util.List;

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
                JBList runConfigurations = new JBList(this.getRunConfigurations());

                runConfigurations.setCellRenderer(this.getListCellRenderer());

                JBPopupFactory.getInstance()
                    .createListPopupBuilder(runConfigurations)
                    .setItemChoosenCallback(() -> {
                        int i = runConfigurations.getSelectedIndex();

                        RunConfiguration runConfiguration = (RunConfiguration) runConfigurations.getModel().getElementAt(i);
                        JParallelsSettingsPanel.this.settingsModel.add(runConfiguration);
                    })
                    .createPopup()
                    .show(anActionButton.getPreferredPopupPoint());
            })
            .setAddActionUpdater(anActionEvent -> !this.getRunConfigurations().isEmpty())
            .setRemoveAction(anActionButtonRunnable -> ListUtil.removeSelectedItems(this.settingsControl));


        if (!SystemInfo.isMac) {
            decorator.setAsUsualTopToolbar();
        }

        JPanel decoratedPanel = decorator.createPanel();
        this.setLayout(new BorderLayout());
        this.add(decoratedPanel, BorderLayout.CENTER);
    }

    public ParallelsSettings getSettings() {
        return new ParallelsSettings(this.settingsModel.getItems());
    }

    public void setSettings(ParallelsSettings parallelsSettings) {
        this.settingsModel.removeAll();
        this.settingsModel.add(parallelsSettings.getRunConfigurations());
    }

    private ListCellRenderer getListCellRenderer() {
        return new ColoredListCellRenderer() {
            @Override
            protected void customizeCellRenderer(@NotNull JList list, Object value, int index, boolean selected, boolean hasFocus) {
                RunConfiguration runConfiguration = (RunConfiguration) value;
                this.setIcon(runConfiguration.getType().getIcon());
                this.append(runConfiguration.getName());
            }
        };
    }

    private List<RunConfiguration> getRunConfigurations() {
        return RunManager
                .getInstance(this.parallelsRunConfiguration.getProject())
                .getAllConfigurationsList()
                .stream()
                .filter(config -> !config.equals(this.parallelsRunConfiguration))
                .filter(config -> !this.settingsModel.getItems().contains(config))
                .collect(toList());
    }
}