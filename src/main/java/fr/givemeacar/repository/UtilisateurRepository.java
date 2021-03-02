package fr.givemeacar.repository;

import fr.givemeacar.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Integer> {

     Optional<List<Utilisateur>> findByAgenceId(int agenceId);
}
