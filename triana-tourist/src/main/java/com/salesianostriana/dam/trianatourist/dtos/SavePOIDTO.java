package com.salesianostriana.dam.trianatourist.dtos;

import com.salesianostriana.dam.trianatourist.validaciones.multiple.anotaciones.UniqueUrl;
import com.salesianostriana.dam.trianatourist.validaciones.simple.anotaciones.IsCoordinate;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@Builder
@UniqueUrl.List({
        @UniqueUrl(url1 = "coverPhoto", url2 = "photo2"),
        @UniqueUrl(url1 = "coverPhoto", url2 = "photo3"),
        @UniqueUrl(url1 = "photo2", url2 = "photo3")
})
public class SavePOIDTO {

    @NotBlank(message = "{poi.name.blank}")
    private String name;

    @IsCoordinate(message = "{poi.location.isCoordinate}")
    private String location;

    @Lob
    private String description;

    @NotBlank(message = "{poi.coverPhoto.blank}")
    @URL(message = "{poi.coverPhoto.url}")
    private String coverPhoto;

    @URL(message = "{poi.photo2.url}")
    private String photo2;

    @URL(message = "{poi.photo3.url}")
    private String photo3;

    @Past(message = "{poi.date.past}")
    private LocalDateTime date;

    private UUID categoryId;
}
