package mx.edu.utez.restaurantes.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "ordenes")
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime fechaHora;

    @Column(nullable = false, length = 20)
    private String estatus;

    @ManyToMany
    @JoinTable(
            name = "orden_inventario",
            joinColumns = @JoinColumn(name = "orden_id"),
            inverseJoinColumns = @JoinColumn(name = "inventario_id")
    )
    private Set<Inventario> itemsInventario = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "mesa_id", nullable = false)
    private Mesa mesa;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    public Orden() {
        this.fechaHora = LocalDateTime.now();
    }

    public Orden(String estatus, Mesa mesa, Cliente cliente) {
        this();
        this.estatus = estatus;
        this.mesa = mesa;
        this.cliente = cliente;
    }
}
