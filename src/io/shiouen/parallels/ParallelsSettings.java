package io.shiouen.parallels;

import com.intellij.execution.configurations.RunConfiguration;

import java.util.LinkedHashSet;
import java.util.Set;

public class ParallelsSettings {
    private final Set<RunConfiguration> runConfigurations;

    public ParallelsSettings() {
        this.runConfigurations = new LinkedHashSet<>();
    }

    public ParallelsSettings(Set<RunConfiguration> runConfigurations) {
        this();
        this.setRunConfigurations(runConfigurations);
    }

    public ParallelsSettings(ParallelsSettings settings) {
        this(settings.getRunConfigurations());
    }

    public Set<RunConfiguration> getRunConfigurations() {
        return this.runConfigurations;
    }

    public void setRunConfigurations(Set<RunConfiguration> runConfigurations) {
        this.runConfigurations.clear();
        this.runConfigurations.addAll(runConfigurations);
    }

    public boolean hasRunConfigurations() {
        return !this.runConfigurations.isEmpty();
    }
}