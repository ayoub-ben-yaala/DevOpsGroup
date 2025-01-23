package tn.esprit.tpfoyer.control;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpfoyer.entity.Etudiant;
import tn.esprit.tpfoyer.service.IEtudiantService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/etudiant")
public class EtudiantRestController {

    private final IEtudiantService etudiantService; // Injection par constructeur

    /**
     * Récupère tous les étudiants.
     */
    @GetMapping("/retrieve-all-etudiants")
    public ResponseEntity<List<Etudiant>> getEtudiants() {
        List<Etudiant> listEtudiants = etudiantService.retrieveAllEtudiants();
        return ResponseEntity.ok().body(listEtudiants);  // Retour avec code HTTP 200
    }

    /**
     * Récupère un étudiant par son CIN.
     */
    @GetMapping("/retrieve-etudiant-cin/{cin}")
    public ResponseEntity<Etudiant> retrieveEtudiantParCin(@PathVariable("cin") Long cin) {
        Etudiant etudiant = etudiantService.recupererEtudiantParCin(cin);
        return etudiant != null ? ResponseEntity.ok(etudiant) : ResponseEntity.notFound().build();  // Retour avec code HTTP 200 ou 404 si non trouvé
    }

    /**
     * Récupère un étudiant par son ID.
     */
    @GetMapping("/retrieve-etudiant/{etudiant-id}")
    public ResponseEntity<Etudiant> retrieveEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        Etudiant etudiant = etudiantService.retrieveEtudiant(etudiantId);
        return etudiant != null ? ResponseEntity.ok(etudiant) : ResponseEntity.notFound().build();  // Retour avec code HTTP 200 ou 404 si non trouvé
    }

    /**
     * Ajoute un nouvel étudiant.
     */
    @PostMapping("/add-etudiant")
    public ResponseEntity<Etudiant> addEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant savedEtudiant = etudiantService.addEtudiant(etudiant);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEtudiant);  // Retour avec code HTTP 201 pour une création réussie
    }

    /**
     * Supprime un étudiant par son ID.
     */
    @DeleteMapping("/remove-etudiant/{etudiant-id}")
    public ResponseEntity<Void> removeEtudiant(@PathVariable("etudiant-id") Long etudiantId) {
        etudiantService.removeEtudiant(etudiantId);
        return ResponseEntity.ok().build();  // Retour avec code HTTP 200
    }

    /**
     * Modifie un étudiant existant.
     */
    @PutMapping("/modify-etudiant")
    public ResponseEntity<Etudiant> modifyEtudiant(@RequestBody Etudiant etudiant) {
        Etudiant updatedEtudiant = etudiantService.modifyEtudiant(etudiant);
        return updatedEtudiant != null ? ResponseEntity.ok(updatedEtudiant) : ResponseEntity.notFound().build();  // Retour avec code HTTP 200 ou 404 si non trouvé
    }
}
