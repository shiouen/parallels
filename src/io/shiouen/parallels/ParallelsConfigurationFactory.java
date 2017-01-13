package io.shiouen.parallels;

import com.intellij.execution.configurations.ConfigurationFactory;
import com.intellij.execution.configurations.ConfigurationType;
import com.intellij.execution.configurations.RunConfiguration;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;


public class ParallelsConfigurationFactory extends ConfigurationFactory {
    private ParallelsConfigurationType parallelsConfigurationType;

    public ParallelsConfigurationFactory(@NotNull ConfigurationType type) {
        super(type);

        this.parallelsConfigurationType = (ParallelsConfigurationType) type;
    }

    @NotNull
    @Override
    public RunConfiguration createTemplateConfiguration(@NotNull Project project) {
        return new ParallelsRunConfiguration(project, this.parallelsConfigurationType);
    }
}
