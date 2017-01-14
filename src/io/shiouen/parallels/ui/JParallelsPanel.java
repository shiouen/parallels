package io.shiouen.parallels.ui;

import com.intellij.openapi.util.SystemInfo;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.ToolbarDecorator;
import com.intellij.ui.components.JBList;
import com.intellij.util.ui.EditableModel;

import javax.swing.*;
import java.awt.*;

public class JParallelsPanel extends JPanel {
    private JBList settingsControl;
    private EditableModel settingsModel;

    public JParallelsPanel() {
        this.settingsModel = new CollectionListModel<>();
        this.settingsControl = new JBList(this.settingsModel);
        this.settingsControl.getEmptyText().setText("There are no run configurations to run");
        this.settingsControl.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        ToolbarDecorator decorator = ToolbarDecorator.createDecorator(this.settingsControl);

        if (!SystemInfo.isMac) {
            decorator.setAsUsualTopToolbar();
        }

        JPanel decoratedPanel = decorator.createPanel();
        this.setLayout(new BorderLayout());
        this.add(decoratedPanel, BorderLayout.CENTER);

        /*
        JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEADING, JBUI.scale(5), JBUI.scale(5)));
        checkboxPanel.add(myShowSettingsBeforeRunCheckBox);
        checkboxPanel.add(myActivateToolWindowBeforeRunCheckBox);
        add(checkboxPanel, BorderLayout.SOUTH);*/
    }
}
