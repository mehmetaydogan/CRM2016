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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author AGLIF - AYDOGAN - NEHOUCHI
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED) 
public class InteractionCoupTelephone extends Interaction{
    private String numeroTel;
    
    public InteractionCoupTelephone() {
    }
    public InteractionCoupTelephone(String contenu, String numeroTel) {
        super(contenu);
        this.numeroTel = numeroTel;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }
    
    @Override
    public String getType() {
        return "Coup de téléphone.";
    }
}
