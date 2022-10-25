package com.leandrosouza.leandroagrotis.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PropertyRequest(Integer id, @JsonProperty("nome") String name) {}
