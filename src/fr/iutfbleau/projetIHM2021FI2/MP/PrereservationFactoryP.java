package fr.iutfbleau.projetIHM2021FI2.MP;

import fr.iutfbleau.projetIHM2021FI2.API.*;

import java.sql.*;
import java.time.LocalDate;
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
            PreparedStatement sql = this.connexion.prepareStatement("SELECT reference, debut, nuits, categorie, client FROM Prereservation WHERE client IN (SELECT id FROM Client WHERE nom=(?) AND prenom=(?) ) ");
            sql.setString(1, n);
            sql.setString(2, p);
            ResultSet result = sql.executeQuery();
            Set<Prereservation> prereservations = new HashSet<>();

            while(result.next()){
                System.out.println("dans le while");
                PreparedStatement sql2 = this.connexion.prepareStatement("SELECT sigle FROM Categorie WHERE id = ?");
                sql2.setInt(1, result.getInt(4));
                ResultSet result2 = sql2.executeQuery();
                TypeChambre type = null;
                result2.next();
                if (result2.getString(1).equals("UNLS")){
                    type = TypeChambre.UNLS;
                } else if (result2.getString(1).equals("UNLD")){
                    type = TypeChambre.UNLD;
                } else if (result2.getString(1).equals("DEUXLS")){
                    type = TypeChambre.DEUXLS;
                }
                System.out.println("12");
                PreparedStatement sql3 = this.connexion.prepareStatement("SELECT id, prenom, nom FROM Client WHERE id = ?");
                System.out.println("13");
                sql3.setInt(1, result.getInt(5));
                System.out.println("14");
                ResultSet result3 = sql3.executeQuery();
                System.out.println("15");
                Client client = new ClientP(result3.getInt(1), result3.getString(2), result3.getString(3));
                Prereservation pre = new PrereservationP(result.getString(1), (LocalDate) result.getObject(2), result.getInt(3), type, client);
                prereservations.add(pre);
            }
            return prereservations;
        } catch (Exception e) {
            throw new IllegalStateException("Il n'y a pas de préréservation avec un Client ayant pour nom et prenom : " + n + " et " + p);
        }
    }
}
