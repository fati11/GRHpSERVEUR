package org.sid.stage.entities;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Conge implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private Date dateDebutConge;
    private Date dateFinConge;
    private static int nbrJour=30;
    private String motif;
    private String etatConge;
    private int nbrjourPris;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "id_Employe")
    private Employe employe;

    public Conge() {
    }
    public int getNbrjourPris() {
        return nbrjourPris;
    }

    public void setNbrjourPris(int nbrjourPris) {
        this.nbrjourPris = nbrjourPris;
    }

    public Conge(Date dateDebutConge, Date dateFinConge, String motif, String etatConge, Employe employe) {
        this.dateDebutConge = dateDebutConge;
        this.dateFinConge = dateFinConge;
        this.motif = motif;
        this.etatConge = etatConge;
        this.employe = employe;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateDebutConge() {
        return dateDebutConge;
    }

    public void setDateDebutConge(Date dateDebutConge) {
        this.dateDebutConge = dateDebutConge;
    }

    public Date getDateFinConge() {
        return dateFinConge;
    }

    public void setDateFinConge(Date dateFinConge) {
        this.dateFinConge = dateFinConge;
    }

    public static int getNbrJour() {
        return nbrJour;
    }

    public static void setNbrJour(int nbrJour) {
        Conge.nbrJour = nbrJour;
    }

    public String getMotif() {
        return motif;
    }

    public void setMotif(String motif) {
        this.motif = motif;
    }

    public String getEtatConge() {
        return etatConge;
    }

    public void setEtatConge(String etatConge) {
        this.etatConge = etatConge;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
}
