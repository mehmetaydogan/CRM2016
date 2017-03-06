/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf;

import entities.Users;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import sessions.UsersFacade;

/**
 *
 * @author AGLIF - AYDOGAN - NEHOUCHI
 */
@Named(value = "loginMBean")
@SessionScoped
public class loginMBean implements Serializable {
    @EJB
    UsersFacade cm;
    
    private Users user;
    private String pseudo;
    private String motDePasse;
    private boolean connected = false;
    private String message = "Veuillez vous identifier :";

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLogin() {
        return pseudo;
    }

    public void setLogin(String login) {
        this.pseudo = login;
    }

    public String getPassword() {
        return motDePasse;
    }

    public void setPassword(String password) {
        this.motDePasse = password;
    }

    public boolean isConnected() {
        return connected;
    }

    public void setConnected(boolean connected) {
        this.connected = connected;
    }

    public Users getUtilisateur() {
        return user;
    }

    public void setUtilisateur(Users utilisateur) {
        this.user = utilisateur;
    }

    /** Creates a new instance of LoginMBean */
    public loginMBean() {
    }

    public void deconnexion() {
        connected = false;
        user = null;
        message = "Veuillez vous identifier :";
    }

    public String traiterIdentification() {
        // Normalement à partir du login on devrait demander à l'ejb
        // gestionnaireUtilisateur de chercher dans la BD et retourner un 
        // utilisateur qu'on met dans la propriété utilisateur.
        // Là on simule...
        System.out.println("DANS TRAITER ID");
        //Users c = cm.rechercherUsersParPseudo(pseudo, motDePasse);
        System.out.println(user == null ? "vide" : user.getNom());
        connected = cm.rechercherUsersParPseudo(pseudo, motDePasse) != null;
        if (connected) {
            user = cm.rechercherUsersParPseudo(pseudo, motDePasse);
            return "entreprises?faces-redirect=true";
        } else {
            message = "Mauvais login/password, veuillez recommencer !";
            return "index?faces-redirect=true";
        }
    }
    
    public Users getUserConnected() {
       return cm.getUserConnected();
    }
}
