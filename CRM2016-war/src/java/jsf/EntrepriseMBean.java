/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import sessions.*;

/**
 *
 * @author AGLIF - AYDOGAN - NEHOUCHI
 */
@Named(value = "entrepriseMBean")
@ViewScoped
public class EntrepriseMBean implements Serializable{
    @EJB
    private EntrepriseFacade entrepriseFacade;

    private List<Entreprise> liste = new ArrayList();
    private LazyDataModel<Entreprise> modele;
    private String nom;
    private String adresse;
    private String codePostal;
    private String ville;
    private String description;
    
    /**
     * Creates a new instance of EntrepriseMBean
     */
    public EntrepriseMBean() {
        modele = new LazyDataModel<Entreprise>() {
            @Override
            public List load(int start, int nb, String nomColonne, SortOrder sort, Map map) {
                return entrepriseFacade.findRange(start, nb, nomColonne, sort.toString());
            }
            
            @Override
            public int getRowCount() {
                return entrepriseFacade.count();
            }
        };
    }
    public LazyDataModel<Entreprise> getModele() {    
        return modele; 
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

    public void setModele(LazyDataModel<Entreprise> modele) {
        this.modele = modele;
    }

    // Modèles correspondant à la liste des entreprises
    public List<Entreprise> getEntreprises() {
        if(liste.isEmpty()) {
            refreshListeFromDatabase();
        } else {
            System.out.println("J'UTILISE LA LISTE CACHEE");
        }
        return liste; 
    }
    
        /** 
     * Action handler - appelé lorsque l'utilisateur sélectionne une ligne dans 
     * la DataTable pour voir les détails 
     * @param entreprise 
     * @return 
     */  
    public String ajouterEntreprise() {  
        Entreprise e = new Entreprise(nom, adresse, codePostal, ville, description);
        liste.add(e);
        entrepriseFacade.create(e);
        return "entreprises?faces-redirect=true";
    } 

    // ACTION METHOD (méthodes appelées par click sur 
    // bouton/lien ou event
    public void refreshListeFromDatabase() {
        // on remplit la liste
        System.out.println("JE REMPLIS LA LISTE");
        liste = entrepriseFacade.findAll();
    }
    
    public String modifierEntreprise(Entreprise e) {
        FacesContext context = FacesContext.getCurrentInstance(); 
        String message = "";
        try {
            entrepriseFacade.edit(e);
            refreshListeFromDatabase(); 
            message = "L'entreprise a été correctement mis à jour.";
            context.addMessage(null, new FacesMessage("Succès", message) );
        } catch(Exception exception) {
            message = "L'entreprise n'a pas été correctement mis à jour.";
            System.out.println(exception.getMessage());
            context.addMessage(null, new FacesMessage("Erreur", message) );
        }
        return "entreprises?faces-redirect=true";
    }
    
    public String voirInteractions(Entreprise e) {
        // ici on va voir...
        System.out.println("DANS voirInteractions id=" + e.getId());
        return "interactions?id="+e.getId()+"&amp;faces-redirect=true";
    }
}