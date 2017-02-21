/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Entreprise;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sessions.EntrepriseFacade;

/**
 *
 * @author m.dev
 */
@Named(value = "entrepriseMBean")
@ViewScoped
public class EntrepriseMBean implements Serializable{
    @EJB
    private EntrepriseFacade entrepriseFacade;
    private List<Entreprise> liste = new ArrayList();
    
    /**
     * Creates a new instance of EntrepriseMBean
     */
    public EntrepriseMBean() {
    }
    
    // 1- Des modèles - définnis par des getters et des setters
    
    // Modèles correspondant à la liste des entreprises
    public List<Entreprise> getEntreprises() {
        if(liste.isEmpty()) {
            refreshListFromDatabase();
        } else {
            System.out.println("J'utilise la liste cachée.");
        }
        return liste;
    }

    // Action method (méthodes appelées par click sur
    // button 
    public void refreshListFromDatabase() {
        // On remplit la liste
        System.out.println("Je remplis la liste.");
        liste = entrepriseFacade.findAll();
    }
    
    public String voirInteractions(Entreprise e) {
        // Quelques choses a faire, on va voir..
        System.out.println("Dans voirInteractions id=" + e.getId());
        
        return "interactions?faces-redirect=true";
    }
}
