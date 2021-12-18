package com.salesianostriana.dam.trianatourist.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor
@Builder
public class GetPOIListDTO {

    private UUID id;
    private String name, location, coverPhoto;
    private String category;
}
