package eguce3g.bns.reglemetier.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import org.springframework.stereotype.Service;

@Entity
@Service
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Regle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomtable;
    //    @OneToOne
//    @JsonManagedReference
    private String expression;
    private String nomchamp;
    private String valeur;
    // private String condition;
    private String resultat;
}
