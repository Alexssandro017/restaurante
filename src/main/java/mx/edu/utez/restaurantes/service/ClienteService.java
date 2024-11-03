package mx.edu.utez.restaurantes.service;

import jakarta.persistence.EntityNotFoundException;
import mx.edu.utez.restaurantes.model.Cliente;
import mx.edu.utez.restaurantes.model.Mesa;
import mx.edu.utez.restaurantes.repository.ClienteRepository;
import mx.edu.utez.restaurantes.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private MesaRepository mesaRepository;

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente guardarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Long id) {
        return clienteRepository.findById(id);
    }


    /*
    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            Cliente cliente = clienteExistente.get();
            cliente.setNombre(clienteActualizado.getNombre());
            cliente.setTelefono(clienteActualizado.getTelefono());
            cliente.setCorreo(clienteActualizado.getCorreo());
            return clienteRepository.save(cliente);
        } else {
            throw new IllegalArgumentException("No se encontró el cliente con id " + id);
        }
    }

     */

    /*
    public void eliminarCliente(Long id) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);
        if (clienteExistente.isPresent()) {
            clienteRepository.delete(clienteExistente.get());
        } else {
            throw new IllegalArgumentException("No se encontró el cliente con id " + id);
        }
    }
     */
    public void eliminarCliente(Long clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        for (Mesa mesa : cliente.getMesas()) {
            mesa.getClientes().remove(cliente);

            mesaRepository.save(mesa);
        }

        cliente.getMesas().clear();
        clienteRepository.delete(cliente);
    }

    public Cliente actualizarCliente(Long clienteId, Cliente nuevosDatos, Set<Long> mesaIds) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new EntityNotFoundException("Cliente no encontrado"));

        cliente.setNombre(nuevosDatos.getNombre());
        cliente.setTelefono(nuevosDatos.getTelefono());
        cliente.setCorreo(nuevosDatos.getCorreo());

        if (mesaIds != null) {
            Set<Mesa> nuevasMesas = new HashSet<>(mesaRepository.findAllById(mesaIds));

            for (Mesa mesa : cliente.getMesas()) {
                mesa.getClientes().remove(cliente);
                mesaRepository.save(mesa);
            }

            cliente.setMesas(nuevasMesas);
            for (Mesa mesa : nuevasMesas) {
                mesa.getClientes().add(cliente);
                mesaRepository.save(mesa);
            }
        }

        return clienteRepository.save(cliente);
    }
}
