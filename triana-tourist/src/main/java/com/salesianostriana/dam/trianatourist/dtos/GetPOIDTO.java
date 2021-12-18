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
public class GetPOIDTO {

    private UUID id;
    private String name, location, description, coverPhoto, photo2, photo3;
    private LocalDateTime date;
    private String category;
}
