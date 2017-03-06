/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Interaction;
import entities.InteractionCoupTelephone;
import entities.InteractionEmail;
import entities.Users;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import sessions.EntrepriseFacade;
import sessions.InteractionFacade;
import sessions.UsersFacade;

/**
 *
 * @author AGLIF - AYDOGAN - NEHOUCHI
 */
@Named(value = "interactionMBean")
@ViewScoped
public class InteractionMBean implements Serializable{
    @EJB
    private EntrepriseFacade entrepriseFacade;
    @EJB
    private InteractionFacade interactionFacade;
    @EJB
    private UsersFacade usersFacade;
    private int id;
    private List<String> typesInteraction = new ArrayList<>();
    private String typeInteraction;
    private String information;
    private String contenu;
    private String email;
    private String telephone;

    public String getTypeInteraction() {
        return typeInteraction;
    }

    public void setTypeInteraction(String typeInteraction) {
        this.typeInteraction = typeInteraction;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public List<String> getTypesInteraction() {
        return typesInteraction;
    }

    public void setTypesInteraction(List<String> typesInteraction) {
        this.typesInteraction = typesInteraction;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    
    public List<Interaction> getInteractions() {
        if(id!=0) {
            return entrepriseFacade.getInteractions(id);
        }
        return null;
    }
    /**
     * Creates a new instance of InteractionMBean
     */
    public InteractionMBean() {
        typesInteraction.add("Coup de Télephone");
        typesInteraction.add("Email");
    }
    
    public Users getUserConnected(){
        return usersFacade.getUserConnected();
    }
    
    public String ajouterInteraction(int id){
        Users user = getUserConnected();

        Interaction i = null;
        if(typeInteraction.equals("Coup de Télephone")) {
            i = new InteractionCoupTelephone(contenu,user,information);
        } else {
            if(typeInteraction.equals("Email")) {
                i = new InteractionEmail(contenu,user,information);
            } 
        }
        entrepriseFacade.ajouterInteraction(i,id);
        return "interactions?id="+id+"&amp;faces-redirect=true";
    }
    
    public String modifierInteraction(Interaction i){
        System.out.println(i.getContenu());
        interactionFacade.edit(i);
        
        return "interactions?id="+id+"&amp;faces-redirect=true";
    }
}
