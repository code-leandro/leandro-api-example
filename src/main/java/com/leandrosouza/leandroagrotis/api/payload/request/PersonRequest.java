package com.leandrosouza.leandroagrotis.api.payload.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.leandrosouza.leandroagrotis.domain.Laboratory;
import com.leandrosouza.leandroagrotis.domain.Person;
import com.leandrosouza.leandroagrotis.domain.Property;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonRequest {

    @Size(max = 40)
    @JsonProperty("nome")
    private String name;

    @JsonProperty("dataInicial")
    private LocalDateTime start;

    @JsonProperty("dataFinal")
    private LocalDateTime endTime;

    @JsonProperty(value = "infosPropriedade")
    private PropertyRequest propertyRequest;

    @JsonProperty(value = "laboratorio")
    private LaboratoryRequest laboratoryRequest;

    @JsonProperty("cnpj")
    private String idEmployer;

    @Size(max = 1000)
    @JsonProperty("observacoes")
    private String note;

    public Person toModel() {
        return Person
            .builder()
            .name(name)
            .start(start)
            .endTime(endTime)
            .idProperty(propertyRequest.id())
            .idLaboratory(laboratoryRequest.id())
            .idEmployer(idEmployer)
            .note(note)
            .build();
    }
}
