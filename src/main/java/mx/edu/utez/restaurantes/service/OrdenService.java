package mx.edu.utez.restaurantes.service;

import jakarta.persistence.EntityNotFoundException;
import mx.edu.utez.restaurantes.model.Cliente;
import mx.edu.utez.restaurantes.model.Inventario;
import mx.edu.utez.restaurantes.model.Mesa;
import mx.edu.utez.restaurantes.model.Orden;
import mx.edu.utez.restaurantes.repository.ClienteRepository;
import mx.edu.utez.restaurantes.repository.InventarioRepository;
import mx.edu.utez.restaurantes.repository.MesaRepository;
import mx.edu.utez.restaurantes.repository.OrdenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrdenService {

    @Autowired
    private OrdenRepository ordenRepository;

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private InventarioRepository inventarioRepository;


    public Orden crearOrden(String estatus, Long mesaId, Long clienteId, Set<Long> inventarioIds) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada"));
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        Set<Inventario> itemsInventario = new HashSet<>();
        for (Long id : inventarioIds) {
            Inventario inventario = inventarioRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Inventario no encontrado para el ID: " + id));
            itemsInventario.add(inventario);
        }

        Orden nuevaOrden = new Orden(estatus, mesa, cliente);
        nuevaOrden.setItemsInventario(itemsInventario);
        return ordenRepository.save(nuevaOrden);
    }

    public List<Orden> obtenerTodasLasOrdenes() {
        return ordenRepository.findAll();
    }


    public Optional<Orden> obtenerOrdenPorId(Long id) {
        return ordenRepository.findById(id);
    }


    public Orden actualizarEstatusOrden(Long id, String nuevoEstatus) {
        Orden orden = ordenRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Orden no encontrada"));
        orden.setEstatus(nuevoEstatus);
        return ordenRepository.save(orden);
    }


    public void eliminarOrden(Long id) {
        ordenRepository.deleteById(id);
    }
}
