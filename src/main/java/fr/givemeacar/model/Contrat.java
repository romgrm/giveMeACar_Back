package fr.givemeacar.model;
import lombok.Data;

import javax.persistence.*;

@Entity
public @Data class Contrat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int startLocation;
    private int endLocation;
    private int price;

   /* private Vehicule vehicule;
    private Utilisateur client;*/


}
