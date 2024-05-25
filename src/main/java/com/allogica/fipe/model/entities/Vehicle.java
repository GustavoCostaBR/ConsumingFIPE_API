package com.allogica.fipe.model.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Vehicle(@JsonAlias("Valor") String code,
                      @JsonAlias("Modelo") String name,
                      @JsonAlias("Marca") String branch,
                      @JsonAlias("AnoModelo") String modelYear,
                      @JsonAlias("Combustivel") String fuel
                      ){
}
