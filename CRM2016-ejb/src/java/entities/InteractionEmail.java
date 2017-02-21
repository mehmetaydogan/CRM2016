/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author m.dev
 */
@Entity
public class InteractionEmail extends Interaction {
    private String email;
    public InteractionEmail() {
    }
    
    public InteractionEmail(String contenu, String email) {
        super(contenu);
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @Override
    public String getType() {
        return "Email.";
    }
   
}
