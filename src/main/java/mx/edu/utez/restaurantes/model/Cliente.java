package mx.edu.utez.restaurantes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = true)
    private String telefono;

    @Column(nullable = true)
    private String correo;

    @ManyToMany(mappedBy = "clientes")
    @JsonIgnore
    private Set<Mesa> mesas = new HashSet<>();


    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private Set<Orden> ordenes = new HashSet<>();


    public Cliente() {

    }

    public Cliente(String nombre, String telefono, String correo) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
    }
}

