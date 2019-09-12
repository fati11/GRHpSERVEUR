package org.sid.stage.DAO;

import org.sid.stage.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select r from Role r where r.employee.id=:x")
    public Role roleById(@Param("x") Long id);
 }
