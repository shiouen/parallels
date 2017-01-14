package io.shiouen.parallels;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.shiouen.parallels.properties.Properties.from;

public class ParallelsRunConfiguration extends RunConfigurationBase {
    private static final String properties = "/properties/run-config.properties";
    private static final String name = "name";

    private ParallelsSettings settings;

    public ParallelsRunConfiguration(Project project, @NotNull ParallelsConfigurationType type) {
        super(project, type.getConfigurationFactories()[0], from(properties).get(name));

        this.settings = new ParallelsSettings();
    }

    public ParallelsSettings getSettings() {
        return this.settings;
    }

    public void setSettings(ParallelsSettings settings) {
        this.settings = settings;
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new ParallelsSettingsEditor(this.settings);
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        if (this.settings.hasRunConfigurations()) {
            throw new RuntimeConfigurationError("No configurations selected.");
        }
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {
        return null;
    }
}
