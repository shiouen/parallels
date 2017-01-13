package io.shiouen.parallels.properties;

public class Properties {
    private static String resource;
    private static PropertyFile propertyFile;

    static {
        resource = "";
    }

    public static PropertyFile from(String resource) {
        if (Properties.resource.equals(resource) && !Properties.resource.isEmpty()) {
            return propertyFile;
        }

        try {
            propertyFile = new PropertyFile(resource);
            Properties.resource = resource;
        } catch (Exception e) {
            return null;
        }

        return propertyFile;
    }
}
