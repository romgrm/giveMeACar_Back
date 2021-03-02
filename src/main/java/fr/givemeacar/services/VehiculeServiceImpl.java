package fr.givemeacar.services;

import fr.givemeacar.model.Agence;
import fr.givemeacar.model.Vehicule;
import fr.givemeacar.repository.AgenceRepository;
import fr.givemeacar.repository.VehiculeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@AllArgsConstructor
public class VehiculeServiceImpl implements VehiculeService{

    private final AgenceRepository agenceRepository;
    private final VehiculeRepository vehiculeRepository;
    private final AgenceServiceImpl agenceService;


    @Override
//    @Transactional
    public void updateVehiculesServ(int vehiculeId,
                                    Vehicule vehicule) {


        Vehicule currentVehicule = agenceService.checkVehicule(vehiculeId);


        if (vehicule.getMarque() != null
                && vehicule.getMarque().length() > 0) {
            currentVehicule.setMarque(vehicule.getMarque());
        }

        if (vehicule.getModele() != null
                && vehicule.getModele().length() > 0) {
            currentVehicule.setModele(vehicule.getModele());
        }

        if (vehicule.getPrice() > 0) {
            currentVehicule.setPrice(vehicule.getPrice());
        }

        if (vehicule.getCoordonneesGPS() != 0) {
            currentVehicule.setCoordonneesGPS(vehicule.getCoordonneesGPS());
        }

        vehiculeRepository.save(currentVehicule);

    }

    public void updateDispoServ(int vehiculeId,
                                boolean isAvailable) {

        Vehicule currentVehicule = agenceService.checkVehicule(vehiculeId);
        Agence currentAgence;

//Si vehicule par en revision on peut le mettre en dispo, sinon erreur "en revision"
        if(!currentVehicule.isInRevision()) {
            if(currentVehicule.getAgence() != null) {
                //Si vehicule dans une agence, on récup l'agence
                currentAgence = agenceRepository.findById(currentVehicule.getAgence().getId())
                        .orElse(new Agence());
                //Si changement dispo, la liste de dispo de l'agence est màj
                if(isAvailable != currentVehicule.isAvailable()) {
                    if(isAvailable) {
                        currentAgence.setVehiculeDispo(currentAgence.getVehiculeDispo()+1);
                    } else {
                        currentAgence.setVehiculeDispo(currentAgence.getVehiculeDispo()-1);
                    }
                }
                currentVehicule.setAvailable(isAvailable);
                agenceRepository.save(currentAgence);
            }

            vehiculeRepository.save(currentVehicule);
        } else
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Le véhicule ne peut pas être dispo tant qu'il est en révision");
    }

    public void updateRevisionServ(int vehiculeId,
                                   boolean inRevision) {

        Vehicule currentVehicule = agenceService.checkVehicule(vehiculeId);
        Agence currentAgence;

        if(currentVehicule.getAgence() != null) {
            //Si vehicule dans une agence, on récup l'agence
            currentAgence = agenceRepository.findById(currentVehicule.getAgence().getId())
                    .orElse(new Agence());
            //Si changement dispo, la liste de dispo de l'agence est màj
            if(inRevision != currentVehicule.isInRevision()) {
                if(inRevision) {
                    currentAgence.setVehiculeRevision(currentAgence.getVehiculeRevision()+1);
                    if(currentVehicule.isAvailable()) {
                        currentVehicule.setAvailable(false);
                        currentAgence.setVehiculeDispo(currentAgence.getVehiculeDispo()-1);
                    }
                } else {
                    currentAgence.setVehiculeRevision(currentAgence.getVehiculeRevision()-1);
                }
            }
            agenceRepository.save(currentAgence);
        }

        currentVehicule.setInRevision(inRevision);
        if(inRevision) currentVehicule.setAvailable(false);
        vehiculeRepository.save(currentVehicule);
    }


    public void moveVehiculeServ(int vehiculeId , int newAgenceId){

        Agence currentAgence = agenceService.checkAgence(newAgenceId);
        Vehicule currentVehicule = agenceService.checkVehicule(vehiculeId);

        // Verifier dans quelle agence est le vehicule: si son agence Id = 0 -> not_found
        // Si il a une agence, on vire l'agence (agenceId dans sql) et on le remplace par newAgenceId , comme ça il a une nouvelle Agence

        currentVehicule.setAgence(currentAgence);
        vehiculeRepository.save(currentVehicule);

    }

}
