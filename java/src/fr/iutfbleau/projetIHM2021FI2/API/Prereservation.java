package fr.iutfbleau.projetIHM2021FI2.API;
import java.util.Date;
/**
 * Une préréservation
 * 
 * e.g. utilisé par le système de réservation externe à l'hôtel.
 * 
 */

public interface Prereservation {

    /**
     * permet de récupérer 
     * @return la référence.
     */
    public String getReference();

    /**
     * permet de récupérer 
     * @return la date de début
     *
     * A priori seule la date est importante, le reste est sans importance.
     */
    public Date getDateDebut();

    /**
     * permet de récupérer 
     * @return la durée en jours (mais comme un entier)
     */
    public int getJours();

    /**
     * permet de récupérer 
     * @return le type de chambre
     */
    public TypeChambre getTypeChambre();

    /**
     * permet de récupérer 
     * @return le client
     */
    public Client getClient();
}
