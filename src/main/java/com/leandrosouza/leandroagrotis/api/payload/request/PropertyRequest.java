package com.leandrosouza.leandroagrotis.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

public record PropertyRequest(@NotNull Integer id, @JsonProperty("nome") String name) {

    public PropertyRequest(@NotNull Integer id, @JsonProperty("nome") String name) {
        this.id = id;
        this.name = name;
    }
}
