package com.allogica.fipe.model.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleYear(@JsonAlias("codigo") String codeYear,
                          @JsonAlias("nome") String nameYear) {
}
