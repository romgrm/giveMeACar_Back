package fr.givemeacar.services;


import fr.givemeacar.model.Agence;
import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.repository.UtilisateurRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UtilisateurServiceImpl implements UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;
    private final AgenceServiceImpl agenceService;


    public void moveClientServ(int utilisateurId, int newAgencyId){
        // je check si mon agence existe avec l'id entré en params
        Agence currentAgence = agenceService.checkAgence(newAgencyId);
        // je check si mon utilisateur existe avec l'id entré en params
        Utilisateur currentUtilisateur = agenceService.checkClient(utilisateurId);

        // Si il n'existe pas, une error sera affichée grâce a AgenceServiceImpl donc pas besoin d'en refaire une ici

        // Si c'est bon, j'ajoute à mon utilisateur l'agence récupérée, donc mon Utilisateur aura un nouvel ID d'agence
        currentUtilisateur.setAgence(currentAgence);
        //Vu qu'on travaille que sur une itération, il faut enregistrer dans la BDD cet utilisateur avec la nouvelle agence grâce à JPA qui connait la function .save()
        utilisateurRepository.save(currentUtilisateur);
    }

    public Agence getAgenceByUserServ(int userId) {
        Utilisateur currentUtilisateur = agenceService.checkClient(userId);

        return currentUtilisateur.getAgence();

    }
}
