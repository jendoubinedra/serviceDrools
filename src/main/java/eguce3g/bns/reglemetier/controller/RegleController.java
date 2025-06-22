package eguce3g.bns.reglemetier.controller;



import eguce3g.bns.reglemetier.model.Regle;
import eguce3g.bns.reglemetier.service.DroolsService;
import eguce3g.bns.reglemetier.service.RegleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RegleController {

   private  final RegleService regleService;
  private final DroolsService droolsService;

    @PostMapping("/addregles")
    public ResponseEntity<Regle> ajouterRegle(@RequestBody Regle nouvelleRegle) {
        Regle regleEnregistree = regleService.ajouterRegle(nouvelleRegle);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(regleEnregistree);
    }
    @GetMapping("/regle/{id}")
    public ResponseEntity<Regle> getRegleById(@PathVariable Long id) {
        Regle regle = regleService.getRegleById(id);
        return ResponseEntity.ok(regle);
    }

    @DeleteMapping("deleteRegle/{id}")
    public ResponseEntity<?> supprimer(@PathVariable Long id) {
        regleService.supprimerRegle(id);
        Map<String, String> response = new HashMap<>();
        response.put("message", " Règle supprimée avec succès.");
        return ResponseEntity.ok(response);
    }

    @PutMapping("updateRegle/{id}")
    public ResponseEntity<Regle> update(@PathVariable Long id, @RequestBody Regle regle) {
        Regle updated = regleService.update(id, regle);
        return ResponseEntity.ok(updated);
    }
    @GetMapping("/allRegles")
    public List<Regle> getAllRegles() {
        return regleService.getAllRegles();
    }
    @PostMapping("/evaluer")
    public ResponseEntity<String> evaluerRègle(@RequestBody Map<String, Object> input) {
        try {
            double montant = Double.parseDouble(input.get("montant").toString());
            String resultat = droolsService.évaluerEtSauvegarder(montant);
            return ResponseEntity.ok(resultat);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Erreur lors de l’évaluation : " + e.getMessage());
        }
    }

}
