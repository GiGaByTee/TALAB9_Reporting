package utils;

import data.Consts;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    public static String get(String key){
        Properties props = new Properties();
        try {
            props.load(new FileInputStream(new File(Consts.PATH_TO_PROPERTIES_FILE)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return props.getProperty(key);
    }
}
