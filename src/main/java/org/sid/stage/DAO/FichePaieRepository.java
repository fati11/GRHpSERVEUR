package org.sid.stage.DAO;

import org.sid.stage.entities.Conge;
import org.sid.stage.entities.Employe;
import org.sid.stage.entities.FichePaie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FichePaieRepository extends JpaRepository<FichePaie,Long> {
    @Query("select f from FichePaie f where f.cin like :x")
    public Page<FichePaie> chercher(@Param("x") String mc, Pageable pageable);
    @Query("select f from FichePaie f where f.cin like :x")
    public Page<FichePaie> retournerFichePaieByCin(@Param("x") String cin , Pageable pageable);
}
