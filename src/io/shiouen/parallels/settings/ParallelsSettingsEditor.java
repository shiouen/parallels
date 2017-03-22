package io.shiouen.parallels.settings;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import io.shiouen.parallels.configuration.ParallelsRunConfiguration;
import io.shiouen.parallels.ui.JParallelsSettingsPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * The ParallelsRunConfiguration settings editor.
 */
public class ParallelsSettingsEditor extends SettingsEditor<ParallelsRunConfiguration> {
    private ParallelsRunConfiguration runConfiguration;

    private JParallelsSettingsPanel panel;

    public ParallelsSettingsEditor(ParallelsRunConfiguration runConfiguration) {
        this.runConfiguration = runConfiguration;

        this.panel = new JParallelsSettingsPanel(this.runConfiguration);
    }

    @Override
    protected void resetEditorFrom(@NotNull ParallelsRunConfiguration parallelsRunConfiguration) {
        this.panel.setSettings(parallelsRunConfiguration.getSettings());
    }

    @Override
    protected void applyEditorTo(@NotNull ParallelsRunConfiguration parallelsRunConfiguration) throws ConfigurationException {
        parallelsRunConfiguration.setSettings(this.panel.getSettings());
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return this.panel;
    }
}
