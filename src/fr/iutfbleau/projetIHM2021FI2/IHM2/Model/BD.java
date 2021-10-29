package fr.iutfbleau.projetIHM2021FI2.IHM2.Model;

import java.sql.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.MNP.*;

public class BD {

    public ReservationFactory Connexion(){
        ReservationFactory grandLivreDOrAPISeulement  = new ReservationFactoryNP();
        return grandLivreDOrAPISeulement;
    }
}
