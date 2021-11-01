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
            PreparedStatement sql2 = this.connexion.prepareStatement("SELECT TypeChambre FROM Chambre WHERE id = ?");
            sql2.setInt(1, c.getNumero());
            ResultSet result2 = sql2.executeQuery();
            if (result.getObject(1) != result2.getObject(1) ) {
                throw new IllegalArgumentException("Erreur sur le type de la chambre: la préréservation indique " + p.getTypeChambre() + " mais la chambre est  " + c.getType());
            }
        } catch (Exception e) {
            throw new IllegalStateException("La réservation a échoué");
        }
        
        else if (//si la chambre n'est pas disponible pour cette date) // on fait comme si ça n'arrive jamais dans l'hôtel magique (pour l'instant).
            {
                throw new IllegalArgumentException("La chambre " + c.monPrint() + " n'est pas disponible pour fabriquer une réservation à partir de la préréservation " + p.monPrint());
            }
        else {
            Reservation r = new ReservationNP(p.getReference(), p.getDateDebut(), p.getJours(), c, p.getClient());
            this.addReservationToBrain(r);
            return r;
        }
    }

    @Override
    public Set<Reservation> getReservation(LocalDate d) {
        // TODO Auto-generated method stub
        return null;
    }

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
