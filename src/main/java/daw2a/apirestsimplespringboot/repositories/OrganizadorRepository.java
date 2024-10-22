package daw2a.apirestsimplespringboot.repositories;

import daw2a.apirestsimplespringboot.entities.Organizador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrganizadorRepository extends JpaRepository<Organizador, Long> {
    Page<Organizador> findOrganizadorByNombreContainingIgnoreCase(String nombre, Pageable pageable);
}
