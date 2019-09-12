package org.sid.stage.web;

import org.sid.stage.DAO.CongeRepository;
import org.sid.stage.DAO.EmployeRepository;
import org.sid.stage.DAO.FichePaieRepository;
import org.sid.stage.entities.Conge;
import org.sid.stage.entities.Employe;
import org.sid.stage.entities.FichePaie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
public class FichePaieRestService {
    @Autowired
    private FichePaieRepository fichePaieRepository;
    @RequestMapping(value="/fichePaies",method=RequestMethod.GET)
    public Page<FichePaie> chercher(@RequestParam(name = "mc",defaultValue = "") String mc,
                                  @RequestParam(name = "page",defaultValue = "0")int page,
                                  @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return fichePaieRepository.chercher("%"+mc+"%", PageRequest.of(page,size) );

    }
    /*@RequestMapping(value="/fichePaies" ,method= RequestMethod.GET)
    public List<FichePaie> getFichePaie()
    {
        return fichePaieRepository.findAll();
    }*/
    @RequestMapping(value="/fichePaie/{id}",method=RequestMethod.GET)
    public Optional<FichePaie> getFichePaie(@PathVariable Long id)
    {
        return fichePaieRepository.findById(id);
    }
    @RequestMapping(value="/fichePaie",method=RequestMethod.POST)
    public FichePaie save(@RequestBody FichePaie fichePaie)
    {
        return fichePaieRepository.save(fichePaie);
    }
    @RequestMapping(value="/updateFichePaie/{id}",method=RequestMethod.PUT)
    public FichePaie updateFichePaie(@PathVariable Long id,@RequestBody FichePaie fichePaie)
    {
        fichePaie.setId(id);
        return fichePaieRepository.save(fichePaie);
    }
    @RequestMapping(value ="/fichePaie/{id}",method = RequestMethod.DELETE)
    public boolean supprimer(@PathVariable Long id){
        fichePaieRepository.deleteById(id);
        return true;
    }
    @RequestMapping(value ="/ficheByCin" , method=RequestMethod.GET)
    public Page<FichePaie> ficheByCin(@RequestParam(name = "cin",defaultValue = "") String cin,
                                      @RequestParam(name = "page",defaultValue = "0")int page,
                                      @RequestParam(name = "size",defaultValue = "5")int size)
                                      {
        return fichePaieRepository.retournerFichePaieByCin(cin,PageRequest.of(page,size));
    }
}
