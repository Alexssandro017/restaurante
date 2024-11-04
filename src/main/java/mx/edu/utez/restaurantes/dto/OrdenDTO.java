package mx.edu.utez.restaurantes.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class OrdenDTO {
    private String estatus;
    private Long mesaId;
    private Long clienteId;
    private Set<Long> inventarioIds;
}
