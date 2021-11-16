package fr.iutfbleau.projetIHM2021FI2.MP;

import java.sql.*;
import java.sql.Date;
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
            Set<Reservation> reservations = new HashSet<Reservation>();
            while( result.next() ){
                //Requête qui permet de récupérer le type de la chambre afin de l'instancier
                PreparedStatement sql2 = this.connexion.prepareStatement("SELECT sigle FROM Categorie WHERE id IN (SELECT categorie FROM Chambre WHERE id = ?)");
                sql2.setInt(1, result.getInt(4));
                ResultSet result2 = sql2.executeQuery();
                result2.next();
                TypeChambre type = null;
                if (result2.getString(1).equals("UNLS")){
                    type = TypeChambre.UNLS;
                } else if (result2.getString(1).equals("DEUXLS")){
                    type = TypeChambre.DEUXLS;
                } else if (result2.getString(1).equals("UNLD")){
                    type = TypeChambre.UNLD;
                }
                //Création de la chambre 
                Chambre chambre = new ChambreP(result.getInt(4),type);
                //Requête qui permet de récupérer le nom et prénom du client afin de l'instancie
                PreparedStatement sql4 = this.connexion.prepareStatement("SELECT nom, prenom FROM Client WHERE id = ?");
                sql4.setInt(1, result.getInt(5));
                ResultSet result4 = sql4.executeQuery();
                result4.next();
                //Création du client 
                Client client =  new ClientP(result.getInt(5),result4.getString(2), result4.getString(1));
                reservations.add(new ReservationP(result.getString(1),result.getDate(2).toLocalDate(),result.getInt(3), chambre, client));
            }
            return reservations;
        }catch(SQLException e){
            throw new IllegalStateException("Problème de récupération des réservations");
        }
    }

    public Set<Chambre> getAllChambre(){
        try {
            //Requête qui permet de récupérer les chambres afin de l'instancier
            PreparedStatement sql = this.connexion.prepareStatement("SELECT id, categorie FROM Chambre");
            ResultSet result = sql.executeQuery();
            Set<Chambre> chambres = new HashSet<Chambre>();
            while( result.next() ){
                PreparedStatement sql2 = this.connexion.prepareStatement("SELECT sigle FROM Categorie WHERE id = ?");
                sql2.setInt(1, result.getInt(2));
                ResultSet result2 = sql2.executeQuery();
                result2.next();
                TypeChambre type2 = null;
                if (result2.getString(1).equals("UNLS")){
                    type2 = TypeChambre.UNLS;
                } else if (result2.getString(1).equals("DEUXLS")){
                    type2 = TypeChambre.DEUXLS;
                } else if (result2.getString(1).equals("UNLD")){
                    type2 = TypeChambre.UNLD;
                }
                chambres.add(new ChambreP(result.getInt(1), type2));
            } 
            return chambres;
        } catch (SQLException e) {
            throw new IllegalStateException("Problème de récupération des chambres.");
        }
    }

    public Set<Chambre> getAllChambreCategorie(TypeChambre type){
        try {
            //Requête qui permet de récupérer les chambres afin de l'instancier
            PreparedStatement sql = this.connexion.prepareStatement("SELECT id, categorie FROM Chambre WHERE categorie IN (SELECT id FROM Categorie WHERE sigle = ?)");
            String string = null;
            if (type == TypeChambre.UNLS){
            string = "UNLS";
            } else if (type == TypeChambre.DEUXLS){
            string = "DEUXLS";
            } else if (type == TypeChambre.UNLD){
            string = "UNLD";
            }
            sql.setString(1, string);
            ResultSet result = sql.executeQuery();
            Set<Chambre> chambres = new HashSet<Chambre>();
            while( result.next() ){
		Chambre chambre = new ChambreP(result.getInt(1), type);
                chambres.add(chambre);
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
            PreparedStatement sql3 = this.connexion.prepareStatement("SELECT sigle FROM Categorie WHERE id = ?");
            sql3.setInt(1, result_ch.getInt(2));
            ResultSet result3 = sql3.executeQuery();
            result3.next();
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
            int verif = 0;
            for (Reservation r : reservations){
                verif = 0;
                for(Chambre c : chambres){
                    //Si c'est le même type
                    if ( c.getType().equals(r.getChambre().getType())) {
                        //Si c'est la même date
                        if (p.getDateDebut().equals(r.getDateDebut())){
                            chambres.remove(c);
                            verif = 1;
                            //Si c'est pas la même date mais dans la reservation (nb de jour)
                        } else if ((r.getDateDebut().compareTo(p.getDateDebut()))<0 && r.getDateDebut().plusDays(r.getJours()).compareTo(p.getDateDebut())<=0 )  {
                            chambres.remove(c);
                            verif = 1;
                        }else{
                            chambre = c;
                            verif = 2;
                        }
                    } else {
                        chambre = c;
                        verif = 2;
                    }
                    //Permet de sortir si la chambre de la réservation a été trouvée
                    if (verif == 1){
                        break;
                    }
                }
                //Permet de sortir si une chambre disponible a été trouvée
                if (verif == 2){
                    break;
                }
            }
            if (chambre == null){
                for (Chambre c : chambres){
                    chambre = c;
                    break;
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
        Objects.requireNonNull(c,"La chambre est null.");
        try {
            //Requête pour récuperer le type de chambre de la prereservation
            PreparedStatement sql = this.connexion.prepareStatement("SELECT categorie FROM Prereservation WHERE reference = ?");
            sql.setString(1, p.getReference());
            ResultSet result = sql.executeQuery();
            result.next();

            //Requête pour récuperer le type de chambre de la chambre
            PreparedStatement sql1 = this.connexion.prepareStatement("SELECT categorie FROM Chambre WHERE id = ?");
            sql1.setInt(1, c.getNumero());
            ResultSet result2 = sql1.executeQuery();
            result2.next();
            if (result.getInt(1) != result2.getInt(1) ) {
                throw new IllegalArgumentException("Erreur sur le type de la chambre.");
            } else {
                try {
                    //Vérification que la chambre en argument soit toujours disponible
                    Set<Reservation> reservations = this.getAllReservation();
                    for(Reservation r : reservations){
                        //si c'est la meme chambre
                        if (c.getNumero() == r.getChambre().getNumero()) {
                            //Si c'est la même date
                            if (p.getDateDebut().equals(r.getDateDebut())){
                                throw new IllegalStateException("La chambre n'est plus disponible.");
                                //Si c'est pas la même date mais dans la reservation (nb de jour)
                            } else if ((r.getDateDebut().compareTo(p.getDateDebut()))<0 && r.getDateDebut().plusDays(r.getJours()).compareTo(p.getDateDebut())<0 )  {
                                throw new IllegalStateException("La chambre n'est plus disponible.");
                            }
                        }
                    }
                    PreparedStatement sql2 = this.connexion.prepareStatement("INSERT INTO Reservation (reference, debut, nuits, client, chambre) VALUES (?,?,?,?,?)");
                    sql2.setString(1, p.getReference());
                    sql2.setDate(2,Date.valueOf(p.getDateDebut()));
                    sql2.setInt(3, p.getJours());
                    sql2.setInt(4, p.getClient().getId());
                    sql2.setInt(5, c.getNumero());
                    sql2.executeUpdate();
                    PreparedStatement sql3 = this.connexion.prepareStatement("DELETE FROM Prereservation WHERE reference = ?");
                    sql3.setObject(1, p.getReference());
                    sql3.executeUpdate();
                    return (new ReservationP(p.getReference(), p.getDateDebut(), p.getJours(), c, p.getClient()));
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
        Objects.requireNonNull(d,"La date proposée est nulle.");
        Set<Reservation> reservations = this.getAllReservation();
        Set<Reservation> reser = new HashSet<Reservation>();
        for (Reservation r : reservations){
            if((r.getDateDebut().equals(d))){
                reser.add(r);
            } else if((r.getDateDebut().compareTo(d)<0 && r.getDateDebut().plusDays(r.getJours()).compareTo(d)<0)){
                reser.add(r);
            }
        }
        return reser;
    }

    /**
     * Cherche le nombre de chambres disponibles pour une date (réservées ou non).
     * @param  d une date
     * @return un entier
     * @throws NullPointerException si un argument est null
     *
     * Ne devrait pas retourner un entier négatif.
     */  
    @Override
    public int getDisponibles(LocalDate d) {
        Objects.requireNonNull(d,"La date proposée est nulle.");
        Set<Chambre> chambres = this.getAllChambre();
        //Réservées ou non ça veut dire toutes les chambres.
        return chambres.size();
    }

    /**
     * Cherche les réservations
     * @param  d une date
     * @param  t un type de chambre
     * @return la ou les réservation(s) pour ce type de chambre à cette date sous forme d'un ensemble
     * @throws NullPointerException si un argument est null
     *
     * Ne devrait pas retourner un objet null, par contre peut être un ensemble qui est vide.
     */ 
    @Override
    public Set<Reservation> getReservation(LocalDate d, TypeChambre t) {
        Objects.requireNonNull(d,"La date proposée est nulle.");
        Objects.requireNonNull(t,"Le tupe de chambre proposé est null.");
        Set<Reservation> reservations = this.getReservation(d);
        Set<Reservation> reser = new HashSet<Reservation>();
        for (Reservation r : reservations){
            try{
                //Recupération du type de chambre de la chambre de la réservation
                PreparedStatement sql = this.connexion.prepareStatement("SELECT sigle FROM Categorie WHERE id IN (SELECT Categorie FROM Chambre WHERE id = ?)");
                sql.setInt(1, r.getChambre().getNumero());
                ResultSet result = sql.executeQuery();
                result.next();
                TypeChambre type = null;
                //Identifiqation du type récupérer afin de l'instancier et 
                //de le comparer au type entré en argument
                if (result.getString(1).equals("UNLS")){
                    type = TypeChambre.UNLS;
                } else if (result.getString(1).equals("DEUXLS")){
                    type = TypeChambre.DEUXLS;
                } else if (result.getString(1).equals("UNLD")){
                    type = TypeChambre.UNLD;
                }

                if( type.equals(t)){
                    reser.add(r);
                }
            } catch (SQLException e) {
                throw new IllegalStateException("Problème de récupération des données de réservation.");
            }
        }
        return reser;
    }

    /**
     * Cherche le nombre de chambres disponibles d'un certain type pour une date (réservées ou non).
     * @param  d une date
     * @param  t un type de chambre
     * @return un entier
     * @throws NullPointerException si un argument est null
     *
     * Ne devrait pas retourner un entier négatif.
     */   
    @Override
    public int getDisponibles(LocalDate d, TypeChambre t) {
        Objects.requireNonNull(d,"La date proposée est nulle.");
        Objects.requireNonNull(t,"Le tupe de chambre proposé est null.");
        Set<Chambre> chambres = this.getAllChambreCategorie(t);
        //Réservées ou non c'est à dire toutes les chambres.
        return chambres.size();
    }

    /**
     * Cherche la proportion de chambres disponibles pour une date (réservées sur réservables).
     * @param  d une date
     * @return un entier entre 0 et 100
     * @throws NullPointerException si un argument est null
     */ 
    @Override
    public int getRatio(LocalDate d) {
        Objects.requireNonNull(d,"La date proposée est nulle.");
        int reservables = this.getDisponibles(d);
        Set<Reservation> reservations = this.getReservation(d);
        //Le nombre de chambre réservées correspond au nombre de reservation
        //car une reservation a obligatoirement une et une seule chambre
        int reservees = reservations.size();
        int ratio = (reservees*100)/reservables;
        return ratio;
    }

     /**
     * Cherche la proportion de chambres disponibles d'un certain type pour une date (réservées sur réservables).
     * @param  d une date
     * @param  t un type de chambre
     * @return un entier entre 0 et 100
     * @throws NullPointerException si un argument est null
     */ 
    @Override
    public int getRatio(LocalDate d, TypeChambre t) {
        Objects.requireNonNull(d,"La date proposée est nulle.");
        Objects.requireNonNull(t,"Le type proposé est null.");
        int reservables = this.getDisponibles(d, t);
        Set<Reservation> reservations = this.getReservation(d, t);
        int reservees = reservations.size();
        int ratio = (reservees*100)/reservables;
        return ratio;
    }

    /**
     * Cherche le nombre moyen de chambres disponibles entre deux date (réservées ou non), arrondies à l'entier inférieur.
     * @param  d1 une date
     * @param  d2 une date
     * @return un entier
     * @throws NullPointerException si un argument est null
     * @throws IllegalArgumentException si l'ordre temporel d1 avant d2 n'est pas respecté.
     *
     * Ne devrait pas retourner un entier négatif.
     */
    @Override
    public int getDisponibles(LocalDate d1, LocalDate d2) {
        Objects.requireNonNull(d1,"La première date proposée est nulle.");
        Objects.requireNonNull(d2,"La seconde date proposée est nulle.");
        if (d1.compareTo(d2)>0)
            throw new IllegalStateException("La première date doit être antérieur à la seconde.");
        int compteur = 0;
        System.out.println("Chargement en cours, veuillez patienter...");
        for (LocalDate i = d1; i.compareTo(d2)<=0; i = i.plusDays(1)){
            compteur += this.getDisponibles(i);
        }
        return compteur;
    }

    /**
     * Cherche les réservations
     * @param  d1 une date
     * @param  d2 une date
     * @param  t un type de chambre
     * @return la ou les réservation(s) pour ce type de chambre entre les dates sous forme d'un ensemble
     * @throws NullPointerException si un argument est null
     * @throws IllegalArgumentException si l'ordre temporel d1 avant d2 n'est pas respecté.
     *
     * Ne devrait pas retourner un objet null, par contre peut être un ensemble qui est vide.
     */ 
    @Override
    public Set<Reservation> getReservation(LocalDate d1, LocalDate d2, TypeChambre t) {
        Objects.requireNonNull(d1,"La première date proposée est nulle.");
        Objects.requireNonNull(d2,"La seconde date proposée est nulle.");
        Objects.requireNonNull(t,"Le type proposé est null.");
        if (d1.compareTo(d2)>0)
            throw new IllegalStateException("La première date doit être antérieur à la seconde.");
        Set<Reservation> reservations = this.getAllReservation();
        Set<Reservation> reser = new HashSet<Reservation>();
        int compteur = 0;
        for (Reservation r : reservations){
           try {
            //Permet de récupérer le type de chambre de la réservation
            PreparedStatement sql = this.connexion.prepareStatement("SELECT sigle FROM Categorie WHERE id IN(SELECT Categorie FROM Chambre WHERE id = ?)");
            sql.setInt(1, r.getChambre().getNumero());
            ResultSet result = sql.executeQuery();
            result.next();
            TypeChambre type = null;
            if (result.getString(1).equals("UNLS")){
                type = TypeChambre.UNLS;
            } else if (result.getString(1).equals("DEUXLS")){
                type = TypeChambre.DEUXLS;
            } else if (result.getString(1).equals("UNLD")){
                type = TypeChambre.UNLD;
            }
            if (type.equals(t)) {
                compteur = 0;
                //Permet de parcourir les reservations et leurs durées et voir si a un moment dans leur
                //durée elle sont dans la periode d1 d2
                for (int i=0; i<=r.getJours(); i++){
                    if (r.getDateDebut().plusDays(i).compareTo(d1)>=0 && r.getDateDebut().plusDays(i).compareTo(d2)<=0){
                        compteur++;
                    }
                }
                if (compteur != 0){
                    reser.add(r);
                }
            }
            } catch (SQLException e) {
                throw new IllegalStateException("Problème de récupération des chambres");
            }
        }
        return reser;
    }

    /**
     * Cherche le <b>nombre moyen</b> de chambres disponibles d'un certain type entre deux date (réservées ou non), arrondies à l'entier inférieur.
     * @param  d1 une date
     * @param  d2 une date
     * @param  t un type de chambre
     * @return un entier
     * @throws NullPointerException si un argument est null
     * @throws IllegalArgumentException si l'ordre temporel d1 avant d2 n'est pas respecté.
     *
     * Ne devrait pas retourner un entier négatif.
     */
    @Override
    public int getDisponibles(LocalDate d1, LocalDate d2, TypeChambre t) {
        Objects.requireNonNull(d1,"La première date proposée est nulle.");
        Objects.requireNonNull(d2,"La seconde date proposée est nulle.");
        Objects.requireNonNull(t,"Le type proposé est null.");
        if (d1.compareTo(d2)>0)
            throw new IllegalStateException("La première date doit être antérieur à la seconde.");
        Set<Chambre> chambres = this.getAllChambreCategorie(t);
        //Les chambres réservées ou non c'est à dire toutes les chambres du type t
        int compteur = 0;
        for (Chambre c : chambres)
            compteur++;
        return compteur;
    }

    /**
     * Cherche la <b>proportion moyenne</b> de chambres disponibles pour une date (réservées sur réservables).
     * @param  d1 une date
     * @param  d2 une date
     * @return un entier entre 0 et 100
     * @throws NullPointerException si un argument est null
     */ 
    @Override
    public int getRatio(LocalDate d1, LocalDate d2) {
        Objects.requireNonNull(d1,"La première date proposée est nulle.");
        Objects.requireNonNull(d2,"La seconde date proposée est nulle.");
        System.out.println("1 ratio");
        System.out.println("date d1 ="+d1);
        System.out.println("date d2 ="+d2);
        int reservables = this.getDisponibles(d1, d2);
        System.out.println("2 ratio");
        int reservees = 0;
        System.out.println("3 ratio");
        //Les reservations entre les deux dates avec pour type de chambre UNLS
        Set<Reservation> reservations = this.getReservation(d1, d2, TypeChambre.UNLS);
        System.out.println("4 ratio");
        //Les reservations entre les deux dates avec poour type de chambre DEUXLS
        for (Reservation r : this.getReservation(d1, d2, TypeChambre.DEUXLS)){
            if (reservations.contains(r)==false){
                reservations.add(r);
            }
        }
        System.out.println("5 ratio");
        //Les reservations entre les deux dates avec pour type de chambre UNLD
        for (Reservation r : this.getReservation(d1, d2, TypeChambre.UNLD)){
            if (reservations.contains(r)==false){
                reservations.add(r);
            }
        }
        System.out.println("6 ratio");
        reservees = reservations.size();
        System.out.println("7 ratio");
        int ratio = (reservees*100)/reservables;
        System.out.println("8 ratio");
        return ratio;
    }

    /**
     * Cherche la <b>proportion moyenne</b> de chambres disponibles d'un certain type pour une date (réservées sur réservables).
     * @param  d1 une date
     * @param  d2 une date
     * @param  t un type de chambre
     * @return un entier entre 0 et 100
     * @throws NullPointerException si un argument est null
     */
    @Override
    public int getRatio(LocalDate d1, LocalDate d2, TypeChambre t) {
        Objects.requireNonNull(d1,"La première date proposée est nulle.");
        Objects.requireNonNull(d2,"La seconde date proposée est nulle.");
        Objects.requireNonNull(t,"Le type proposé est null.");
        System.out.println("ratio date d1 ="+ d1);
        System.out.println("ratio date d2 ="+ d2);
        int reservables = this.getDisponibles(d1, d2);
        int reservees = 0;
        Set<Reservation> reservations = this.getReservation(d1, d2,t);
        for (Reservation r : reservations)
            //Le nombre de chambre réservées correspond au nombre de reservation
            //car une reservation a obligatoirement une et une seule chambre
            reservees++;
        int ratio = (reservees*100)/reservables;
        return ratio;
    }
}
