package io.shiouen.parallels;

import com.intellij.execution.configurations.RunConfiguration;

import java.util.LinkedHashSet;
import java.util.Set;

public class ParallelsSettings {
    private Set<RunConfiguration> runConfigurations;

    public ParallelsSettings() {
        this.runConfigurations = new LinkedHashSet<>();
    }

    public Set<RunConfiguration> getRunConfigurations() {
        return this.runConfigurations;
    }

    public void setRunConfigurations(Set<RunConfiguration> runConfigurations) {
        this.runConfigurations = runConfigurations;
    }

    public boolean hasRunConfigurations() {
        return !this.runConfigurations.isEmpty();
    }

    public void addRunConfiguration(RunConfiguration runConfiguration) {
        this.runConfigurations.add(runConfiguration);
    }
}