package io.shiouen.parallels;

import com.intellij.execution.configurations.ConfigurationTypeBase;
import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.Icon;

import static io.shiouen.parallels.properties.Properties.from;

public class ParallelsConfigurationType extends ConfigurationTypeBase {
    private static final String properties = "/properties/run-config-type.properties";

    private static final String id = "id";
    private static final String name = "name";
    private static final String description = "description";
    private static final String icon = "icon";

    public ParallelsConfigurationType() {
        this(from(properties).get(id),
             from(properties).get(name),
             from(properties).get(description),
             IconLoader.getIcon(from(properties).get(icon)));

        addFactory(new ParallelsConfigurationFactory(this));
    }

    public ParallelsConfigurationType(@NotNull String id, String displayName, String description, Icon icon) {
        super(id, displayName, description, icon);
    }
}
