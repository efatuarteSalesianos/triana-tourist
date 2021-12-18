package com.salesianostriana.dam.trianatourist.modelos;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            joinColumns = @JoinColumn(name="poi_id"),
            inverseJoinColumns = @JoinColumn(name="route_id")
    )
    private List<Route> routes = new ArrayList<>();

    /** MÉTODOS HELPERS PARA ASOCIACIÓN CON CATEGORIA **/
    public void addCategory(Category c) {
        this.category = c;
        c.getSitios().add(this);
    }

    public void removeCategory(Category c) {
        c.getSitios().remove(this);
        this.category = null;
    }

    /** MÉTODOS HELPERS PARA ASOCIACIÓN CON RUTA **/
    public void addRoute(Route r) {
        routes.add(r);
        if(this.getRoutes() == null)
            this.setRoutes((new ArrayList<>()));
        if(r.getSteps() == null)
            r.setSteps(new ArrayList<>());
        r.getSteps().add(this);
    }

    public void removeRoute(Route r) {
        routes.remove(r);
        r.getSteps().remove(this);
    }

}

