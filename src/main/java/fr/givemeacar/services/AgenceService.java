package fr.givemeacar.services;

import fr.givemeacar.model.Agence;
import fr.givemeacar.model.Utilisateur;
import fr.givemeacar.model.Vehicule;

import java.util.List;

public interface AgenceService {


    // On créer une fonction qui va permettre de donner le stock par agence
    List<Vehicule> getStockVehiculesServ(int id);

    List<Utilisateur> getListClientsServ(int id);

    void addVehiculeToAgencyServ(int agenceId, int vehiculeId);

    void addClientToAgencyServ(int agenceId, int clientele);

    void deleteClientFromAgencyServ(int agenceId, int clientId);

    void deleteVehiculeFromAgencyServ(int agenceId, int vehiculeId);

    void createAgency(Agence agence);

}
