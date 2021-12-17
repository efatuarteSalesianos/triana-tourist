package com.salesianostriana.dam.trianatourist.modelos;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class POI implements Serializable {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = {
                    @Parameter(
                            name = "uuid_gen_strategy_class",
                            value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
                    )
            }
    )
    private UUID id;

    private String name, location, description, coverPhoto, photo2, photo3;

    private LocalDateTime date;

    @ManyToOne
    private Categoria category;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "poi_id",
            foreignKey = @ForeignKey(name="FK_VISITA_POI")),
            inverseJoinColumns = @JoinColumn(name = "route_id",
                    foreignKey = @ForeignKey(name="FK_VISITA_ROUTE")),
            name = "visita"
    )
    private List<Route> routes;

}

