package fr.iutfbleau.projetIHM2021FI2.MNP;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import java.sql.*;
import java.util.Date;
import java.util.Set;

public class RechercheChambre implements ReservationFactory{

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

    @Override
    public Chambre getChambre(Prereservation p) {
        //permet de connaitre le type de chambre demndé par le client
        TypeChambre type = p.getTypeChambre();
        Connection cnx = Connexion();
		try{
            //Permet de savoir le nombre de chambre réservées comme celle que le client veut
			PreparedStatement numero1 = cnx.prepareStatement("SELECT Count(*) FROM Reservation WHERE categorie IN (SELECT id FROM Categorie WHERE sigle = ?)");
			numero1.setObject(1, type);
			numero1.executeUpdate();
			ResultSet rs1 = numero1.executeQuery();
			int resultat = rs1.getInt(1);
			Deconnexion(cnx);
			//return ...;
		}catch(SQLException a){
			System.err.println("Problème d'exécution de la requête SQL par reference");
		}
		Deconnexion(cnx);
		return null;
    }

    @Override
    public Set<Chambre> getChambres(Prereservation p) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Reservation createReservation(Prereservation p, Chambre c) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Set<Reservation> getReservation(Date d) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getDisponibles(Date d) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<Reservation> getReservation(Date d, TypeChambre t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getDisponibles(Date d, TypeChambre t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRatio(Date d) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRatio(Date d, TypeChambre t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getDisponibles(Date d1, Date d2) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Set<Reservation> getReservation(Date d1, Date d2, TypeChambre t) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int getDisponibles(Date d1, Date d2, TypeChambre t) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRatio(Date d1, Date d2) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int getRatio(Date d1, Date d2, TypeChambre t) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
