package com.leandrosouza.leandroagrotis.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leandrosouza.leandroagrotis.domain.Property;

public record PropertyResponse(Integer id, @JsonProperty("nome") String name) {

    public static PropertyResponse fromModel(Property property) {
        if (property == null)
            return null;
        return new PropertyResponse(property.getId(), property.getName());
    }
}
