package fr.iutfbleau.projetIHM2021FI2.API;
/**
 * Une chambre
 */

public interface Chambre {

    /**
     * permet de récupérer le numéro de la chambre.
     * @return le numéro.
     */
    public int getNumero();

    /**
     * permet de savoir si la chambre a un seul lit qui est simple
     * @return vrai si c'est le cas.
     */
    public boolean unLitSimple();

    /**
     * permet de savoir si la chambre a deux lits simples
     * @return vrai si c'est le cas.
     */
    public boolean deuxLitsSimples();

    /**
     * permet de savoir si la chambre a un lit double
     * @return vrai si c'est le cas.
     */
    public boolean unLitDouble();

}
