package com.leandrosouza.leandroagrotis.api.payload.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leandrosouza.leandroagrotis.domain.Laboratory;

public record LaboratoryResponse(Integer id, @JsonProperty("nome") String name){

    public static LaboratoryResponse fromModel(Laboratory laboratory) {
        if (laboratory == null)
            return null;
        return new LaboratoryResponse(laboratory.getId(), laboratory.getName());
    }
}
