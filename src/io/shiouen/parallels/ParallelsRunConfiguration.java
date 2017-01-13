package io.shiouen.parallels;

import com.intellij.execution.ExecutionException;
import com.intellij.execution.Executor;
import com.intellij.execution.configurations.*;
import com.intellij.execution.runners.ExecutionEnvironment;
import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

import static io.shiouen.parallels.properties.Properties.from;

public class ParallelsRunConfiguration extends RunConfigurationBase {
    private static final String properties = "/properties/run-config.properties";
    private static final String name = "name";

    private Set<RunConfiguration> configurations;

    public ParallelsRunConfiguration(Project project, @NotNull ParallelsConfigurationType type) {
        super(project, type.getConfigurationFactories()[0], from(properties).get(name));
    }

    @NotNull
    @Override
    public SettingsEditor<? extends RunConfiguration> getConfigurationEditor() {
        return new ParallelsSettingsEditor();
    }

    @Override
    public void checkConfiguration() throws RuntimeConfigurationException {

    }

    @Nullable
    @Override
    public RunProfileState getState(@NotNull Executor executor, @NotNull ExecutionEnvironment executionEnvironment) throws ExecutionException {
        return null;
    }
}
