package com.awedzonka.bhpbackend.env;

import org.springframework.stereotype.Component;

@Component
public interface EnvInterface {

    String get(String envName);

    String get(EnvDictionary env);
}
