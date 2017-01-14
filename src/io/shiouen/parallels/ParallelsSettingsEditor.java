package io.shiouen.parallels;

import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import io.shiouen.parallels.ui.JParallelsPanel;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.Set;

public class ParallelsSettingsEditor extends SettingsEditor<ParallelsRunConfiguration> {
    private ParallelsSettings settings;

    private JParallelsPanel panel;

    public ParallelsSettingsEditor() {
        this(new ParallelsSettings());
    }

    public ParallelsSettingsEditor(ParallelsSettings settings) {
        this.settings = settings;

        Set<RunConfiguration> configurations = settings.getRunConfigurations();

        for (RunConfiguration configuration : configurations) {

        }

        this.panel = new JParallelsPanel();
    }

    @Override
    protected void resetEditorFrom(@NotNull ParallelsRunConfiguration parallelsRunConfiguration) {
        this.settings = parallelsRunConfiguration.getSettings();
    }

    @Override
    protected void applyEditorTo(@NotNull ParallelsRunConfiguration parallelsRunConfiguration) throws ConfigurationException {
        parallelsRunConfiguration.setSettings(this.settings);
    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return this.panel;
    }
}
