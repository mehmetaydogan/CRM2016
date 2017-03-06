/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author AGLIF - AYDOGAN - NEHOUCHI
 */
@Entity
public abstract class Interaction implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date dateInteraction;
    private String contenu;
    @OneToOne
    private Users createdBy;
    
    public Interaction() {
    }
    
    public Interaction(String contenu, Users created) {
        this.contenu = contenu;
        this.dateInteraction = new Date();
        this.createdBy = created;
    }

    public Date getDateInteraction() {
        return dateInteraction;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users user) {
        this.createdBy = user;
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
    
    // Toutes les sous-classes devront avoir une méthode getType().
    public abstract String getType();

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Interaction)) {
            return false;
        }
        Interaction other = (Interaction) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ContactEntreprise[ id=" + id + " ]";
    }
}
