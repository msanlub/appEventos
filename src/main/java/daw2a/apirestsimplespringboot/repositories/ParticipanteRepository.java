package daw2a.apirestsimplespringboot.repositories;

import daw2a.apirestsimplespringboot.entities.Participante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipanteRepository extends JpaRepository<Participante, Long> {
    Page<Participante> findParticipantesByEmailContainingIgnoreCase(String email, Pageable pageable);
}
