package fr.iutfbleau.projetIHM2021FI2.IHM1.MP;
import fr.iutfbleau.projetIHM2021FI2.API.*;

import java.sql.*;
import java.util.*;
/**
 *
 */
public class PrereservationFactoryP{
    
    // plutôt que d'utiliser un ensemble, on utilise un HashMap car on suppose qu'on va devoir chercher
    // plutôt les préréservations avec le numéro de référence (un String, voir Prereservation).
    // Pour la recherche par nom et prenom on va devoir traverser le HashMap.
    private HashMap<String,Prereservation> brain =new HashMap<String,Prereservation>();//Creating HashMap 
    private Connection connexion;

    public PrereservationFactoryP(){
        connexion();
    }

    
    public void connexion(){
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            try {
                this.connexion = DriverManager.getConnection(
                    "jdbc:mariadb://dwarves.iut-fbleau.fr/bernardal",
                    "bernardal", "bernardal");
            } catch (SQLException e) {
                System.err.println("La connexion avec la base de données a echoué.");
            }
            
        } catch (ClassNotFoundException e) {
            System.err.println("Problème de connexion avec la base de données.");
        }
    }

    public void deconnexion(){
        try {
            this.connexion.close();
        } catch (SQLException e) {
            System.err.println("Problème avec la fermeture de la Base de données.");
        }
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
            Set<Prereservation> prereservations =  ;
            while(result.next())
                prereservations.add((Prereservation) result.getObject(1)); //faire prereservation
            return prereservations;
        } catch (Exception e) {
            throw new IllegalStateException("Il n'y a pas de préréservation avec un Client ayant pour nom et prenom : " + n + " et " + p);
        }
    }
}
