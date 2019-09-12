package org.sid.stage.web;

import org.sid.stage.DAO.EmployeRepository;
import org.sid.stage.DAO.FormationRepository;
import org.sid.stage.entities.Conge;
import org.sid.stage.entities.Employe;
import org.sid.stage.entities.Formation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class FormationRestService  {
    @Autowired
    private FormationRepository formationRepository;
    @Autowired
    private EmployeRepository employeRepository;
    @RequestMapping(value="/formations" ,method= RequestMethod.GET)
    public List<Formation> getFormations()
    {
        return formationRepository.findAll();
    }
    @RequestMapping(value="/formation/{id}",method=RequestMethod.GET)
    public Optional<Formation> getFormation(@PathVariable Long id)
    {
        return formationRepository.findById(id);
    }

    @RequestMapping(value="/DernierFormationLance/{id}",method=RequestMethod.GET)
    public Formation getDernierFormationLance(@PathVariable Long id) {
        Employe e = employeRepository.findById(id).get();
        // return formationRepository.retournerDernierFormation(e).get(0);
        return formationRepository.retournerDernierFormation(e).get(0);
    }
    @RequestMapping(value="/formation",method=RequestMethod.POST)
    public Formation save(@RequestBody Formation formation)
    {
        Optional<Employe> employe=employeRepository.findById(formation.getEmploye().getId());
        if(employe.isPresent()){
            formation.setEmploye(employe.get());
        }
        return formationRepository.save(formation);
    }

    @RequestMapping(value="/listesFormations/{id}",method=RequestMethod.GET)
    public Page<Formation> listesFormations(@PathVariable Long id,
                                    @RequestParam(name = "page",defaultValue = "0")int page,
                                    @RequestParam(name = "size",defaultValue = "5")int size
    )
    {
        Employe e = employeRepository.findById(id).get();
        return formationRepository.listesFormations(e,PageRequest.of(page,size));
    }

    @RequestMapping(value="/updateFormation/{id}",method=RequestMethod.PUT)
    public Formation updateFormation(@PathVariable Long id,@RequestBody Formation formation)
    {
        formation.setId(id);
        return formationRepository.save(formation);
    }
    @RequestMapping(value="/chercherFormation",method=RequestMethod.GET)
    public Page<Formation> chercher(@RequestParam(name = "mc",defaultValue = "") String mc,
                                @RequestParam(name = "page",defaultValue = "0")int page,
                                @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return formationRepository.chercher("%"+mc+"%", PageRequest.of(page,size) );

    }
    @RequestMapping(value="/chercherFormationNV",method=RequestMethod.GET)
    public Page<Formation> chercherFormationNV(@RequestParam(name = "page",defaultValue = "0")int page,
                                       @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return formationRepository.chercherFormationNV(PageRequest.of(page,size));

    }
    @RequestMapping(value ="/formation/{id}",method = RequestMethod.DELETE)
    public boolean supprimer(@PathVariable Long id){
        formationRepository.deleteById(id);
        return true;
    }
}
