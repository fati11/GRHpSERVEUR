package org.sid.stage.web;

import org.sid.stage.DAO.EmployeRepository;
import org.sid.stage.entities.Employe;
import org.sid.stage.storage.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmployeRestService {
    @Autowired
    private EmployeRepository employeRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @RequestMapping(value="/employees" ,method= RequestMethod.GET)
    public List<Employe> getEmployees()
    {
        return employeRepository.findAll();
    }
    @RequestMapping(value ="/cinEmploye" , method=RequestMethod.GET)
    public ResponseEntity<List<String>> getcinEmployees(@RequestParam(name = "mc",defaultValue = "") String mc)
    {
        return new ResponseEntity<List<String>>(employeRepository.search("%"+mc+"%"),HttpStatus.OK) ;
    }
    @RequestMapping(value ="/EmployeByCin" , method=RequestMethod.GET)
    public Employe employeByCin(@RequestParam(name = "cin",defaultValue = "") String cin){
        return employeRepository.employeByCin("%"+cin+"%");
    }
    @RequestMapping(value="/employe/{id}",method=RequestMethod.GET)
    public Optional<Employe> getEmploye(@PathVariable Long id)
    {
        return employeRepository.findById(id);
    }
    @RequestMapping(value="/employe",method=RequestMethod.POST)
    public Employe save(@RequestBody Employe employe)
    {   employe.setPassword(bcryptEncoder.encode(employe.getPassword()));
        employe.setUsername(employe.getNom());
        return employeRepository.save(employe);
    }
    @RequestMapping(value="/updateEmploye/{id}",method=RequestMethod.PUT)
    public Employe updateEmploye(@PathVariable Long id,@RequestBody Employe employe)
    {
        employe.setId(id);
        return employeRepository.save(employe);
    }
    @RequestMapping(value="/chercherEmployes",method=RequestMethod.GET)
    public Page<Employe> chercher(@RequestParam(name = "mc",defaultValue = "") String mc,
                                      @RequestParam(name = "page",defaultValue = "0")int page,
                                      @RequestParam(name = "size",defaultValue = "5")int size)
    {
        return employeRepository.chercher("%"+mc+"%", PageRequest.of(page,size) );

    }

    @RequestMapping(value="/chercherEmploye",method=RequestMethod.GET)
    public Employe chercher(@RequestParam(name = "username",defaultValue = "") String nom
                                  )
    {
        return employeRepository.chercherEmploye(nom);

    }

    @RequestMapping(value ="/employe/{id}",method = RequestMethod.DELETE)
            public boolean supprimer(@PathVariable Long id){
        employeRepository.deleteById(id);
        return true;
    }
    StorageService storageService;

    List<String> files = new ArrayList<>();

    @PostMapping("/post")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            storageService.store(file);
            files.add(file.getOriginalFilename());

            message = "You successfully uploaded " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "FAIL to upload " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }
    }
    @GetMapping("/files/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Resource> getFile(@PathVariable String filename) {
        Resource file = storageService.loadFile(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

}
