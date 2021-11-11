package fr.iutfbleau.projetIHM2021FI2.MP;

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
                //Requête qui permet de récupérer la catégorie de la Chambre afin de l'instancier
                PreparedStatement sql2 = this.connexion.prepareStatement("SELECT categorie FROM Chambre WHERE id = ?");
                sql2.setInt(1, result.getInt(4));
                ResultSet result2 = sql2.executeQuery();
                result2.next();
                //Requête qui permet de récupérer le type de la chambre afin de l'instancier
                PreparedStatement sql3 = this.connexion.prepareStatement("SELECT single FROM TypeChambre WHERE id = ?");
                sql3.setInt(1, result2.getInt(1));
                ResultSet result3 = sql3.executeQuery();
                result3.next();
                //Requête qui permet de récupérer le nom et prénom du client afin de l'instancie
                PreparedStatement sql4 = this.connexion.prepareStatement("SELECT nom, prenom FROM Client WHERE id = ?");
                sql4.setInt(1, result.getInt(5));
                ResultSet result4 = sql4.executeQuery();
                result4.next();
                TypeChambre type = null;
                if (result3.getString(1).equals("UNLS")){
                    type = TypeChambre.UNLS;
                } else if (result3.getString(1).equals("DEUXLS")){
                    type = TypeChambre.DEUXLS;
                } else if (result3.getString(1).equals("UNLD")){
                    type = TypeChambre.UNLD;
                }
                //Création de la chambre 
                Chambre chambre = new ChambreP(result.getInt(4),type);
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
            Set<Chambre> chambres = new HashSet<>();
            while( result.next() ){
                PreparedStatement sql2 = this.connexion.prepareStatement("SELECT single FROM Categorie WHERE id = ?");
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
	    System.out.println("sql fait , 1");
	    String string = null;
	    if (type == TypeChambre.UNLS){
		string = "UNLS";
		System.out.println(string);
	    } else if (type == TypeChambre.DEUXLS){
		string = "DEUXLS";
		System.out.println(string);
	    } else if (type == TypeChambre.UNLD){
		string = "UNLD";
		System.out.println(string);
	    }
            sql.setString(1, string);
	    System.out.println("setString fait");
            ResultSet result = sql.executeQuery();
	    System.out.println("result fait");
            Set<Chambre> chambres = new HashSet<Chambre>();
	    System.out.println("chalbres fait");
            while( result.next() ){
		System.out.println("entre dans boucle");
		Chambre chambre = new ChambreP(result.getInt(1), type);
		System.out.println("Chambre créé, 4");
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
	    System.out.println("1");
            Set<Reservation> reservations = this.getAllReservation();
	    System.out.println("reservatons fait, 2");
            Set<Chambre> chambres = this.getAllChambreCategorie(p.getTypeChambre());
	    System.out.println("chambres faites : 3");
            Chambre chambre = null;
	    System.out.println("chambre null, 4");
            for (Reservation r : reservations){
                for(Chambre c : chambres){
                    //Si c'est le même type
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
                    } else {
                        chambres.remove(c);
                    }
                }
            }
	    if (chambre == null){
		System.out.println("null oui");
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
                try {
                    //Vérification que la chambre en argument soit toujours disponible
                    Set<Reservation> reservations = this.getAllReservation();
                    for(Reservation r : reservations){
                        if ( c.getType().equals(r.getChambre().getType())) {
                            //Si c'est la même date
                            if (p.getDateDebut().equals(r.getDateDebut())){
                                throw new IllegalStateException("La chambre n'est plus disponible.");
                                //Si c'est pas la même date mais dans la reservation (nb de jour)
                            } else if ((r.getDateDebut().compareTo(p.getDateDebut()))<0 && r.getDateDebut().plusDays(r.getJours()).compareTo(p.getDateDebut())<=0 )  {
                                throw new IllegalStateException("La chambre n'est plus disponible.");
                            }
                        }
                    }
                    sql = this.connexion.prepareStatement("INSERT INTO Reservation VALUES (?,?,?,?,?)");
                    sql.setObject(1, p.getReference());
                    sql.setObject(2, p.getDateDebut());
                    sql.setInt(3, p.getJours());
                    sql.setObject(4, p.getClient());
                    sql.setInt(5, c.getNumero());
                    sql.executeUpdate();
                    sql = this.connexion.prepareStatement("DELETE FROM Prereservation WHERE reference = ?");
                    sql.setObject(1, p.getReference());
                    sql.executeUpdate();
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
        for (Reservation r : reservations){
            if(!(r.getDateDebut().equals(d))){
                if( !(r.getDateDebut().compareTo(d)<0 && r.getDateDebut().plusDays(r.getJours()).compareTo(d)<=0)){
                    reservations.remove(r);
                }
            }
        }
        return reservations;
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
        int compteur = 0;
        Set<Chambre> chambres = this.getAllChambre();
        //Réservées ou non ça veut dire toutes les chambres.
        for (Chambre c : chambres){
            compteur++;
        }
        return compteur;
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
        for (Reservation r : reservations){
            try{
                //Recupération du type de chambre de la chambre de la réservation
                PreparedStatement sql = this.connexion.prepareStatement("SELECT single FROM Categorie WHERE id IN(SELECT Categorie FROM Chambre WHERE id = ?)");
                sql.setObject(1, r.getChambre());
                ResultSet result = sql.executeQuery();
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
                //Si le type de la réservation et le type d'argument n'est pas le même
                //on le supprime de de l'ensemble
                if( !type.equals(t)){
                    reservations.remove(r);
                }
            } catch (SQLException e) {
                throw new IllegalStateException("Problème de récupération des données de réservation.");
            }
        }
        return reservations;
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
        int compteur = 0;
        Set<Chambre> chambres = this.getAllChambreCategorie(t);
        //Réservées ou non c'est à dire toutes les chambres.
        for (Chambre c : chambres){
            compteur++;
        }
        return compteur;
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
        int reservees = 0;
        Set<Reservation> reservations = this.getReservation(d);
        for (Reservation r : reservations)
            //Le nombre de chambre réservées correspond au nombre de reservation
            //car une reservation a obligatoirement une et une seule chambre
            reservees++;
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
        int reservees = 0;
        Set<Reservation> reservations = this.getReservation(d, t);
        for (Reservation r : reservations)
            reservees++;
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
        for (LocalDate i = d1; d1.compareTo(d2)<0; i.plusDays(1)){
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
        Set<Chambre> chambres = this.getAllChambreCategorie(t);
        int compteur = 0;
        for (Reservation r : reservations){
           try {
            //Permet de récupérer le type de chambre de la réservation
            PreparedStatement sql = this.connexion.prepareStatement("SELECT single FROM Categorie WHERE id IN(SELECT Categorie FROM Chambre WHERE id = ?)");
            sql.setObject(1, r.getChambre());
            ResultSet result = sql.executeQuery();
            TypeChambre type = null;
            if (result.getString(1).equals("UNLS")){
                type = TypeChambre.UNLS;
            } else if (result.getString(1).equals("DEUXLS")){
                type = TypeChambre.DEUXLS;
            } else if (result.getString(1).equals("UNLD")){
                type = TypeChambre.UNLD;
            }
            if ( type.equals(t)) {
                compteur = 0;
                //Permet de parcourir les reservations et leur durée et voir si a un moment dans leur
                //durée elle sont dans la periode d1 d2
                for (int i=0; i<=r.getJours(); i++){
                    if (r.getDateDebut().plusDays(i).compareTo(d1)>=0 && r.getDateDebut().plusDays(i).compareTo(d2)<=0){
                        compteur++;
                    }
                }
                if (compteur == 0){
                    reservations.remove(r);
                }
            } else {
                reservations.remove(r);
            }
           } catch (SQLException e) {
                throw new IllegalStateException("Problème de récupération des chambres");
           }
        }
        return reservations;
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
        int reservables = this.getDisponibles(d1, d2);
        int reservees = 0;
        //Les reservations entre les deux dates avec pour type de chambre UNLS
        Set<Reservation> reservations = this.getReservation(d1, d2, TypeChambre.UNLS);
        //Les reservations entre les deux dates avec poour type de chambre DEUXLS
        for (Reservation r : this.getReservation(d1, d2, TypeChambre.DEUXLS))
            reservations.add(r);
        //Les reservations entre les deux dates avec pour type de chambre UNLD
        for (Reservation r : this.getReservation(d1, d2, TypeChambre.UNLD))
            reservations.add(r);
        for (Reservation r : reservations)
            //Le nombre de chambre réservées correspond au nombre de reservation
            //car une reservation a obligatoirement une et une seule chambre
            reservees++;
        int ratio = (reservees*100)/reservables;
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
