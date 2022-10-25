package com.leandrosouza.leandroagrotis.builder;

import com.leandrosouza.leandroagrotis.domain.Laboratory;

import java.util.List;

public class LaboratoryBuilder {

    public static Integer ID_TEST = 1010;
    public static String NAME_TEST = "my name test";

    public static Laboratory getLaboratory() {
        return Laboratory
                .builder()
                .id(ID_TEST)
                .name(NAME_TEST)
                .build();
    }

    public static List<Laboratory> getList() {
        return List.of(getLaboratory());
    }
}
