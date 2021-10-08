package fr.iutfbleau.projetIHM2021FI2.MNP;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import java.util.*;
import java.sql.*;

/**
 * 
 */
public class RecherchePrereservation implements PrereservationFactory{

	/**
	 * 
	 * @return Connection
	 */
	public Connection Connexion(){
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			try{
				Connection cnx = DriverManager.getConnection(
					"jdbc:mariadb://dwarves.iut-fbleau.fr/phpmyadmin",
					"projetihm", "mhitejorp"
					);
					return cnx;
			}catch(SQLException f){
				System.err.println("Connexion impossible");
			}
		}catch(ClassNotFoundException e){
			System.err.println("La vérification de la connexion a échouée !");
		}
		return null;
	}

	public void Deconnexion(Connection cnx){
		try{
			cnx.close();
		}catch(SQLException e){
			System.err.println("Déconnexion impossible");
		}
	}

	/**
	 * 
	 */
	@Override
	public Prereservation getPrereservation(String r) {
		Connection cnx = Connexion();
		try{
			PreparedStatement numero1 = cnx.prepareStatement("SELECT * FROM Reservation WHERE reference = ?");
			numero1.setString(1, r);
			numero1.executeUpdate();
			ResultSet rs1 = numero1.executeQuery();
			Prereservation resultat = (Prereservation) rs1.getObject(1);
			Deconnexion(cnx);
			return resultat;
		}catch(SQLException a){
			System.err.println("Problème d'exécution de la requête SQL par reference");
		}
		Deconnexion(cnx);
		return null;
	}

	/**
	 * 
	 */
	@Override
	public Set<Prereservation> getPrereservations(String n, String p) {
		Connection cnx = Connexion();
		try{
			PreparedStatement numero2 = cnx.prepareStatement("SELECT * FROM Reservation WHERE Client IN (SELECT id FROM Client WHERE nom = ? AND prenom = ?)") ;
			numero2.setString(1, n);
			numero2.setString(2, p);
			numero2.executeUpdate();
			ResultSet rs2 = numero2.executeQuery();
			Set<Prereservation> resultat = new HashSet<Prereservation>();
			while(rs2.next()){
				resultat.add((Prereservation) rs2.getObject(1));
			}
			Deconnexion(cnx);
			return resultat;
		}catch(SQLException a){
			System.err.println("Problème d'exécution de la requête SQL par reference");
		}
		Deconnexion(cnx);
		return null;
	}

}