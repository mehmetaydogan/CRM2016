/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Interaction;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sessions.EntrepriseFacade;

/**
 *
 * @author Mehmet
 */
@Named(value = "interactionMBean")
@ViewScoped
public class InteractionMBean implements Serializable{
    @EJB
    private EntrepriseFacade ge;
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    public List<Interaction> getInteractions() {
        return ge.getInteractions(id);
    }
    /**
     * Creates a new instance of InteractionMBean
     */
    public InteractionMBean() {
    }
    
}
