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

    @Override
    public Reservation createReservation(Prereservation p, Chambre c) {
        // TODO Auto-generated method stub
        return null;
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
