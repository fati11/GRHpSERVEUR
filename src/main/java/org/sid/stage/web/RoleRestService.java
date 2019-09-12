package org.sid.stage.web;

import org.sid.stage.DAO.EmployeRepository;
import org.sid.stage.DAO.RoleRepository;
import org.sid.stage.entities.Employe;
import org.sid.stage.entities.FichePaie;
import org.sid.stage.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@RestController
public class RoleRestService {
    @Autowired
    public RoleRepository roleRepository;
    @Autowired
    public EmployeRepository employeRepository;
    @RequestMapping(value="/rola",method= RequestMethod.POST)
    public Role save(@RequestBody Role role)
    {
        return roleRepository.save(role);
    }
    @RequestMapping(value ="/role/{id}",method = RequestMethod.GET)
    public Role roleById(@PathVariable Long id)
    {
       return roleRepository.roleById(id);
    }
}
