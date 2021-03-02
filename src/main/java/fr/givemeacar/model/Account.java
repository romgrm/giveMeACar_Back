package fr.givemeacar.model;

import lombok.Data;

import javax.persistence.*;


@MappedSuperclass // permet de dire à JPA que ce n'est pas une @Entity mais une classe Parent/Modèle, récupère les données pour les classes enfants (Permet à Spring de gérer l'héritage)
public @Data class Account {

    private String mail;
    private String password;
    private String pseudo;
    private String name;
    private String firstName;
    private String role;

}
