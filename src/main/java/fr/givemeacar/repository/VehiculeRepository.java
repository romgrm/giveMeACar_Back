package fr.givemeacar.repository;

import fr.givemeacar.model.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiculeRepository extends JpaRepository<Vehicule,Integer>{

    //On ajoute une nouvelle fonction qui va nous retourner le stock d'une agence par son @Id
    //Optional signifie que la fonction peut rendre une valeur null
    Optional<List<Vehicule>> findByAgenceId(int agenceId);

}
