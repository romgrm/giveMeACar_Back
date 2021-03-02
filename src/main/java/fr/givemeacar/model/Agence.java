package fr.givemeacar.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

//import fr.givemeacar.model.Vehicule;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity // table sql
public class Agence {


    // Id auto-incrémenté
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique=true)
    private String name;
    private String localisation;

    private int nombreVehicules;
    private int vehiculeDispo;
    private int vehiculeRevision;

    @OneToMany(mappedBy = "agence")
    @JsonManagedReference
    @JsonIgnore
    private List<Vehicule> stockVehicules; //todo

    @OneToMany(mappedBy = "agence")
    @JsonManagedReference
    @JsonIgnore
    private List<Utilisateur> clientele;



 /* METHODS
    public void ajouterVehicule(){

    }

    public void supprimerVehicule(){

    }*/
}
