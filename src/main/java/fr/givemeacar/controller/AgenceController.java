package fr.givemeacar.controller;

import fr.givemeacar.model.Agence;
import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.AgenceRepository;
import fr.givemeacar.services.AgenceServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.server.ResponseStatusException;


@RestController // Controller qui permet de réaliser des requêtes Http CRUD -> Api Rest
@AllArgsConstructor
@RequestMapping(path = "agence")
@CrossOrigin
public class AgenceController {

    private final AgenceRepository agenceRepository;
    private final AgenceServiceImpl agenceService;

    /*
        GET all agences
    */
    @GetMapping
    ResponseEntity<List<Agence>> getAllAgencies() {
        return ResponseEntity.ok().body(
                agenceRepository.findAll()
        );
    }

    /*
        GET une agence par son Id
    */
    @GetMapping(value="{id}")
    public ResponseEntity<Agence> getAgencyById(@PathVariable int id){
        return ResponseEntity.ok().body(
                agenceRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT))
        );
    }

    /*
      GET liste vehicules d'une agence
    */
    @GetMapping(value = "{agenceId}/vehicules/")
    public ResponseEntity<List<Vehicule>> getAllVehiculeByAgency(
            @PathVariable(value="agenceId") int agenceId) {

        return ResponseEntity.ok().body(
                agenceService.getStockVehiculesServ(agenceId)
        );
    }

    /*
        GET liste clients/utilisateurs d'une agence
    */
    @GetMapping(value = "{agenceId}/utilisateurs/")
    public ResponseEntity<List<Utilisateur>> getAllClientsByAgency(
            @PathVariable(value="agenceId") int agenceId) {

        return ResponseEntity.ok().body(
                agenceService.getListClientsServ(agenceId)
        );
    }

    /*
        POST agence
    */
    @PostMapping
    public ResponseEntity<Void> createAgency(
            @RequestBody Agence agence) {

        agenceService.createAgency(agence);

        return ResponseEntity.created(
                URI.create("/agence/" + agence.getId()))
                .build();
    }

    /*
        POST - Ajouter vehicule à agence
    */
    @PostMapping(value="{agenceId}/vehicule/{vehiculeId}")
    public ResponseEntity<Void> addVehiculeToAgency(@PathVariable("agenceId") int agenceId,
                                                    @PathVariable("vehiculeId") int vehiculeId) {

        agenceService.addVehiculeToAgencyServ(agenceId, vehiculeId);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    /*
        POST - Ajouter client à agence
    */
    @PostMapping(value="{agenceId}/client/{clientId}")
    public ResponseEntity<Void> addClientToAgency(@PathVariable("agenceId") int agenceId,
                                                  @PathVariable("clientId") int clientId) {

        agenceService.addClientToAgencyServ(agenceId, clientId);
        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }

    /*
        PUT - Mettre à jour agence
    */
    @PutMapping
    public ResponseEntity<Void> updateAgency(
            @RequestBody Agence agence) {

        agenceRepository.save(agence);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /*
        Supprimer agence
    */
    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> deleteAgency(@PathVariable int id){

        agenceRepository.deleteById(id);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /*
        DELETE client d'une agence
    */
    @DeleteMapping(value="{agenceId}/client/{clientId}")
    public ResponseEntity<Void> deleteClientFromAgency(@PathVariable("agenceId") int agenceId,
                                                       @PathVariable("clientId") int clientId) {

        agenceService.deleteClientFromAgencyServ(agenceId, clientId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /*
        DELETE vehicule du stock d'une agence
    */
    @DeleteMapping(value="{agenceId}/vehicule/{vehiculeId}")
    public ResponseEntity<Void> deleteVehiculeFromAgency(@PathVariable("agenceId") int agenceId,
                                                         @PathVariable("vehiculeId") int vehiculeId) {
        agenceService.deleteVehiculeFromAgencyServ(agenceId, vehiculeId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
