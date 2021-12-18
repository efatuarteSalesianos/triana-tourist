package com.salesianostriana.dam.trianatourist.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class SavePOIDTO {

    private String name, location, description, coverPhoto, photo2, photo3;
    private LocalDateTime date;
    private UUID categoryId;

}
