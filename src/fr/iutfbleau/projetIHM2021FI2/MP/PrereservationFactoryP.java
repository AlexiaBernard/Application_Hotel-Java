package fr.iutfbleau.projetIHM2021FI2.MP;

import fr.iutfbleau.projetIHM2021FI2.API.*;

import java.sql.*;
import java.util.*;

/**
 * Usine persistante stockant les préréservations dans une structure de donnée
 * permettant de simuler un ensemble.
 * 
 * Elle permet de rechercher les préréservations à partir du numéro d'une
 * préréservation, ou bien du nom et prenom d'un Client.
 *
 * La recherche par numéro devrait être plus efficace.
 */
public class PrereservationFactoryP implements PrereservationFactory {

    private Connection connexion;

    public PrereservationFactoryP(Connection connexion) {
        this.connexion = connexion;
    }

    public Connection getConnexion() {
        return this.connexion;
    }

    /**
     * Recherche une préréservation par reference
     * 
     * @param r la référence du système de préréservation
     * @return la préréservation.
     * @throws NullPointerException  si un argument est null
     * @throws IllegalStateException si la Préréservation avec cette référence
     *                               n'existe pas.
     *
     *                               Ne devrait pas retourner un objet null.
     */
    public Prereservation getPrereservation(String r) {
        Objects.requireNonNull(r, "La référence recherchée est null.");
        try {
            // Requête qui récupère la préreservation
            PreparedStatement sql = this.connexion.prepareStatement(
                    "SELECT reference, debut, nuits, categorie, client FROM Prereservation WHERE reference = ?");
            sql.setString(1, r);
            ResultSet result = sql.executeQuery();
            result.next();
            // Requête qui récupère le type de chambre
            PreparedStatement sql2 = this.connexion.prepareStatement("SELECT sigle FROM Categorie WHERE id = ?");
            sql2.setInt(1, result.getInt(4));
            ResultSet result2 = sql2.executeQuery();
            result2.next();
            TypeChambre type = null;
            if (result2.getString(1).equals("UNLS")) {
                type = TypeChambre.UNLS;
            } else if (result2.getString(1).equals("UNLD")) {
                type = TypeChambre.UNLD;
            } else if (result2.getString(1).equals("DEUXLS")) {
                type = TypeChambre.DEUXLS;
            }
            // Requête qui récupère le client
            PreparedStatement sql3 = this.connexion.prepareStatement("SELECT prenom, nom FROM Client WHERE id = ?");
            sql3.setInt(1, result.getInt(5));
            ResultSet result3 = sql3.executeQuery();
            result3.next();
            // Créé le client
            Client client = new ClientP(result.getInt(5), result3.getString(1), result3.getString(2));
            // Créé la préréservation
            Prereservation prereservation = new PrereservationP(r, result.getDate(2).toLocalDate(), result.getInt(3),
                    type, client);
            return prereservation;
        } catch (SQLException e) {
            throw new IllegalStateException("Il n'y a pas de préréservation avec la référence : " + r);
        }
    }

    /**
     * Recherche une préréservation par nom et prenom
     * 
     * @param n le nom
     * @param p le prenom
     * @return un ensemble de préréservations.
     * @throws NullPointerException  si un argument est null
     * @throws IllegalStateException si aucune préréservation n'existe avec ce nom
     *
     *                               Ne devrait pas retourner un objet null ou un
     *                               ensemble vide.
     */
    public Set<Prereservation> getPrereservations(String n, String p) {
        Objects.requireNonNull(n, "Le nom recherché est null.");
        Objects.requireNonNull(p, "Le prénom recherché est null.");

        try {
            // Requête qui récupère la préréservation
            PreparedStatement sql = this.connexion.prepareStatement(
                    "SELECT reference, debut, nuits, categorie, client FROM Prereservation WHERE client IN (SELECT id FROM Client WHERE nom=? AND prenom=? ) ");
            sql.setString(1, n);
            sql.setString(2, p);
            ResultSet result = sql.executeQuery();
            Set<Prereservation> prereservations = new HashSet<Prereservation>();

            while (result.next()) {
                // Requête qui récupère la catégorie de la chambre de la préréservation
                PreparedStatement sql2 = this.connexion.prepareStatement("SELECT sigle FROM Categorie WHERE id = ?");
                sql2.setInt(1, result.getInt(4));
                ResultSet result2 = sql2.executeQuery();
                TypeChambre type = null;
                result2.next();
                if (result2.getString(1).equals("UNLS")) {
                    type = TypeChambre.UNLS;
                } else if (result2.getString(1).equals("UNLD")) {
                    type = TypeChambre.UNLD;
                } else if (result2.getString(1).equals("DEUXLS")) {
                    type = TypeChambre.DEUXLS;
                }
                // Créé le client
                Client client = new ClientP(result.getInt(5), p, n);
                // Créé la préréservation
                Prereservation pre = new PrereservationP(result.getString(1), result.getDate(2).toLocalDate(),
                        result.getInt(3), type, client);
                // l'ajoute a la liste
                prereservations.add(pre);
            }
            return prereservations;
        } catch (Exception e) {
            throw new IllegalStateException(
                    "Il n'y a pas de préréservation avec un Client ayant pour nom et prenom : " + n + " et " + p);
        }
    }
}
