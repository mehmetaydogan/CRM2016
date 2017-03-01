/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Entreprise;
import entities.Interaction;
import entities.InteractionCoupTelephone;
import entities.InteractionEmail;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author AGLIF - AYDOGAN - NEHOUCHI
 */
@Stateless
public class EntrepriseFacade extends AbstractFacade<Entreprise> {
    @EJB
    private InteractionFacade interactionFacade;
    @PersistenceContext
    private EntityManager em;

    // ON hérite du CRUD + pagination
    // si on doit faire de nouvelles fonctionnalités
    // ou de nouvelles requêtes on rajoutera
    // des méthodes ici...
    
    
    //Exemple
    public void creerEntreprisesDeTest () {
        System.out.println("CREATION DE DONNEES DE TEST");
        Entreprise e1 = new Entreprise("Miage Nice", "Route des lucioles", "06560",
                "Sophia Antipolis", "La meilleure entreprise du monde");
        // utilisation de la méthode héritée pour faire
        // un insert
        create(e1);
        
        // une interaction pour l'entreprise
        Interaction i1 = new InteractionCoupTelephone("Appel de monsieur tartanpion pour la taxe d'apprentissage au 0654345323","0654345323");
        // on fait un insert dans la table des interactions.
        interactionFacade.create(i1);
        
        // une interaction pour l'entreprise
        Interaction i2 = new InteractionEmail("Email à Mr Dupont","0493546543");
        // on fait un insert dans la table des interactions.
        interactionFacade.create(i2);
        
        Entreprise e2 = new Entreprise("Polytech Nice", "Site des templiers", "06560",
                "Sophia Antipolis", "De l'autre côté de la rue");
        create(e2);
        
        // on ajoute l'interaction
        e1.addInteraction(i1);
        // on ajoute l'interaction
        e1.addInteraction(i2);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EntrepriseFacade() {
        super(Entreprise.class);
    }
    
}
