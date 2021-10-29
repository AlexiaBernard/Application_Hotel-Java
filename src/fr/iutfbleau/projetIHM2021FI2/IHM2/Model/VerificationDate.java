package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.sql.*;
import java.time.*;
import javax.swing.*;

public class VerificationDate {

    private JFrame fenetre;
    private BD baseDeDonnées;
    private LocalDate date;

    /**
     * 
     * @param fenetre
     * @param baseDeDonnées
     * @param date
     */
    public VerificationDate(JFrame fenetre, BD baseDeDonnées, LocalDate date) {
        this.fenetre = fenetre;
        this.baseDeDonnées = baseDeDonnées;
        this.date = date;
    }

    public void run(){
        Connection connexion = this.baseDeDonnées.getConnexion();
        try {
            PreparedStatement sql = connexion.prepareStatement("SELECT COUNT(*) FROM Reservation WHERE debut = ?");
            sql.setObject(1, this.date);
            ResultSet result = sql.executeQuery();
            while(result.next()){
                //Normalement un seul résultat
                System.out.println(result.getInt(1));
            }
            sql = connexion.prepareStatement("SELECT COUNT(*) FROM Reservation WHERE debut = ?");
            sql.setObject(1, this.date);
            ResultSet result1 = sql.executeQuery();
            while(result.next()){
                //Normalement un seul résultat
                System.out.println(result.getInt(1));
            }
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
    
}
