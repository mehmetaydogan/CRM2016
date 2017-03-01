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
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
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
    @EJB
    private InteractionFacade interactionFacade;
    private List<Entreprise> listeEntreprise = new ArrayList();
    private List<Interaction> listeInteraction = new ArrayList();
    
    /**
     * Creates a new instance of EntrepriseMBean
     */
    public EntrepriseMBean() {
    }
    
    // 1- Des modèles - définnis par des getters et des setters
    
    // Modèles correspondant à la liste des entreprises
    public List<Entreprise> getEntreprises() {
        if(listeEntreprise.isEmpty()) {
            refreshEntreprisesFromDatabase();
        } else {
            System.out.println("J'utilise la liste cachée.");
        }
        return listeEntreprise;
    }
    
    // Modèles correspondant à la liste des entreprises
    public List<Interaction> getInteractions(Entreprise e) {
        if(listeInteraction.isEmpty()) {
            refreshInteractionsFromDatabase(e);
        } else {
            System.out.println("J'utilise la liste cachée.");
        }
        return listeInteraction;
    }

    // Action method (méthodes appelées par click sur
    // button 
    public void refreshEntreprisesFromDatabase() {
        // On remplit la liste
        System.out.println("Je remplis la liste.");
        listeEntreprise = entrepriseFacade.findAll();
    }
    
    public void refreshInteractionsFromDatabase(Entreprise e) {
        // On remplit la liste
        System.out.println("Je remplis la liste.");
        listeInteraction = entrepriseFacade.find(e.getId()).getInteractions();
    }
    
    public String voirInteractions(Entreprise e) {
        // Quelques choses a faire, on va voir..
        System.out.println("Dans voirInteractions id=" + e.getId());
        // Trouver que les interactions de l'entreprises.
        listeInteraction = this.getInteractions(e);
        return "interactions?faces-redirect=true";
    }
}