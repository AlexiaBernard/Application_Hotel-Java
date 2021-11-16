package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import java.time.LocalDate;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;

public class AfficherGraphique  {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;
    private LocalDate dateDeb;
    private LocalDate dateFin;
    private int retour;
    private int ratio;
    private int ratioComp;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param centre
     * @param dateDeb
     * @param dateFin
     * @param retour
     * @param ratio
     * @param ratioComp
     */
    public AfficherGraphique(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre,
            LocalDate dateDeb, LocalDate dateFin, int retour, int ratio, int ratioComp) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
        this.dateDeb = dateDeb;
        this.dateFin = dateFin;
        this.retour = retour;
        this.ratio = ratio;
        this.ratioComp = ratioComp;        
    }

    public void run() {
        System.out.println("dans afficher graphique");
        DessinerGraphique graphique = new DessinerGraphique(this.centre, this.retour, 
                this.ratio, this.ratioComp);
        this.centre.add(graphique);
        System.out.println("après dessiner graph");
        this.fenetre.repaint();
        this.fenetre.revalidate();
        this.fenetre.setVisible(true);
    }

    public void deleteCentre(JPanel centre) {
        centre.removeAll();
        this.fenetre.repaint();
        this.fenetre.revalidate();
        this.fenetre.setVisible(true);
    }
}
