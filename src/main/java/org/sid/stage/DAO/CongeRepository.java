package org.sid.stage.DAO;

import org.sid.stage.entities.Conge;
import org.sid.stage.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CongeRepository extends JpaRepository<Conge,Long> {
    @Query("select c from Conge c where c.motif like :x")
    public Page<Conge> chercher(@Param("x") String mc, Pageable pageable);
    @Query("select SUM (c.nbrjourPris) from Conge c  where c.employe.id=:x")
    public Long nbrCongePris(@Param("x") Long id_employe);
    @Query("select c from Conge c where c.etatConge like 'NonValide'")
    public Page<Conge> chercherCongeNV(Pageable pageable);
    @Query(value="SELECT c FROM Conge c where c.employe=:x group by c.id order by c.id desc ")
    public List<Conge> retournerDernierConge(@Param("x") Employe e);
    @Query("select c from Conge c where c.employe=:x order by c.dateFinConge asc ")
    public Page<Conge> listeConge(@Param("x") Employe e,Pageable pageable);
}
