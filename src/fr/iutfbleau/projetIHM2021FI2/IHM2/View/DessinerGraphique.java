package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import javax.swing.*;
import java.awt.*;

public class DessinerGraphique extends JComponent {

    private JFrame fenetre;
    private int retour;
    private int ratio;
    private int ratioComp;

    /**
     * 
     * @param fenetre
     * @param retour
     * @param ratio
     * @param ratioComp
     */
    public DessinerGraphique(JFrame fenetre, int retour, int ratio, int ratioComp) {
        super();
        this.fenetre = fenetre;
        this.retour = retour;
        this.ratio = ratio;
        this.ratioComp = ratioComp;
    }

    @Override
    protected void paintComponent(Graphics pinceau) {
        int decHaut = 50;
        int decBas = 20;
        int hauteur = this.getWidth()-180;
        int un = (hauteur-decHaut-decBas)/100;

        Graphics secondPinceau = pinceau.create();
        //Pour pas transparent
        if (this.isOpaque()) {
            secondPinceau.setColor(this.getBackground());
            secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        //La ligne droite verticale
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.drawLine(40, decHaut, 40, decHaut+un*110);

        //La flèche au bout de la droite
        secondPinceau.drawLine(40, decHaut, 35, decHaut+10);
        secondPinceau.drawLine(40, decHaut, 45, decHaut+10);
        secondPinceau.drawString("(en %)", 25, decHaut-7);

        //Les petits traits
        secondPinceau.drawString("100", 7, decHaut+un*10+5);
        secondPinceau.drawLine(35, decHaut+un*10, 45, decHaut+un*10);

        secondPinceau.drawString("90", 10, decHaut+un*20+5);
        secondPinceau.drawLine(35, decHaut+un*20, 45, decHaut+un*20);

        secondPinceau.drawString("80", 10, decHaut+un*30+5);
        secondPinceau.drawLine(35, decHaut+un*30, 45, decHaut+un*30);

        secondPinceau.drawString("70", 10, decHaut+un*40+5);
        secondPinceau.drawLine(35, decHaut+un*40, 45, decHaut+un*40);

        secondPinceau.drawString("60", 10, decHaut+un*50+5);
        secondPinceau.drawLine(35, decHaut+un*50, 45, decHaut+un*50);

        secondPinceau.drawString("50", 10, decHaut+un*60+5);
        secondPinceau.drawLine(35, decHaut+un*60, 45, decHaut+un*60);

        secondPinceau.drawString("40", 10, decHaut+un*70+5);
        secondPinceau.drawLine(35, decHaut+un*70, 45, decHaut+un*70);

        secondPinceau.drawString("30", 10, decHaut+un*80+5);
        secondPinceau.drawLine(35, decHaut+un*80, 45, decHaut+un*80);

        secondPinceau.drawString("20", 10, decHaut+un*90+5);
        secondPinceau.drawLine(35, decHaut+un*90, 45, decHaut+un*90);

        secondPinceau.drawString("10", 10, decHaut+un*100+5);
        secondPinceau.drawLine(35, decHaut+un*100, 45, decHaut+un*100);
        

        //La ligne horizontale
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.drawLine(40, decHaut+un*110, 450, decHaut+un*110);

        //Premier rectangle (3 ans)
        secondPinceau.setColor(Color.BLACK);
        
        secondPinceau.drawString("Ratio sur 3 ans", 65, decHaut+un*110+15);
        if (ratioComp > 0){
            secondPinceau.setColor(Color.BLUE);
            secondPinceau.fillRect(80, decHaut+un*110-un*this.ratioComp, 40, un*this.ratioComp);
            secondPinceau.setColor(Color.BLACK);
            secondPinceau.drawString(""+this.ratioComp, 90, decHaut+un*110-un*this.ratioComp-10);
        } else if (ratioComp == 0){
            secondPinceau.drawString(""+this.ratioComp, 90, decHaut+un*110-10);
        }
        
        //Deuxième rectangle (1 semaine, 1 mois ou 3 mois)
        secondPinceau.setColor(Color.BLACK);
        if (this.retour==0){
            secondPinceau.drawString("Ratio de la semaine demandée", 220, decHaut+un*110+15);
        } else if (this.retour==1){
            secondPinceau.drawString("Ratio du mois demandé", 220, decHaut+un*110+15);
        }else if (this.retour==2){
            secondPinceau.drawString("Ratio des trois mois demandés", 220, decHaut+un*110+15);
        }
        if (ratio > 0){
            secondPinceau.setColor(Color.ORANGE);
            secondPinceau.fillRect(290, decHaut+un*110-un*this.ratio, 40, un*this.ratio);
            secondPinceau.setColor(Color.BLACK);
            secondPinceau.drawString(""+this.ratio, 300, decHaut+un*110-this.ratio*un-10);
        } else if(ratio == 0){
            secondPinceau.drawString(""+this.ratio, 300,  decHaut+un*110-10);
        }
    }
}
