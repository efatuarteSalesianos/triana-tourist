package com.salesianostriana.dam.trianatourist.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetRouteListDTO {

    private UUID id;
    private String name;
    private int steps;

}
