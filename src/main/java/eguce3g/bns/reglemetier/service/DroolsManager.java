package eguce3g.bns.reglemetier.service;

import eguce3g.bns.reglemetier.model.Regle;
import eguce3g.bns.reglemetier.repository.RegleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
@Component
@RequiredArgsConstructor
public class DroolsManager {


    private  final  RegleRepository regleRepository;
    private static final String RULES_FILE = "D:/backup/e-Guce/regle-metier/src/main/resources/rules/decisions.drl";


    public void regenererEtRecharger() {
        try {
            Path chemin = Paths.get(RULES_FILE);

            // Assure-toi que le dossier "rules" existe
            if (!Files.exists(chemin.getParent())) {
                Files.createDirectories(chemin.getParent());
            }

            // Génération du contenu .drl
            List<Regle> regles = regleRepository.findAll();
            StringBuilder drlContent = new StringBuilder("package rules;\n\n");
            drlContent.append("import eguce3g.bns.reglemetier.model.Marchandise;\n\n");

            int i = 1;
            for (Regle r : regles) {
                drlContent.append("rule \"Règle ").append(i++).append("\"\n")
                        .append("when\n")
                        .append("    $d : Marchandise(")
                        .append(r.getNomchamp()).append(" ")
                        .append(r.getExpression()).append(" ")
                        .append(r.getValeur()).append(")\n")
                        .append("then\n")
                        .append("    $d.setResultat(\"").append(r.getResultat()).append("\");\n")
                        .append("end\n\n");
            }
            System.out.println("🧾 Contenu du fichier .drl généré :");
            System.out.println(drlContent.toString());
            Files.write(chemin, drlContent.toString().getBytes(),
                    StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

            System.out.println(" Fichier .drl mis à jour avec succès !");
            System.out.println("Chemin absolu : " + chemin.toAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(" Erreur lors de la génération du fichier .drl", e);
        }
    }
}

