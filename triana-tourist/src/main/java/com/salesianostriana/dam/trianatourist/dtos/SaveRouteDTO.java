package com.salesianostriana.dam.trianatourist.dtos;

import com.salesianostriana.dam.trianatourist.validaciones.simple.anotaciones.UniqueRouteName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class SaveRouteDTO {

    @NotBlank(message = "{route.name.blank}")
    @Size(min = 2, max = 32, message = "{route.name.size}")
    @UniqueRouteName(message = "{route.name.unique}")
    private String name;
}
