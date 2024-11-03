package mx.edu.utez.restaurantes.controller;

import mx.edu.utez.restaurantes.model.Inventario;
import mx.edu.utez.restaurantes.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api/inventario")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    // Obtener todos los artículos del inventario
    @GetMapping
    public ResponseEntity<List<Inventario>> listarInventario() {
        return ResponseEntity.ok(inventarioService.listarInventario());
    }

    // Obtener artículo por ID
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerArticuloPorId(@PathVariable Long id) {
        Optional<Inventario> inventario = inventarioService.obtenerArticuloPorId(id);
        return inventario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear nuevo artículo
    @PostMapping
    public ResponseEntity<Inventario> guardarArticulo(@RequestBody Inventario inventario) {
        Inventario nuevoArticulo = inventarioService.guardarArticulo(inventario);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoArticulo);
    }

    // Actualizar artículo
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizarArticulo(@PathVariable Long id, @RequestBody Inventario inventario) {
        Optional<Inventario> articuloExistente = inventarioService.obtenerArticuloPorId(id);
        if (articuloExistente.isPresent()) {
            inventario.setId(id);
            return ResponseEntity.ok(inventarioService.guardarArticulo(inventario));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar artículo
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarArticulo(@PathVariable Long id) {
        if (inventarioService.eliminarArticulo(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
