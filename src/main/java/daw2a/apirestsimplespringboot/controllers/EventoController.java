package daw2a.apirestsimplespringboot.controllers;

import daw2a.apirestsimplespringboot.entities.Evento;
import daw2a.apirestsimplespringboot.repositories.EventoRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    @Autowired
    private EventoRepository eventoRepository;

    @GetMapping
    public Page<Evento> findEventoByTituloContainingIgnoreCase(@RequestParam String titulo,
                                                               Pageable pageable) {
        if (titulo == null || titulo.isEmpty()) {
            return eventoRepository.findEventoByTituloContainingIgnoreCase(titulo,pageable);
        }else{
            return eventoRepository.findAll(pageable);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Evento> obtenerEvento(@PathVariable Long id){
        return eventoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Evento> crearEvento(@RequestBody @Valid Evento evento){
        return ResponseEntity.ok(eventoRepository.save(evento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Evento> actualizarEvento(@PathVariable Long id,
                                                 @RequestBody Evento eventoActualizado){
        return eventoRepository.findById(id).map(evento -> {
            evento.setTitulo(eventoActualizado.getTitulo());
            return ResponseEntity.ok(eventoRepository.save(evento));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity eliminarEvento(@PathVariable Long id){
        return eventoRepository.findById(id).map(evento -> {
            eventoRepository.delete(evento);
            return ResponseEntity.noContent().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
