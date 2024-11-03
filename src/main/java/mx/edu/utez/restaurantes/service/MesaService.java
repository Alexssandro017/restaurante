package mx.edu.utez.restaurantes.service;

import jakarta.persistence.EntityNotFoundException;
import mx.edu.utez.restaurantes.model.Cliente;
import mx.edu.utez.restaurantes.model.Mesa;
import mx.edu.utez.restaurantes.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private ClienteService clienteService;

    public Mesa guardarMesa(Mesa mesa) {
        Optional<Mesa> mesaExistente = mesaRepository.findByNumero(mesa.getNumero());
        if (mesaExistente.isPresent()) {
            throw new IllegalArgumentException("Ya existe una mesa con el número " + mesa.getNumero());
        }
        return mesaRepository.save(mesa);
    }

    public List<Mesa> obtenerTodasLasMesas() {
        return mesaRepository.findAll();
    }

    public Optional<Mesa> obtenerMesaPorId(Long id) {
        return mesaRepository.findById(id);
    }

    public Mesa actualizarMesa(Long id, Mesa mesaActualizada) {
        Optional<Mesa> mesaExistente = mesaRepository.findById(id);
        if (mesaExistente.isPresent()) {
            Mesa mesa = mesaExistente.get();
            mesa.setCapacidad(mesaActualizada.getCapacidad());
            mesa.setDisponible(mesaActualizada.getDisponible());
            mesa.setNumero(mesaActualizada.getNumero());
            return mesaRepository.save(mesa);
        } else {
            throw new IllegalArgumentException("No se encontró la mesa con id " + id);
        }
    }

    public void eliminarMesa(Long id) {
        Optional<Mesa> mesaExistente = mesaRepository.findById(id);
        if (mesaExistente.isPresent()) {
            mesaRepository.delete(mesaExistente.get());
        } else {
            throw new IllegalArgumentException("No se encontró la mesa con id " + id);
        }
    }

    public Mesa agregarClienteAMesa(Long mesaId, Cliente cliente) {
        Mesa mesa = mesaRepository.findById(mesaId)
                .orElseThrow(() -> new EntityNotFoundException("Mesa no encontrada"));

        if (cliente.getId() == null) {
            cliente = clienteService.crearCliente(cliente); // Método para crear un nuevo cliente
        } else {
            Cliente clienteExistente = clienteService.obtenerClientePorId(cliente.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));
            cliente = clienteExistente; // Utiliza el cliente existente
        }

        mesa.getClientes().add(cliente);
        cliente.getMesas().add(mesa);

        return mesaRepository.save(mesa);
    }
}
