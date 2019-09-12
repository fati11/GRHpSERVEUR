package org.sid.stage.DAO;

import org.sid.stage.entities.Conge;
import org.sid.stage.entities.Employe;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EmployeRepository extends JpaRepository<Employe,Long> {
    @Query("select e from Employe e where e.nom like :x")
    public Page<Employe> chercher(@Param("x") String mc, Pageable pageable);
    @Query("select e from Employe e where e.username like :x")
    public Employe chercherEmploye(@Param("x") String login);
    Employe findByUsername(String  username);
    @Query("select e.cin from Employe e where e.cin like :mc")
    public List<String> search(@Param("mc") String kw);
    @Query("select e from Employe e where e.cin like :x")
    public Employe employeByCin(@Param("x") String cin);
}
