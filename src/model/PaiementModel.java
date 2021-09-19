/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author User
 */
public class PaiementModel extends EleveModel{
    
    private String titre_niveau;
    private String frais_payer;
    private String montant_payer;
    private String date;

    public PaiementModel() {
    }

    public PaiementModel(String titre_niveau, String frais_payer, String montant_payer, String date) {
        this.titre_niveau = titre_niveau;
        this.frais_payer = frais_payer;
        this.montant_payer = montant_payer;
        this.date = date;
    }

    public PaiementModel(String nom, String prenom, String sexe, String classe, String niveau) {
        super(nom, prenom, sexe, classe, niveau);
    }

    public PaiementModel(String nom, String prenom, String sexe, String classe, String niveau, String section) {
        super(nom, prenom, sexe, classe, niveau, section);
    }

    public PaiementModel(String id, String nom, String prenom, String sexe, String classe, String niveau, String section) {
        super(id, nom, prenom, sexe, classe, niveau, section);
    }

    public PaiementModel(String titre_niveau, String frais_payer, String montant_payer, String date, String nom, String prenom, String sexe, String classe, String niveau) {
        super(nom, prenom, sexe, classe, niveau);
        this.titre_niveau = titre_niveau;
        this.frais_payer = frais_payer;
        this.montant_payer = montant_payer;
        this.date = date;
    }

    public PaiementModel(String titre_niveau, String frais_payer, String montant_payer, String date, String nom, String prenom, String sexe, String classe, String niveau, String section) {
        super(nom, prenom, sexe, classe, niveau, section);
        this.titre_niveau = titre_niveau;
        this.frais_payer = frais_payer;
        this.montant_payer = montant_payer;
        this.date = date;
    }

    public PaiementModel(String titre_niveau, String frais_payer, String montant_payer, String date, String id, String nom, String prenom, String sexe, String classe, String niveau, String section) {
        super(id, nom, prenom, sexe, classe, niveau, section);
        this.titre_niveau = titre_niveau;
        this.frais_payer = frais_payer;
        this.montant_payer = montant_payer;
        this.date = date;
    }

    /**
     * @return the titre_niveau
     */
    public String getTitre_niveau() {
        return titre_niveau;
    }

    /**
     * @param titre_niveau the titre_niveau to set
     */
    public void setTitre_niveau(String titre_niveau) {
        this.titre_niveau = titre_niveau;
    }

    /**
     * @return the frais_payer
     */
    public String getFrais_payer() {
        return frais_payer;
    }

    /**
     * @param frais_payer the frais_payer to set
     */
    public void setFrais_payer(String frais_payer) {
        this.frais_payer = frais_payer;
    }

    /**
     * @return the montant_payer
     */
    public String getMontant_payer() {
        return montant_payer;
    }

    /**
     * @param montant_payer the montant_payer to set
     */
    public void setMontant_payer(String montant_payer) {
        this.montant_payer = montant_payer;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
    
}
