package mx.edu.utez.restaurantes.repository;

import mx.edu.utez.restaurantes.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    // Aquí puedes agregar métodos adicionales según tus necesidades
}
