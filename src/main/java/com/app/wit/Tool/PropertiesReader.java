package com.app.wit.Tool;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    //PUBLIC METHODS
    public static String getMessage(String language, String key) {
        return getValueFromKey(key, "src/main/resources/language-" + language + ".properties");
    }

    public static String getPath(String key) {
        return getValueFromKey(key, "src/main/resources/path.properties");
    }

    public static void setPathValueFromKey(String key, String value) {
        setValueFromKey(key, value, "src/main/resources/path.properties");
    }

    //PRIVATE METHODS
    private static String getValueFromKey(String key, String path) {
        Properties properties = new Properties();
        String value = null;

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
            value = properties.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return value;
    }

    private static void setValueFromKey(String key, String value, String path) {
        Properties properties = new Properties();

        try (FileInputStream fileInputStream = new FileInputStream(path)) {
            properties.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        properties.setProperty(key, value);

        try (FileOutputStream fileOutputStream = new FileOutputStream(path)) {
            properties.store(fileOutputStream, "User used this directory last time they imported a file");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}