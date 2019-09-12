package org.sid.stage.DAO;

import org.sid.stage.entities.Conge;
import org.sid.stage.entities.Employe;
import org.sid.stage.entities.Formation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FormationRepository extends JpaRepository<Formation,Long> {
    @Query("select f from Formation f where f.sujetFormation like :x")
    public Page<Formation> chercher(@Param("x") String mc, Pageable pageable);
    @Query("select f from Formation f where f.statut like 'NonValide'")
    public Page<Formation> chercherFormationNV(Pageable pageable);
    @Query("SELECT f FROM Formation f where f.employe=:x order by f.id desc ")
    public List<Formation> retournerDernierFormation(@Param("x")Employe e);
    @Query("select f from Formation f where f.employe=:x order by f.dateDebut asc ")
    public Page<Formation> listesFormations(@Param("x") Employe e,Pageable pageable);
}
