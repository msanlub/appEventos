package daw2a.apirestsimplespringboot.repositories;

import daw2a.apirestsimplespringboot.entities.Evento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Long> {
    Page<Evento> findEventoByTituloContainingIgnoreCase(String titulo, Pageable pageable);
}
