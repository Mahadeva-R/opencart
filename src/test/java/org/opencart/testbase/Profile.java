package org.opencart.testbase;

import java.util.Properties;

public class Profile {

    private static final ThreadLocal<Profile> context = new ThreadLocal<>();
    private static Properties properties;

    static {
        loadPropertyFile();
    }

    private Profile() {
    }

    public static Profile getProfileInstance() {
        if (context.get() == null) {
            context.set(new Profile());
        }
        return context.get();
    }

    public static void loadPropertyFile() {
        try {
            properties = new Properties();
            properties.load(
                    Profile.class
                            .getClassLoader()
                            .getResourceAsStream("config.properties")
            );
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public String getAppUrl() {
        return properties.getProperty("url");
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getOs() {
        return properties.getProperty("os");
    }

    public String getLoginEmailId(){
        return properties.getProperty("email");
    }

    public String getPassword(){
        return properties.getProperty("password");
    }
}