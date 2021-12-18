package com.salesianostriana.dam.trianatourist.dtos;

import com.salesianostriana.dam.trianatourist.validaciones.simple.anotaciones.UniqueName;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
public class SaveCategoryDTO {

    @NotBlank(message = "{category.name.blank}")
    @Size(min = 2, max = 32, message = "{category.name.size}")
    @UniqueName(message = "{category.name.unique}")
    private String name;

}
