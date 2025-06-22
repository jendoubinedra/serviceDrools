package eguce3g.bns.reglemetier.service;

import eguce3g.bns.reglemetier.model.Regle;
import eguce3g.bns.reglemetier.repository.RegleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegleService {
    private final RegleRepository regleRepository;
    private  final DroolsManager droolsManager;
    public Regle ajouterRegle(Regle regle) {

        Regle regleEnregistree = regleRepository.save(regle);
        // 1. Régénérer le fichier de règles Drools avec toutes les règles actives
        droolsManager.regenererEtRecharger();
        return regleEnregistree;
    }
    public void supprimerRegle(Long id) {
        if (!regleRepository.existsById(id)) {
            throw new RuntimeException(" Règle introuvable avec l'ID : " + id);
        }
        regleRepository.deleteById(id);
        try {
            droolsManager.regenererEtRecharger();
            System.out.println("✅ Fichier .drl mis à jour après suppression.");
        } catch (Exception e) {
            throw new RuntimeException(" Erreur lors de la mise à jour du fichier .drl après suppression", e);
        }
    }


    public Regle update(Long id, Regle updatedRegle) {
        Regle existing = regleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Règle introuvable avec l'ID : " + id));

        existing.setNomtable(updatedRegle.getNomtable());
        existing.setNomchamp(updatedRegle.getNomchamp());
        // existing.setExpression(updatedRegle.getExpression());
        existing.setValeur(updatedRegle.getValeur());
        // existing.setCondition(updatedRegle.getCondition());
        existing.setResultat(updatedRegle.getResultat());

        Regle saved = regleRepository.save(existing);
        droolsManager.regenererEtRecharger(); // Regénérer le fichier .drl
        return saved;
    }

    public List<Regle> getAllRegles() {
        return regleRepository.findAll();
    }

    public Regle getRegleById(Long id) {
        return regleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Règle non trouvée"));
    }
}
