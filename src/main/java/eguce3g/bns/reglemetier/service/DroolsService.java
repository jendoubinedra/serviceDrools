package eguce3g.bns.reglemetier.service;

import eguce3g.bns.reglemetier.model.Marchandise;
import lombok.RequiredArgsConstructor;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DroolsService {


    private final KieContainer kieContainer;


    public String évaluerEtSauvegarder(double montant) {
        KieSession kieSession = kieContainer.newKieSession("session-drools");
        Marchandise d = new Marchandise(montant);
        kieSession.insert(d);
        kieSession.fireAllRules();
        kieSession.dispose();
        return  d.getResultat();
    }
}