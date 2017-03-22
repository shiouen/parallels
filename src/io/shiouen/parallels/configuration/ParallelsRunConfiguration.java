package io.shiouen.parallels.configuration;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import io.shiouen.parallels.settings.ParallelsSettings;
import io.shiouen.parallels.settings.ParallelsSettingsEditor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static io.shiouen.parallels.properties.Properties.from;

/**
 * Instance of a ParallelsConfigurationType RunConfiguration.
 * Is used from the moment you add a configuration of that type.
 */
public class ParallelsRunConfiguration extends RunConfigurationBase {
    private static final String properties = "/properties/run-configuration.properties";
    private static final String name = "name";

    private ParallelsSettings settings;

    public ParallelsRunConfiguration(Project project, @NotNull ParallelsConfigurationFactory factory) {
        super(project, factory, from(properties).get(name));
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
        return new ParallelsSettingsEditor(this);
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {
        if (!this.settings.hasRunConfigurations()) {
            throw new RuntimeConfigurationError("No run configurations selected.");
        }

        // check here if recursive repetition of config
    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {
        return null;
    }
}
