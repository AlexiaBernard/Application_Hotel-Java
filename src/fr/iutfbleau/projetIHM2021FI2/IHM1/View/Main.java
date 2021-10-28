package fr.iutfbleau.projetIHM2021FI2.IHM1.View;

import java.time.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.MNP.*;

public class Main {
 public static void main(String[] args) {

	JFrame fenetre = new JFrame("Menu");

	fenetre.setSize(500, 500);
	fenetre.setLocation(100, 100);
	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	// morceaux de modèle
	PrereservationFactoryNP bookingPointCom = new PrereservationFactoryNP();

	Client c1 =  new ClientNP(1,"Marine","Carpentier");
	Client c2 =  new ClientNP(2, "Aaron","Dumas");
	Client c3 =  new ClientNP(3, "Kimberley", "Leroux");	
	Client c4 =  new ClientNP(4, "Florentin", "Giraud");	
	Client c5 =  new ClientNP(5, "Martin", "Gillet");	

	bookingPointCom.ajoutePrereservation("4751-3708-LRFM", LocalDate.of(2018,1,5),1, TypeChambre.DEUXLS,c1);
	bookingPointCom.ajoutePrereservation("2436-3909-NXLL", LocalDate.of(2018,01,07),1, TypeChambre.UNLS,c1);
	bookingPointCom.ajoutePrereservation("1351-0775-BETZ", LocalDate.of(2018,01,05),2, TypeChambre.DEUXLS,c2);
	bookingPointCom.ajoutePrereservation("3440-0631-NFCU", LocalDate.of(2018,01,06),2, TypeChambre.UNLD,c2);
	bookingPointCom.ajoutePrereservation("1499-2254-DBIU", LocalDate.of(2018,01,04),2, TypeChambre.UNLS,c2);
	bookingPointCom.ajoutePrereservation("5660-8953-YKJO", LocalDate.of(2018,01,06),2, TypeChambre.DEUXLS,c5);

	// une fois que le modèle de PrereservationFactoryNP a du contenu, je peux le caster en l'interface PreservationFactory de l'API correspondante pour que la vue s'en serve.
	
	PrereservationFactory bookingPointComAPISeulement = bookingPointCom;
	JOptionPane.showMessageDialog(fenetre,"Les modèles de préréservation et de réservation sont prêts.");

	ReservationFactory grandLivreDOrAPISeulement  = new ReservationFactoryNP();

	Menu menu = new Menu(bookingPointComAPISeulement, grandLivreDOrAPISeulement,fenetre);
	menu.run();
 }    
}