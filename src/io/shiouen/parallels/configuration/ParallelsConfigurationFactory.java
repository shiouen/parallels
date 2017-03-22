package io.shiouen.parallels.configuration;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Factory class responsible for creating ParallelsRunConfiguration instances.
 */
public class ParallelsConfigurationFactory extends ConfigurationFactory {
    public ParallelsConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new ParallelsRunConfiguration(project, this);
    }

    @Override
    public boolean isConfigurationSingletonByDefault() {
        return true;
    }
}