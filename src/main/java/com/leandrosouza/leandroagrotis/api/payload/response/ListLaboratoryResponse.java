package com.leandrosouza.leandroagrotis.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListLaboratoryResponse {

    List<LaboratoryResponse> laboratories;
}
