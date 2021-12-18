package com.salesianostriana.dam.trianatourist.dtos;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class SavePOIDTO {

    @NotBlank(message = "{poi.name.blank}")
    private String name;

    private String location;

    private String description;

    @NotBlank @URL
    private String coverPhoto;

    @URL
    private String photo2;

    @URL
    private String photo3;

    @Past
    private LocalDateTime date;
}
