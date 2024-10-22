package daw2a.apirestsimplespringboot.controllers;


import daw2a.apirestsimplespringboot.entities.Participante;
import daw2a.apirestsimplespringboot.repositories.ParticipanteRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/participantes")
public class ParticipanteController {
    @Autowired
    private ParticipanteRepository participanteRepository;

    @GetMapping
    public Page<Participante> findParticipantesByEmailContainingIgnoreCase(@RequestParam String email,
                                                                           Pageable pageable) {
        if (email == null || email.isEmpty()) {
            return participanteRepository.findParticipantesByEmailContainingIgnoreCase(email,pageable);
        }else{
            return participanteRepository.findAll(pageable);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Participante> obtenerParticipante(@PathVariable Long id){
        return participanteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Participante> crearParticipante(@RequestBody @Valid Participante evento){
        return ResponseEntity.ok(participanteRepository.save(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Participante> actualizarParticipante(@PathVariable Long id,
                                                   @RequestBody Participante participanteActualizado){
        return participanteRepository.findById(id).map(participante -> {
            participante.setNombre(participanteActualizado.getNombre());
            participante.setEmail(participanteActualizado.getEmail());
            return ResponseEntity.ok(participanteRepository.save(participante));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarParticipante(@PathVariable Long id){
        return participanteRepository.findById(id).map(participante -> {
            participanteRepository.delete(participante);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}


