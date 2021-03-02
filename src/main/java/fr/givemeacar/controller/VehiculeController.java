package fr.givemeacar.controller;

import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.VehiculeRepository;
import fr.givemeacar.services.VehiculeServiceImpl;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController // Controller qui permet de réaliser des requêtes Http CRUD -> Api Rest
@RequestMapping(path = "vehicule")
@CrossOrigin
public class VehiculeController {

    private final VehiculeRepository vehiculeRepository;
    private final VehiculeServiceImpl vehiculeService;

    /*
        GET all vehicules
    */
    @GetMapping
    public ResponseEntity<List<Vehicule>> allVehicule() {
        return ResponseEntity.ok(vehiculeRepository.findAll());
    }

    /*
       GET un vehicule
    */
    @GetMapping(value = "{id}")
    public ResponseEntity<Optional<Vehicule>> vehiculeById(@PathVariable int id) {
        return ResponseEntity.ok()
                .body(vehiculeRepository.findById(id));
    }

    /*
        POST VEHICULE
    */
    @PostMapping
    public ResponseEntity<Void> createVehicule(@RequestBody Vehicule vehicule) {
        vehiculeRepository.save(vehicule);

        return ResponseEntity.created(
                URI.create("/vehicule/" + vehicule.getId()))
                .build();
    }

    /* PUT VEHICULE
        J'ai crée deux fonctions séparées pour gérer les booléens, si on les traite dans "updateVehicule"
        ils seront set sur false par défaut ce qu'on ne veut pas
    */
    @PutMapping(path = "{vehiculeId}")
    public ResponseEntity<Void> updateVehicule(
            @PathVariable("vehiculeId") int vehiculeId,
            @RequestBody Vehicule vehicule)
    {

        vehiculeService.updateVehiculesServ(vehiculeId, vehicule);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping(path = "{vehiculeId}/dispo")
    public ResponseEntity<Void> setDisponibility(
            @PathVariable("vehiculeId") int vehiculeId,
            @RequestParam boolean dispoCheck)
    {

        vehiculeService.updateDispoServ(vehiculeId, dispoCheck);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping(path = "{vehiculeId}/revision")
    public ResponseEntity<Void> setRevision(
            @PathVariable("vehiculeId") int vehiculeId,
            @RequestParam boolean revisionCheck)
    {

        vehiculeService.updateRevisionServ(vehiculeId, revisionCheck);
        return new ResponseEntity<>(null, HttpStatus.OK);

    }

    @PutMapping(path = "{vehiculeId}/agence/{newAgenceId}")
    public ResponseEntity<Void> moveVehicule(
            @PathVariable("vehiculeId") int vehiculeId,
            @PathVariable("newAgenceId") int newAgenceId)
    {
        vehiculeService.moveVehiculeServ(vehiculeId, newAgenceId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    /*
        DELETE VEHICULE
    */
    @DeleteMapping(value="{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable int id){
        vehiculeRepository.deleteById(id);

        return new ResponseEntity<>(null, HttpStatus.OK);

    }

}
