package com.leandrosouza.leandroagrotis.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record LaboratoryRequest(Integer id, @JsonProperty("nome") String name){}
