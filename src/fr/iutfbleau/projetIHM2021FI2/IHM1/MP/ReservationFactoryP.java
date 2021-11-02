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

    public Set<Reservation> getAllReservation(){
        try {
            //Requête qui récupère toutes les réservations de l'Hôtel
            PreparedStatement sql = this.connexion.prepareStatement("SELECT reference, debut, nuits, chambre, client FROM Reservation");
            ResultSet result = sql.executeQuery();
            Set<Reservation> reservations = new HashSet<>();
            while( result.next() ){
                //Requête qui permt de récupérer la catégorie de la Chambre afin de l'instancier
                PreparedStatement sql2 = this.connexion.prepareStatement("SELECT categorie FROM Chambre WHERE id = ?");
                sql2.setInt(1, result.getInt(4));
                ResultSet result2 = sql2.executeQuery();
                //Requête qui permet de récupérer le type de la chambre afin de l'instancier
                PreparedStatement sql3 = this.connexion.prepareStatement("SELECT single FROM TypeChambre WHERE id = ?");
                sql3.setInt(1, result2.getInt(1));
                ResultSet result3 = sql3.executeQuery();
                //Requête qui permet de récupérer le nom et prénom du client afin de l'instancie
                PreparedStatement sql4 = this.connexion.prepareStatement("SELECT nom, prenom FROM Client WHERE id = ?");
                sql4.setInt(1, result.getInt(5));
                ResultSet result4 = sql4.executeQuery();
                TypeChambre type = null;
                if (result3.getString(1).equals("UNLS")){
                    type = TypeChambre.UNLS;
                } else if (result3.getString(1).equals("DEUXLS")){
                type = TypeChambre.DEUXLS;
                } else if (result3.getString(1).equals("UNLD")){
                    type = TypeChambre.UNLD;
                }
                reservations.add(new ReservationP(result.getString(1),(LocalDate) result.getObject(2), result.getInt(3), new ChambreP(result.getInt(4),type), new ClientP(result.getInt(5), result4.getString(2), result4.getString(1))) );
            }
            return reservations;
        }catch(SQLException e){
            throw new IllegalStateException("Problème de récupération des réservations");

        }
    }

    public Set<Chambre> getAllChambreCategorie(TypeChambre type){
        try {
            //Requête qui permet de récupérer les chambres afin de l'instancier
            PreparedStatement sql = this.connexion.prepareStatement("SELECT id, categorie FROM Chambre WHERE categorie IN (SELECT id FROM TypeChambre WHERE single = ?");
            sql.setObject(1, type);
            ResultSet result = sql.executeQuery();
            Set<Chambre> chambres = new HashSet<>();
            while( result.next() ){
                chambres.add(new ChambreP(result.getInt(1), type));
            } 
            return chambres;
        } catch (SQLException e) {
            throw new IllegalStateException("Problème de récupération des chambres avec le type "+type);
        }
    }

    public Set<Chambre> getAllChambreId(int id){
        try {
            //Requête qui permet de récupérer les chambrees afin de l'instancier
            PreparedStatement sql_ch = this.connexion.prepareStatement("SELECT id, categorie FROM Chambre WHERE categorie = ?");
            sql_ch.setInt(1, id);
            ResultSet result_ch = sql_ch.executeQuery();
            
            //Requête qui permet de récupérer le type de la chambre afin de l'instancier
            PreparedStatement sql3 = this.connexion.prepareStatement("SELECT single FROM TypeChambre WHERE id = ?");
            sql3.setInt(1, result_ch.getInt(2));
            ResultSet result3 = sql3.executeQuery();
            TypeChambre type2 = null;
            if (result3.getString(1).equals("UNLS")){
                type2 = TypeChambre.UNLS;
            } else if (result3.getString(1).equals("DEUXLS")){
            type2 = TypeChambre.DEUXLS;
            } else if (result3.getString(1).equals("UNLD")){
                type2 = TypeChambre.UNLD;
            }
            Set<Chambre> chambres = new HashSet<>();
            while( result_ch.next() ){
                chambres.add(new ChambreP(result_ch.getInt(1), type2));
            } 
            return chambres;
        } catch (SQLException e) {
            throw new IllegalStateException("Problème de récupération des chambres");
        }
        
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
        try{
            Set<Reservation> reservations = this.getAllReservation();
            Set<Chambre> chambres = this.getAllChambreCategorie(p.getTypeChambre());
            Chambre chambre = null;
            for (Reservation r : reservations){
                for(Chambre c : chambres){
                    if ( c.getType().equals(r.getChambre().getType())) {
                        //Si c'est la même date
                        if (p.getDateDebut().equals(r.getDateDebut())){
                            chambres.remove(c);
                            //Si c'est pas la même date mais dans la reservation (nb de jour)
                        } else if ((r.getDateDebut().compareTo(p.getDateDebut()))<0 && r.getDateDebut().plusDays(r.getJours()).compareTo(p.getDateDebut())<=0 )  {
                            chambres.remove(c);
                        }else{
                            chambre = c;
                            break;
                        }
                    }
                }
            }
            return chambre;
        } catch (Exception e) {
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
            Set<Reservation> reservations = this.getAllReservation();
            Set<Chambre> chambres = this.getAllChambreCategorie(p.getTypeChambre());
            for (Reservation r : reservations){
                for(Chambre c : chambres){
                    if ( c.getType().equals(r.getChambre().getType())) {
                        //Si c'est la même date
                        if (p.getDateDebut().equals(r.getDateDebut())){
                            chambres.remove(c);
                            //Si c'est pas la même date mais dans la reservation (nb de jour)
                        } else if ((r.getDateDebut().compareTo(p.getDateDebut()))<0 && r.getDateDebut().plusDays(r.getJours()).compareTo(p.getDateDebut())<=0 )  {
                            chambres.remove(c);
                        }
                    }
                }
            }
            return chambres;
        } catch (Exception e) {
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
                //Ici il faut vérifier que la chambre mise en argument ne soit pas déjà dans une reservation
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
