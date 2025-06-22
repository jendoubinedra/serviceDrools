package eguce3g.bns.reglemetier.model;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Marchandise {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double montant;
    private double poids;
    private String origine;
    private String destination;
    private String procedure;
    private Long valeurFOB;
    private int quantite;
    private String codeTarif;
    private String resultat;

    public Marchandise(double montant) {
        this.montant = montant;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }
}

