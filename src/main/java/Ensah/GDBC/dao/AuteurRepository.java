package Ensah.GDBC.dao;


import Ensah.GDBC.models.Auteur;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AuteurRepository extends JpaRepository<Auteur,Long> {
}
