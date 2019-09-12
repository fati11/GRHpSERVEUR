package org.sid.stage.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Entity
@Data
public class Employe implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String sexe;
    private Date dateNaissance;
    private String cin;
    private String cnss;
    private String statutMatrimoniel;
    private int enfants;
    private String username;
    private String password;
    private String telephone;
    private String mobile;
    private String adresse;
    private String ville;
    private String pays;
    private String photo;
    @JsonIgnore
    @OneToMany(mappedBy = "employe",fetch = FetchType.LAZY)
    private Collection<Conge> conges;
    @OneToOne(mappedBy = "employee",fetch = FetchType.LAZY)
    @JsonIgnore
    private Role role;
    public Employe() {
    }

    public Employe(String nom, String prenom, String email, String sexe, Date dateNaissance, String cin, String cnss, String statutMatrimoniel, int enfants, String username, String password, String telephone, String mobile, String adresse, String ville, String pays, String photo, Collection<Conge> conges, Role role) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.sexe = sexe;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
        this.cnss = cnss;
        this.statutMatrimoniel = statutMatrimoniel;
        this.enfants = enfants;
        this.username = username;
        this.password = password;
        this.telephone = telephone;
        this.mobile = mobile;
        this.adresse = adresse;
        this.ville = ville;
        this.pays = pays;
        this.photo = photo;
        this.conges = conges;
        this.role = role;
    }
}
