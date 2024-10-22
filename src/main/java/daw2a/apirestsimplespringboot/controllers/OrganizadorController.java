package daw2a.apirestsimplespringboot.controllers;

import daw2a.apirestsimplespringboot.entities.Organizador;
import daw2a.apirestsimplespringboot.repositories.OrganizadorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/organizadores")
public class OrganizadorController {
    @Autowired
    private OrganizadorRepository organizadorRepository;

    @GetMapping
    public Page<Organizador> findOrganizadorByNombreContainingIgnoreCase(@RequestParam String nombre,
                                                                         Pageable pageable) {
        if (nombre == null || nombre.isEmpty()) {
            return organizadorRepository.findOrganizadorByNombreContainingIgnoreCase(nombre,pageable);
        }else{
            return organizadorRepository.findAll(pageable);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Organizador> obtenerOrganizador(@PathVariable Long id){
        return organizadorRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Organizador> crearOrganizador(@RequestBody @Valid Organizador organizador){
        return ResponseEntity.ok(organizadorRepository.save(organizador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Organizador> actualizarOrganizador(@PathVariable Long id,
                                                   @RequestBody Organizador organizadorActualizado){
        return organizadorRepository.findById(id).map(evento -> {
            evento.setNombre(organizadorActualizado.getNombre());
            return ResponseEntity.ok(organizadorRepository.save(evento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarOrganizador(@PathVariable Long id){
        return organizadorRepository.findById(id).map(organizador -> {
            organizadorRepository.delete(organizador);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}

