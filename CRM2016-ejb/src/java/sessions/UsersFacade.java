/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 @author AGLIF - AYDOGAN - NEHOUCHI
 */
@Stateless
public class UsersFacade extends AbstractFacade<Users> {
    @PersistenceContext(unitName = "CRM2016-ejbPU")
    private EntityManager em;
    private Users user;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
     public void generateTestUsers() {
         
        create(new Users("Aydogan", "Mehmet", "mehmet", "123"));
        create(new Users("Aglif", "Fatima", "fatimata", "impot"));
        create(new Users("Nehouchi", "Oussama", "ouss", "ouss"));
    }

    public UsersFacade() {
        super(Users.class);
    }
    
    public void creerUnUser(Users c) {
        em.persist(c);
    }
    
    public Users rechercherUsersParPseudo(String pseudo, String motDePasse) {
        Query q = em.createQuery("select c from Users c where c.pseudo = :pseudo");
        q.setParameter("pseudo", pseudo);
        Users c = null;
        try {
            c = (Users) q.getSingleResult();
        } catch(Exception exp) {
            System.out.println(exp.getMessage());
        }
        if(c == null) return c;
        if(!c.getMotDePasse().equals(motDePasse)) {
            return null;
        } else {
            user = c;
            return c;
        }
    }
    
    public Users usersParPseudo(String pseudo) {
        Query q = em.createQuery("select c from Users c where c.pseudo = :pseudo");
        q.setParameter("pseudo", pseudo);
        Users c = null;
        try {
            c = (Users) q.getSingleResult();
        } catch(Exception exp) {
            System.out.println(exp.getMessage());
        }
        return c;
    }
    
    public Users getUserConnected () {
        return user;
    }

}
