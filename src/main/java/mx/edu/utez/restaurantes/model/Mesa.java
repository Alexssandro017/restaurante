package mx.edu.utez.restaurantes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numero; // Número de la mesa

    @Column(nullable = false)
    private Integer capacidad; // Capacidad de la mesa (número de personas)

    @Column(nullable = false)
    private Boolean disponible; // Estado de la mesa (disponible o ocupada)

}
