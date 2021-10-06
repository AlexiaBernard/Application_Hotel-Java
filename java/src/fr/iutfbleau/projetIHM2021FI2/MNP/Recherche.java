package fr.iutfbleau.projetIHM2021FI2.MNP;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import java.util.*;
import java.sql.*;
/**
*
*/

public class Recherche implements PrereservationFactory{

	public boolean Connexion(){
		try{
			Class.forName("org.mariadb.jdbc.Driver");
			try{
				Connection cnx = DriverManager.getConnection(
					"jdbc:mariadb://dwarves.iut-fbleau.fr/phpmyadmin",
					"projetihm", "mhitejorp"
					);
				
				return true;

			}catch(SQLException f){
				System.err.println("Connexion impossible");
			}
		}catch(ClassNotFoundException e){
			System.err.println("La vérification de la connexion a échouée !");
		}
	}

	public boolean Deconnexion(Connection cnx){
		if (cnx.close()){
			return true;
		} else
			return false; 
	}

}