package mx.edu.utez.restaurantes.controller;

import mx.edu.utez.restaurantes.dto.OrdenDTO;
import mx.edu.utez.restaurantes.model.Cliente;
import mx.edu.utez.restaurantes.model.Inventario;
import mx.edu.utez.restaurantes.model.Mesa;
import mx.edu.utez.restaurantes.model.Orden;
import mx.edu.utez.restaurantes.service.OrdenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/ordenes")
public class OrdenController {

    @Autowired
    private OrdenService ordenService;

    @PostMapping
    public ResponseEntity<Orden> crearOrden(@RequestBody OrdenDTO ordenDTO) {
        Orden nuevaOrden = ordenService.crearOrden(
                ordenDTO.getEstatus(),
                ordenDTO.getMesaId(),
                ordenDTO.getClienteId(),
                ordenDTO.getInventarioIds()
        );
        return ResponseEntity.ok(nuevaOrden);
    }



    @GetMapping
    public ResponseEntity<List<Orden>> obtenerTodasLasOrdenes() {
        List<Orden> ordenes = ordenService.obtenerTodasLasOrdenes();
        return ResponseEntity.ok(ordenes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orden> obtenerOrdenPorId(@PathVariable Long id) {
        Optional<Orden> orden = ordenService.obtenerOrdenPorId(id);
        return orden.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orden> actualizarEstatusOrden(@PathVariable Long id,
                                                        @RequestParam String nuevoEstatus) {
        Orden ordenActualizada = ordenService.actualizarEstatusOrden(id, nuevoEstatus);
        return ResponseEntity.ok(ordenActualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarOrden(@PathVariable Long id) {
        ordenService.eliminarOrden(id);
        return ResponseEntity.noContent().build();
    }
}
