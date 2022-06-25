package com.awedzonka.bhpbackend.service.generalresponse.fields;

import java.util.List;

public class ContentPage {
    private final String header;
    private final List<String> content;

    public ContentPage(String header, List<String> content) {
        this.header = header;
        this.content = content;
    }
}
