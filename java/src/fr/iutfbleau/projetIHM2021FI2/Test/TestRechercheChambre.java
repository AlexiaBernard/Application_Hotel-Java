package fr.iutfbleau.projetIHM2021FI2.Test;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.MNP.RechercheChambre;
import static org.junit.Assert.assertTrue; // import static : une facilité offerte depuis java5 (pas besoin de mettre le préfixe)
import static org.junit.Assert.assertFalse; 
import org.junit.Test;
import java.util.*;


/**
 * Une classe pour faire des tests sur la classe RechercheChambre avec JUnit
 */
public class TestRechercheChambre {
    

//---------------------getChambre(Preresrvation)---------------------//

    @Test(expected = NullPointerException.class)
    public void argPasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        test.getChambre(null);
    }

/* si une chambre correspondant à cette Préréservation n'existe pas : à faire
Lorsque compris comment se compose Prereservation
   @Test(expected = IllegalStateException.class)
    public void argPasFaitPourInexistant(){
        RechercheChambre test = new RechercheChambre();
        test.getChambre(...);
    }
*/

//---------------------getChambres(Prereservaton)---------------------//

    @Test(expected = NullPointerException.class)
    public void nomPasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        test.getChambres(null);
    }

/* si une chambre correspondant à cette Préréservation n'existe pas.  : à faire
Lorsque compris comment se compose Prereservation
    @Test(expected = IllegalStateException.class)
    public void prenomPasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        test.getChambres(...);
    }
*/

//---------------------createReservation(Prereservation, Chambre)---------------------//

/*
    @Test(expected = NullPointerException.class)
    public void prereservationPasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        Chambre c; //à faire en sorte que la chambre existe dans la BD
        test.createReservation(null, c);
    }
*/

/*
    @Test(expected = NullPointerException.class)
    public void chambrePasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        Prereservation p; //à faire en sorte que la preresrvation existe dans la BD
        test.createReservation(p, null);
    }
*/

/*
    @Test(expected = IllegalArgumentException.class)
    public void chambrePasFaitPourTypeDifferent(){
        RechercheChambre test = new RechercheChambre();
        Chambre c; //à faire en sorte qu'elle soit différente du type de la prereservation
        Prereservation p; //à faire en sorte que la prereservation existe dans la BD
        test.createReservation(p, c);
    }
*/

/*
    @Test(expected = IllegalStateException.class)
    public void chambrePasFaitPourDejaReservee(){
        RechercheChambre test = new RechercheChambre();
        Chambre c; //à faire en sorte qu'elle soit déjà reservée
        Prereservation p; //à faire en sorte que la prereservation existe dans la BD
        test.createReservation(p, c);
    }
*/

//---------------------getReservation(Date)---------------------//

    @Test(expected = NullPointerException.class)
    public void dateReservationPasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        test.getReservation(null);
    }


//---------------------getDisponibles(Date)---------------------//

    @Test(expected = NullPointerException.class)
    public void dateDisponiblePasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        test.getDisponibles(null);
    }

//---------------------getReservation(Date, TypeChambre)---------------------//

/*
    @Test(expected = NullPointerException.class)
    public void dateReservation2PasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        TypeChambre t; //faire en sorte qu'il existe dans la BD
        test.getReservation(null, t);
    }
*/

/*
    @Test(expected = NullPointerException.class)
    public void TypeChambreReservationPasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        Date d; //faire en sorte qu'elle existe dans la BD
        test.getReservation(d, null);
    }
*/
    
//---------------------getDisponibles(Date, TypeChambre)---------------------//

/*
    @Test(expected = NullPointerException.class)
    public void dateDisponible2PasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        TypeChambre t; //faire en sorte qu'il existe dans la BD
        test.getDisponibles(null, t);
    }
*/

/*
    @Test(expected = NullPointerException.class)
    public void TypeChambreDisponiblePasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        Date d; //faire en sorte qu'elle existe dans la BD
        test.getDisponibles(d, null);
    }
*/

//---------------------getRatio(Date)---------------------//

    @Test(expected = NullPointerException.class)
    public void dateRatioPasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        test.getRatio(null);
    }

//---------------------getRatio(Date, TypeChambre)---------------------//

/*
    @Test(expected = NullPointerException.class)
    public void dateRatio2PasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        TypeChambre t; //faire en sorte qu'il existe dans la BD
        test.getRatio(null, t);
    }
*/

/*
    @Test(expected = NullPointerException.class)
    public void typeChambreRatioPasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        Date d; //faire en sorte qu'elle existe dans la BD
        TypeChambre t = null;
        test.getRatio(d, t);
    }
*/

//---------------------getDisponibles(Date, Date)---------------------//

/*
    @Test(expected = NullPointerException.class)
    public void dateDisponible3PasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        Date d2; //faire en sorte qu'il existe dans la BD
        Date d1 = null;
        test.getDisponibles(d1, d2);
    }
*/

/*
    @Test(expected = NullPointerException.class)
    public void DateDisponibl4PasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        Date d1; //faire en sorte qu'elle existe dans la BD
        Date d2 = null;
        test.getDisponibles(d1,d2);
    }
*/

/* Ici faire lorsque trouver comment faire pour donner une date Date + utiliser données de la BD
    @Test(expected = IllegalArgumentException.class)
    public void DateDisponibl4PasFaitPourNull(){
        RechercheChambre test = new RechercheChambre();
        Date d1;
        Date d2;
        //d2 doit etre avant d1
        test.getDisponibles(d1,d2);
    }
*/



    
}
