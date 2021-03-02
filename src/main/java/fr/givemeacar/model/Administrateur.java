package fr.givemeacar.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Administrateur extends Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;





     /*CREATION DES AGENCES & MANAGERS

    public Agence createAgencyAccount(){
        return null;
    }

    public Manager createManagerAccount(){
        return null;
    }*/

}
