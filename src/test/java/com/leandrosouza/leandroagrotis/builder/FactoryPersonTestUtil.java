package com.leandrosouza.leandroagrotis.builder;

import com.leandrosouza.leandroagrotis.api.payload.request.LaboratoryRequest;
import com.leandrosouza.leandroagrotis.api.payload.request.PersonRequest;
import com.leandrosouza.leandroagrotis.api.payload.request.PropertyRequest;
import com.leandrosouza.leandroagrotis.domain.Person;

import java.util.List;

public class FactoryPersonTestUtil {

    public static Integer ID = 999999999;
    public static String NAME = "name_test";

    public static Person getPersonWithId() {
        Person person = getPerson();
        person.setId(ID);
        return person;
    }

    public static Person getPerson() {
        return Person
                .builder()
                .name(NAME)
                .idProperty(FactoryPropertyTestUtil.ID_TEST)
                .idLaboratory(FactoryLaboratoryTestUtil.ID_TEST)
                .build();
    }

    public static List<Person> list() {
        return List.of(getPersonWithId());
    }

    public static PersonRequest getPersonRequest(){
        return PersonRequest
                .builder()
                .name(NAME)
                .propertyRequest(new PropertyRequest(FactoryPropertyTestUtil.ID_TEST, FactoryPropertyTestUtil.NAME_TEST))
                .laboratoryRequest(new LaboratoryRequest(FactoryLaboratoryTestUtil.ID_TEST, FactoryLaboratoryTestUtil.NAME_TEST))
                .build();
    }

    public static Person getPersonRequestToModel(){
        return getPersonRequest().toModel();
    }

    public static Person getPersonRequestToModelWithId(){
        Person person = getPersonRequest().toModel();
        person.setId(ID);
        return person;
    }
}
