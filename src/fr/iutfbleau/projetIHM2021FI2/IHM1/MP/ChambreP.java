package fr.iutfbleau.projetIHM2021FI2.IHM1.MP;
import fr.iutfbleau.projetIHM2021FI2.API.*;
import java.util.*;
/**
 * Une chambre non persistante toute bête
 */

public class ChambreP implements Chambre{

    private int numero;
    private TypeChambre type;

    /**
     * Constructeur
     */
    public ChambreP(int numero, TypeChambre t){
        Objects.requireNonNull(t,"On ne peut pas créer une chambre avec un type de chambre à null.");
        this.numero=numero;
        this.type=t;
    }

    /**
     * permet de récupérer le numéro de la chambre.
     * @return le numéro.
     */
    public int getNumero(){
        return this.numero;
    }

    /**
     * @return le type de chambre (un type énuméré de l'API)
    */
    public TypeChambre getType(){
        return this.type;
    }

    @Override
    public boolean unLitSimple() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean deuxLitsSimples() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean unLitDouble() {
        // TODO Auto-generated method stub
        return false;
    }
}
