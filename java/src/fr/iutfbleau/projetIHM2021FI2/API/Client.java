package fr.iutfbleau.projetIHM2021FI2.API;

/**
 * Un client
 */

public interface Client {

    /**
     * permet de récupérer l'identifiant du client (qu'on suppose être le même pour les différents systèmes, internes et externes à l'hôtel).
     * @return l'identifiant.
     */
    public int getId();

    /**
     * permet de récupérer 
     * @return le nom du client.
     */
    public String getNom();

    /**
     * permet de récupérer
     * @return le prénom du client
     */
    public String getPrenom();

}
