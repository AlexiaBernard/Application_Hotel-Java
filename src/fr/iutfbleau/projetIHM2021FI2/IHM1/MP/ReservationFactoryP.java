package fr.iutfbleau.projetIHM2021FI2.IHM1.MP;

import java.sql.*;
import java.time.*;
import java.util.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;

public class ReservationFactoryP implements ReservationFactory {

    private Connection connexion;

    public ReservationFactoryP(Connection connexion) {
        this.connexion = connexion;
    }

    public Connection getConnexion(){
        return this.connexion;
    }


    /**
     * Recherche une chambre adéquate à partir de
     * @param  p une  préréservation 
     * @return la chambre
     * @throws NullPointerException si un argument est null
     * @throws IllegalStateException si une chambre correspondant à cette Préréservation n'existe pas.
     *
     * Ne devrait pas retourner un objet null.
     */
    @Override
    public Chambre getChambre(Prereservation p) {
        Objects.requireNonNull(p,"La préréservation est null.");
        try {
            PreparedStatement sql = this.connexion.prepareStatement("SELECT * FROM Chambre WHERE type = ? NOT IN (SELECT Chambre FROM Reservation ...");
            //Requête SQL a retravailler : il faut vérifier que chaque chambre soit bien disponible dans 
            //les reservations du jour mais aussi pas réservée dans les jours précédents sur une plus longue 
            //durée
            sql.setObject(1, p.getTypeChambre());
            ResultSet result = sql.executeQuery();
            return (Chambre) result;
        } catch (SQLException e) {
            throw new IllegalStateException("L'Hôtel ne dispose plus de chambre disponible pour le type "+ p.getTypeChambre());
        }
    }


     /**
     * Recherche toutes les chambres adéquates à partir de
     * @param  p une  préréservation 
     * @return les chambres (set de chambre)
     * @throws NullPointerException si un argument est null
     * @throws IllegalStateException si une chambre correspondant à cette Préréservation n'existe pas.
     * Ne devrait pas retourner un objet null.
     * 
     */
    @Override
    public Set<Chambre> getChambres(Prereservation p) {
        Objects.requireNonNull(p,"La préréservation est null.");
        try {
            PreparedStatement sql = this.connexion.prepareStatement("SELECT * FROM Chambre WHERE type = ? NOT IN (SELECT Chambre FROM Reservation ...");
            //Requête SQL a retravailler : il faut vérifier que chaque chambre soit bien disponible dans 
            //les reservations du jour mais aussi pas réservée dans les jours précédents sur une plus longue 
            //durée
            Set<Chambre> disponibles = new HashSet<>();
            sql.setObject(1, p.getTypeChambre());
            ResultSet result = sql.executeQuery();
            while(result.next())
                disponibles.add((Chambre) result.getObject(1));//Creer un objet chambre
            return disponibles;
        } catch (SQLException e) {
            throw new IllegalStateException("L'Hôtel ne dispose plus de chambre disponible pour le type "+ p.getTypeChambre());
        }
    }


    /**
     * Fabrique (ajoute) une réservation
     * @param  p une  préréservation 
     * @param  c une  chambre (normalement libre et adaptée à la préréservation) 
     * @return la réservation
     * @throws NullPointerException si un argument est null
     * @throws IllegalArgumentException si la chambre ne correspondant pas au type de chambre de la préréservation.
     * @throws IllegalStateException si la chambre n'est pas disponible.
     *
     * Ne devrait pas retourner un objet null.
     */    
    @Override
    public Reservation createReservation(Prereservation p, Chambre c) {
        Objects.requireNonNull(p,"La préréservation est null.");
        Objects.requireNonNull(p,"La chambre est null.");
        try {
            //Requête pour récuperer le type de chambre de la prereservation
            PreparedStatement sql = this.connexion.prepareStatement("SELECT TypeChambre FROM Prereservation WHERE reference = ?");
            sql.setObject(1, p.getReference());
            ResultSet result = sql.executeQuery();

            //Requête pour récuperer le type de chambre de la chambre
            sql = this.connexion.prepareStatement("SELECT TypeChambre FROM Chambre WHERE id = ?");
            sql.setInt(1, c.getNumero());
            ResultSet result2 = sql.executeQuery();
            if (result.getObject(1) != result2.getObject(1) ) {
                throw new IllegalArgumentException("Erreur sur le type de la chambre: la préréservation indique " + p.getTypeChambre() + " mais la chambre est  " + c.getType());
            } else {
                //Ici il faut vérifier que la chmabre mise en argument ne soit pas déjà dans une reservation
                try {
                    //Si la chambre n'est pas disponible pour cette date)
                    sql = this.connexion.prepareStatement("INSERT INTO Reservation VALUES (?,?,?,?,?)");
                    sql.setObject(1, p.getReference());
                    sql.setObject(2, p.getDateDebut());
                    sql.setInt(3, p.getJours());
                    sql.setObject(4, p.getClient());
                    sql.setInt(5, c.getNumero());
                    //Ici il faut faire la requête d'insertion dans reservation
                    sql.executeUpdate();
                    sql = this.connexion.prepareStatement("DELETE FROM Prereservation WHERE reference = ?");
                    sql.setObject(1, p.getReference());
                    sql.executeUpdate();
                    //Reservation reserv = new ReservationP(p.getReference(), p.getDateDebut(), p.getJours(), c, p.getClient());
                    //return reserv;
                    return null;
                } catch (Exception e) {
                    throw new IllegalArgumentException("La chambre " + c.monPrint() + " n'est pas disponible pour fabriquer une réservation à partir de la préréservation " + p.monPrint());
                }
            }
        } catch (SQLException e) {
            throw new IllegalStateException("La réservation a échoué");
        }
    }

    /**
     * Cherche les réservations
     * @param  d une date
     * @return la ou les réservation(s) à cette date sous forme d'un ensemble
     * @throws NullPointerException si un argument est null
     *
     * Ne devrait pas retourner un objet null, par contre peut être un ensemble qui est vide.
     */ 
    @Override
    public Set<Reservation> getReservation(LocalDate d) {
        Objects.requireNonNull(d,"La date proposée est null.");
        Set<Reservation> s = new HashSet<>();
        try {
            PreparedStatement sql = this.connexion.prepareStatement("SELECT * FROM Reservation WHERE  = ? NOT IN (SELECT Chambre FROM Reservation ...");
            //Requête SQL a retravailler : il faut chercher toutes les reservations de ce jour, y
            //compris celles faites les jours précédents et toujours à la date d
            Set<Reservation> reservations = new HashSet<>();
            sql.setObject(1, d);
            ResultSet result = sql.executeQuery();
            while(result.next())
                reservations.add((Reservation) result.getObject(1));//Creer un objet Reservation
            return reservations;
            //tout ça est à retravailler avec la requête : verifier chaque jour
            //Probablement faire une boucle
        } catch (SQLException e) {
            throw new IllegalStateException("Problème avec la requête.");
        }
    }
    //CETTE METHODE EST A RETRAVAILLER


    
    @Override
    public int getDisponibles(LocalDate d) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<Reservation> getReservation(LocalDate d, TypeChambre t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getDisponibles(LocalDate d, TypeChambre t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRatio(LocalDate d) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRatio(LocalDate d, TypeChambre t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDisponibles(LocalDate d1, LocalDate d2) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<Reservation> getReservation(LocalDate d1, LocalDate d2, TypeChambre t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getDisponibles(LocalDate d1, LocalDate d2, TypeChambre t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRatio(LocalDate d1, LocalDate d2) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRatio(LocalDate d1, LocalDate d2, TypeChambre t) {
        // TODO Auto-generated method stub
        return 0;
    }

}
