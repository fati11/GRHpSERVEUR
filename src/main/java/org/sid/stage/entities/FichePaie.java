package org.sid.stage.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class FichePaie implements Serializable {
    @Id @GeneratedValue
    private Long id;
    private String cin;
    private String fonction;
    private String mois ;
    private Date dateDebut;
    private Date dateFin;
    private Date dateEmbauche ;
    private long salaireBase;
    private String SituationFamiliale ;
    private int EnfantAcharge;
    private double tauxCIMR;
    private double assuranceMaladie;
    private String typePrime;
    private double montantPrime;

    public FichePaie() {
    }
    public FichePaie(String cin, String fonction, String mois, Date dateDebut, Date dateFin, Date dateEmbauche, long salaireBase, String situationFamiliale, int enfantAcharge, double tauxCIMR, double assuranceMaladie, String typePrime, double montantPrime) {
        this.cin = cin;
        this.fonction = fonction;
        this.mois = mois;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.dateEmbauche = dateEmbauche;
        this.salaireBase = salaireBase;
        SituationFamiliale = situationFamiliale;
        EnfantAcharge = enfantAcharge;
        this.tauxCIMR = tauxCIMR;
        this.assuranceMaladie = assuranceMaladie;
        this.typePrime = typePrime;
        this.montantPrime = montantPrime;
    }
}
