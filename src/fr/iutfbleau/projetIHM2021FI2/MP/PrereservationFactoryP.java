package fr.iutfbleau.projetIHM2021FI2.MP;
import fr.iutfbleau.projetIHM2021FI2.API.*;

import java.sql.*;
import java.util.*;
/**
 *
 */
public class PrereservationFactoryP implements PrereservationFactory{
    
    private Connection connexion;

    public PrereservationFactoryP(Connection connexion){
        this.connexion = connexion;
    }

    
    public Connection getConnexion(){
        return this.connexion;
    }

    /**
     * Recherche une préréservation par reference
     * @param  r la référence du système de préréservation 
     * @return la préréservation.
     * @throws NullPointerException si un argument est null
     * @throws IllegalStateException si la Préréservation avec cette référence n'existe pas.
     *
     * Ne devrait pas retourner un objet null.
     */
    public Prereservation getPrereservation(String r){
        Objects.requireNonNull(r,"La référence recherchée est null.");
        try {
            PreparedStatement sql = this.connexion.prepareStatement("SELECT * FROM Prereservation WHERE reference = ?");
            sql.setString(1, r);
            ResultSet result = sql.executeQuery();
            return (Prereservation) result.getObject(1);
        } catch (SQLException e) {
            throw new IllegalStateException("Il n'y a pas de préréservation avec la référence : " + r);
        }
    }
    /**
     * Recherche une préréservation par nom et prenom
     * @param  n le nom
     * @param  p le prenom
     * @return un ensemble de préréservations.
     * @throws NullPointerException si un argument est null
     * @throws IllegalStateException si aucune préréservation n'existe avec ce nom
     *
     * Ne devrait pas retourner un objet null ou un ensemble vide.
     */
    public Set<Prereservation> getPrereservations(String n, String p){
        Objects.requireNonNull(n,"Le nom recherché est null.");
        Objects.requireNonNull(p,"Le prénom recherché est null.");
        try {
            PreparedStatement sql = this.connexion.prepareStatement("SELECT * FROM Prereservation WHERE client IN (SELECT id FROM Client WHERE nom=? AND prenom=? ");
            sql.setString(1, n);
            sql.setString(2, p);
            ResultSet result = sql.executeQuery();
            Set<Prereservation> prereservations = new HashSet<>();
            while(result.next())
                prereservations.add((Prereservation) result.getObject(1)); //Creer un objet prereservation
            return prereservations;
        } catch (Exception e) {
            throw new IllegalStateException("Il n'y a pas de préréservation avec un Client ayant pour nom et prenom : " + n + " et " + p);
        }
    }
}
