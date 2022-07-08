package com.awedzonka.bhpbackend.env;


import com.awedzonka.bhpbackend.dictionary.AppEnvDictionary;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@Component
@Profile({AppEnvDictionary.NOT_PROD})
public class EnvDev implements EnvInterface {

    private final String DOT_ENV = ".env";
    private final String DOT_ENV_LOCAL = ".env.local";

    public String get(String envName) {
        return load().getProperty(envName);
    }

    public String get(EnvDictionary env) {
        return get(env.name());
    }

    private Properties load() {
        try {
            Properties properties = new Properties();
            if (new File(DOT_ENV).exists()) {
                properties.load(new FileInputStream(DOT_ENV));
            }
            // nie ładuj tu pliku .env.prod
            if (new File(DOT_ENV_LOCAL).exists()) {
                properties.load(new FileInputStream(DOT_ENV_LOCAL));
            }
            return properties;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
