package fr.iutfbleau.projetIHM2021FI2.Test;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.MNP.RecherchePrereservation;

import static org.junit.Assert.assertTrue; // import static : une facilité offerte depuis java5 (pas besoin de mettre le préfixe)
import static org.junit.Assert.assertFalse; 
import org.junit.Test;


    /**
 * Une classe pour faire des tests sur la classe RecherchePrereservation avec JUnit
 */
public class TestRecherchePrereservation {
    
//---------------------getPrereservation(String)---------------------//

    @Test(expected = NullPointerException.class)
    public void argPasFaitPourNull(){
        RecherchePrereservation test = new RecherchePrereservation();
        test.getPrereservation(null);
    }

    @Test(expected = IllegalStateException.class)
    public void argPasFaitPourInexistant(){
        RecherchePrereservation test = new RecherchePrereservation();
        test.getPrereservation("Bonjour");
    }


//---------------------getPrereservations(String, String)---------------------//

    @Test(expected = NullPointerException.class)
    public void nomPasFaitPourNull(){
        RecherchePrereservation test = new RecherchePrereservation();
        test.getPrereservations(null, "Marine");
    }

    @Test(expected = NullPointerException.class)
    public void prenomPasFaitPourNull(){
        RecherchePrereservation test = new RecherchePrereservation();
        test.getPrereservations("Carpentier",null);
    }

    @Test(expected = IllegalStateException.class)
    public void argsPasFaitPourInexistant(){
        RecherchePrereservation test = new RecherchePrereservation();
        test.getPrereservations("Bernard", "Aléxia");
    }
}
}
