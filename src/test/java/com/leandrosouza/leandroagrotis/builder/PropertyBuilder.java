package com.leandrosouza.leandroagrotis.builder;

import com.leandrosouza.leandroagrotis.domain.Property;

import java.util.List;

public class PropertyBuilder {

    public static Integer ID_TEST = 1010;
    public static String NAME_TEST = "my name test";

    public static Property getProperty() {
        return Property
            .builder()
            .id(ID_TEST)
            .name(NAME_TEST)
            .build();
    }

    public static List<Property> getList() {
        return List.of(getProperty());
    }
}
