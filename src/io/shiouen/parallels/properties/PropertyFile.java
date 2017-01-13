package io.shiouen.parallels.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFile {
    private Properties properties;

    public PropertyFile(String resource) throws IOException {
        this.properties = new Properties();
        this.load(resource);
    }

    public void load(String resource) throws IOException {
        InputStream stream = PropertyFile.class.getResourceAsStream(resource);
        this.properties.load(stream);
    }

    public String get(String name) {
        return this.properties.getProperty(name);
    }

    public String get(String name, String defaultValue) {
        return this.properties.getProperty(name, defaultValue);
    }
}
