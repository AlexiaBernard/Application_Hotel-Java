package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import javax.swing.*;
import java.awt.*;

/**
 * <code>AfficherFin</code> est une vue
 * Elle affiche le graphique des ratio de l'hotel sur une période donnée
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class AfficherGraphique  {

    private JFrame fenetre;
    private JPanel centre;
    private int retour;
    private int ratio;
    private int ratioComp;

    /**
     * 
     * @param fenetre
     * @param centre
     * @param retour
     * @param ratio
     * @param ratioComp
     */
    public AfficherGraphique(JFrame fenetre, JPanel centre, int retour, int ratio, int ratioComp) {
        this.fenetre = fenetre;
        this.centre = centre;
        this.retour = retour;
        this.ratio = ratio;
        this.ratioComp = ratioComp;        
    }

    public void run() {
        Color fond = new Color(222,212,249);
        DessinerGraphique graphique = new DessinerGraphique(this.fenetre, this.retour, 
                this.ratio, this.ratioComp);
        this.fenetre.remove(this.centre);
        graphique.setName("graphique");
        graphique.setBackground(fond);
        this.fenetre.add(graphique, BorderLayout.CENTER);
        this.fenetre.repaint();
        this.fenetre.revalidate();
        this.fenetre.setVisible(true);
    }
}
