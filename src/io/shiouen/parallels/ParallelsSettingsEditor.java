package io.shiouen.parallels;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public class ParallelsSettingsEditor extends SettingsEditor<ParallelsRunConfiguration> {
    @Override
    protected void resetEditorFrom(@NotNull ParallelsRunConfiguration parallelsRunConfiguration) {

    }

    @Override
    protected void applyEditorTo(@NotNull ParallelsRunConfiguration parallelsRunConfiguration) throws ConfigurationException {

    }

    @NotNull
    @Override
    protected JComponent createEditor() {
        return null;
    }
}
