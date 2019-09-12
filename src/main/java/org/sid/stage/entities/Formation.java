package org.sid.stage.entities;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Formation implements Serializable {
    @Id @GeneratedValue
 private Long id;
 private String sujetFormation;
 private Date dateDebut;
 private int NbrJours;
 private String lieu;
 private String statut;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "id_Employe")
    private Employe employe;

    public Formation() {
    }
    public Formation(String sujetFormation, Date dateDebut, int nbrJours, String lieu, String statut, Employe employe) {
        this.sujetFormation = sujetFormation;
        this.dateDebut = dateDebut;
        NbrJours = nbrJours;
        this.lieu = lieu;
        this.statut = statut;
        this.employe = employe;
    }
}
