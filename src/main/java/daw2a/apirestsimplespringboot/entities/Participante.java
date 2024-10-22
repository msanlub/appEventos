package daw2a.apirestsimplespringboot.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Participante {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String email;

    @ManyToOne
    @JoinColumn(name = "evento_id")
    @JsonBackReference
    //lado no propietario de la relacion, lado inverso de la relacion.Referencia hacia atras (el organizador)
    private Evento evento;
}
