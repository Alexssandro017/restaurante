package mx.edu.utez.restaurantes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "mesas")
public class Mesa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Integer numero;

    @Column(nullable = false)
    private Integer capacidad;

    @Column(nullable = false)
    private Boolean disponible;

    @ManyToMany
    @JoinTable(
            name = "mesa_cliente",
            joinColumns = @JoinColumn(name = "mesa_id"),
            inverseJoinColumns = @JoinColumn(name = "cliente_id")
    )
    private Set<Cliente> clientes = new HashSet<>();
}
