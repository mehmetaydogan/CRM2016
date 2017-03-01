/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Interaction;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AGLIF - AYDOGAN - NEHOUCHI
 */
@Stateless
public class InteractionFacade extends AbstractFacade<Interaction> {
    @PersistenceContext(unitName = "CRM2016-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InteractionFacade() {
        super(Interaction.class);
    }
    
}
