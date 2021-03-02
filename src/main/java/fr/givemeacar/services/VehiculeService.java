package fr.givemeacar.services;

import fr.givemeacar.model.Vehicule;

public interface VehiculeService {


    void updateVehiculesServ(int vehiculeId,
                             Vehicule vehicule);

    void updateDispoServ(int vehiculeId,
                         boolean isAvailable);

    void updateRevisionServ(int vehiculeId,
                            boolean inRevision);

}
