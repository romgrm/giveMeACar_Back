package fr.givemeacar.controller;
import fr.givemeacar.model.Agence;
import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.repository.AgenceRepository;
import fr.givemeacar.repository.UtilisateurRepository;
import fr.givemeacar.services.AgenceServiceImpl;
import fr.givemeacar.services.UtilisateurServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping(path = "utilisateur")
@CrossOrigin
public class UtilisateurController {

    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurServiceImpl utilisateurService;

    // Renvoie tous nos utilisateurs

    @GetMapping
    ResponseEntity<List<Utilisateur>> getUtilisateurs() {
        return ResponseEntity.ok(
                utilisateurRepository.findAll());
    }

    // Renvoie un item via son id
    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Utilisateur>> utilisateurById(@PathVariable int id) {
        return ResponseEntity.ok(
                utilisateurRepository.findById(id));
    }

    // Retourne l'agence d'un user
    @GetMapping(value = "{userId}/agence")
    public ResponseEntity<Agence> getAgenceByUser(@PathVariable ("userId") int userId) {
        return ResponseEntity.ok(
                utilisateurService.getAgenceByUserServ(userId));
    }

    // Mettre à jour un item déjà existant
    @PutMapping
    public ResponseEntity<Void> updateUtilisateur(@RequestBody Utilisateur utilisateur) {

        utilisateurRepository.save(utilisateur);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // Changer l'Utilisateur d'Agence
    @PutMapping(path = "/{utilisateurId}/agence/{newAgenceId}")
    public ResponseEntity<Void> moveUtilisateur(@PathVariable("utilisateurId") int utilisateurId, @PathVariable("newAgenceId") int newAgenceId){
        utilisateurService.moveClientServ(utilisateurId, newAgenceId);
        // on return rien vu que c'est <Void> donc on met null
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // Supprimer un item via son Id
    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteUtilisateur(@PathVariable int id) {
        utilisateurRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /*POST Utilisateur*/
    @PostMapping
    public ResponseEntity<Void> postUtilisateur(@RequestBody Utilisateur utilisateur) {
        utilisateurRepository.save(utilisateur);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }
}
