package com.awedzonka.bhpbackend.service.generalresponse.fields;

import lombok.Data;

@Data
public class InputText {
    private final String label;
    private final String id;
    private final String name;
    private final String type;
    private final String value;
}
