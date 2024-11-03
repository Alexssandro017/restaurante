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
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nombre; // Nombre del artículo

    @Column(nullable = false)
    private Integer cantidad; // Cantidad disponible

    @Column(nullable = false)
    private Double precio; // Precio por unidad

    @Column(nullable = false)
    private Boolean activo; // Estado del artículo (disponible o no)

    @Column(nullable = false, length = 30)
    private String tipo; // Tipo de artículo (ej. bebida, comida, etc.)

}
