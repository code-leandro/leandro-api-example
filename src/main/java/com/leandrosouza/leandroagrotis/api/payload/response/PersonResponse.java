package com.leandrosouza.leandroagrotis.api.payload.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.leandrosouza.leandroagrotis.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PersonResponse {

    private Integer id;

    @JsonProperty("nome")
    private String name;

    @JsonProperty("dataInicial")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime start;

    @JsonProperty("dataFinal")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    @JsonProperty(value = "infosPropriedade")
    private PropertyResponse property;

    @JsonProperty(value = "laboratorio")
    private LaboratoryResponse laboratory;

    @JsonProperty("cnpj")
    private String idEmployer;

    @JsonProperty("observacoes")
    private String note;

    public static PersonResponse fromModel(Person person) {
        return PersonResponse
                .builder()
                .id(person.getId())
                .name(person.getName())
                .start(person.getStart())
                .endTime(person.getEndTime())
                .idEmployer(person.getIdEmployer())
                .note(person.getNote())
                .property(PropertyResponse.fromModel(person.getProperty()))
                .laboratory(LaboratoryResponse.fromModel(person.getLaboratory()))
                .build();
    }
}
