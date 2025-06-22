package eguce3g.bns.reglemetier.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegleDto {
    private String nomtable;
    private String nomchamp;
    private String valeur;
    private String idexpresion;
    private String resultat;
}