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
public class EleveModel {
    private String id;
    private String nom;
    private String prenom;
    private String sexe;
    private String classe;
    private String niveau;
    private String section;

    public EleveModel() {
    }

    public EleveModel(String id, String nom, String prenom, String sexe, String classe, String niveau, String section) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.classe = classe;
        this.niveau = niveau;
        this.section = section;
    }
      public EleveModel(String nom, String prenom, String sexe, String classe, String niveau, String section) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.classe = classe;
        this.niveau = niveau;
        this.section = section;
    }
       public EleveModel(String nom, String prenom, String sexe, String classe, String niveau) {
        this.nom = nom;
        this.prenom = prenom;
        this.sexe = sexe;
        this.classe = classe;
        this.niveau = niveau;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * @param prenom the prenom to set
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    /**
     * @return the sexe
     */
    public String getSexe() {
        return sexe;
    }

    /**
     * @param sexe the sexe to set
     */
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    /**
     * @return the classe
     */
    public String getClasse() {
        return classe;
    }

    /**
     * @param classe the classe to set
     */
    public void setClasse(String classe) {
        this.classe = classe;
    }

    /**
     * @return the niveau
     */
    public String getNiveau() {
        return niveau;
    }

    /**
     * @param niveau the niveau to set
     */
    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    /**
     * @return the section
     */
    public String getSection() {
        return section;
    }

    /**
     * @param section the section to set
     */
    public void setSection(String section) {
        this.section = section;
    }
    
}
