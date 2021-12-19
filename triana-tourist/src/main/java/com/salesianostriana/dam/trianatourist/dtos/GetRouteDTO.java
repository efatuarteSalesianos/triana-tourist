package com.salesianostriana.dam.trianatourist.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetRouteDTO {

    private UUID id;
    private String name;
    private List<GetPOIListDTO> steps;

}
