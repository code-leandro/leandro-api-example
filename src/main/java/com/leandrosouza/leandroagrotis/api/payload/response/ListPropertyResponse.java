package com.leandrosouza.leandroagrotis.api.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListPropertyResponse {

    List<PropertyResponse> properties;
}
