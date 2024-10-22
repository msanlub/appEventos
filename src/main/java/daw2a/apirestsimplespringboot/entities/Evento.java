package daw2a.apirestsimplespringboot.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;

    @OneToMany(mappedBy = "evento", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Participante> participantes;

    @ManyToOne
    @JoinColumn(name = "organizador_id")
    @JsonBackReference
    //lado no propietario de la relacion, lado inverso de la relacion.Referencia hacia atras (el organizador)
    private Organizador organizador;
}
