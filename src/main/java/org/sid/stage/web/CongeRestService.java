package org.sid.stage.web;

import org.sid.stage.DAO.CongeRepository;
import org.sid.stage.DAO.EmployeRepository;
import org.sid.stage.entities.Conge;
import org.sid.stage.entities.Employe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CongeRestService {
    @Autowired
    private CongeRepository congeRepository;
    @Autowired
    private EmployeRepository employeRepository;
    @RequestMapping(value="/conges" ,method= RequestMethod.GET)
    public List<Conge> getConges()
    {
        return congeRepository.findAll();
    }
    @RequestMapping(value="/conge/{id}",method=RequestMethod.GET)
    public Optional<Conge> getConge(@PathVariable Long id)
    {
        return congeRepository.findById(id);
    }
    @RequestMapping(value="/congePris/{id}",method=RequestMethod.GET)
    public Long getCongePris(@PathVariable Long id)
    {
        return congeRepository.nbrCongePris(id);
    }
    @RequestMapping(value="/DerniercongePris/{id}",method=RequestMethod.GET)
    public Conge getDernierCongePris(@PathVariable Long id )
    {
        Employe e = employeRepository.findById(id).get();
        return congeRepository.retournerDernierConge(e).get(0);
    }

    @RequestMapping(value="/listesConge/{id}",method=RequestMethod.GET)
    public Page<Conge> listesConges(@PathVariable Long id,
                              @RequestParam(name = "page",defaultValue = "0")int page,
                              @RequestParam(name = "size",defaultValue = "5")int size
                              )
    {
        Employe e = employeRepository.findById(id).get();
        return congeRepository.listeConge(e,PageRequest.of(page,size));
    }

    @RequestMapping(value="/conge",method=RequestMethod.POST)
    public Conge save(@RequestBody Conge conge)
    {
       Optional<Employe> employe=employeRepository.findById(conge.getEmploye().getId());
       if(employe.isPresent()){
            int MILLISECONDS_PER_DAY = 1000 * 60 * 60 * 24;
           long delta = conge.getDateFinConge().getTime() - conge.getDateDebutConge().getTime();
           conge.setNbrjourPris((int) ( delta / MILLISECONDS_PER_DAY));
           conge.setEmploye(employe.get());
       }
        return congeRepository.save(conge);
    }
    @RequestMapping(value="/updateConge/{id}",method=RequestMethod.PUT)
    public Conge updateConge(@PathVariable Long id,@RequestBody Conge conge)
    {
        conge.setId(id);
        return congeRepository.save(conge);
    }
    @RequestMapping(value="/chercherConges",method=RequestMethod.GET)
    public Page<Conge> chercher(@RequestParam(name = "mc",defaultValue = "") String mc,
                                  @RequestParam(name = "page",defaultValue = "0")int page,
                                  @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return congeRepository.chercher("%"+mc+"%", PageRequest.of(page,size) );

    }
    @RequestMapping(value="/chercherCongesNV",method=RequestMethod.GET)
    public Page<Conge> chercherCongeNV(@RequestParam(name = "page",defaultValue = "0")int page,
                                       @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return congeRepository.chercherCongeNV(PageRequest.of(page,size));

    }
    @RequestMapping(value ="/conge/{id}",method = RequestMethod.DELETE)
    public boolean supprimer(@PathVariable Long id){
        congeRepository.deleteById(id);
        return true;
    }
}
