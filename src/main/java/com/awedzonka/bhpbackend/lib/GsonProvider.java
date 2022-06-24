package com.awedzonka.bhpbackend.lib;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;

@Component
public class GsonProvider {
    public Gson get() {
        return new GsonBuilder().serializeNulls().create();
    }
}
