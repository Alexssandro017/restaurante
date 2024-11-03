package mx.edu.utez.restaurantes.repository;

import mx.edu.utez.restaurantes.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    @Query("SELECT c FROM Cliente c JOIN c.mesas m WHERE m.id = :mesaId")
    List<Cliente> findClientesByMesaId(@Param("mesaId") Long mesaId);
}
