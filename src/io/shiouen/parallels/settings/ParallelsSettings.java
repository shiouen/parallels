package io.shiouen.parallels.settings;

import com.intellij.execution.compound.CompoundRunConfiguration;
import com.intellij.execution.configurations.ConfigurationPerRunnerSettings;
import com.intellij.execution.configurations.RunConfiguration;
import io.shiouen.parallels.configuration.ParallelsRunConfiguration;

import java.util.ArrayList;
import java.util.List;

public class ParallelsSettings {
    private final List<RunConfiguration> runConfigurations;

    public ParallelsSettings() {
        this(new ArrayList<>());
    }

    public ParallelsSettings(List<RunConfiguration> runConfigurations) {
        this.runConfigurations = new ArrayList<>();
        this.setRunConfigurations(runConfigurations);
    }

    public List<RunConfiguration> getRunConfigurations() {
        return this.runConfigurations;
    }

    public void setRunConfigurations(List<RunConfiguration> runConfigurations) {
        this.runConfigurations.clear();
        this.runConfigurations.addAll(runConfigurations);
    }

    public boolean hasRunConfigurations() {
        return !this.runConfigurations.isEmpty();
    }

    public boolean hasDuplicateRunConfigurations() {
        for (RunConfiguration configuration : this.runConfigurations) {
            boolean isParallels = configuration instanceof ParallelsRunConfiguration ||
                    configuration instanceof CompoundRunConfiguration;

//            if (hasPossibleDuplicates) {
//
//            }
        }

        return false;
    }
}