package fr.iutfbleau.projetIHM2021FI2.MP;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import java.util.*;
/**
 * Un client non persistant tout bête
 */

public class ClientP implements Client {

    private int id;
    private String nom;
    private String prenom;

    /**
     * Constructeur
     */
    public ClientP(int id, String prenom, String nom){
        Objects.requireNonNull(nom,"On ne peut pas créer une personne avec un nom à null.");
        Objects.requireNonNull(prenom,"On ne peut pas créer une personne avec un prenom à null.");
        this.id=id;
        this.nom=nom;
        this.prenom=prenom;
    }
    
    /**
     * permet de récupérer l'identifiant du client (qu'on suppose être le même pour les différents systèmes, internes et externes à l'hôtel).
     * @return l'identifiant.
     */
    public int getId(){
        return this.id;
    }

    /**
     * permet de récupérer 
     * @return le nom du client.
     */
    public String getNom(){
        return this.nom;
    }

    /**
     * permet de récupérer
     * @return le prénom du client
     */
    public String getPrenom(){
        return this.prenom;
    }
}
