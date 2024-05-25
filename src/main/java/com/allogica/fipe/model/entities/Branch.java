package com.allogica.fipe.model.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Branch(@JsonAlias("codigo") String code,
                     @JsonAlias("nome") String name) {
}
