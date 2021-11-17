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
        System.out.println("dans dessiner graphique");
        int hauteur = this.getWidth()-180;
        int largeur = this.fenetre.getHeight();
        int un = (hauteur-40)/100;

        Graphics secondPinceau = pinceau.create();
        //Pour pas transparent
        if (this.isOpaque()) {
            secondPinceau.setColor(this.getBackground());
            secondPinceau.fillRect(0, 0, this.getWidth(), this.getHeight());
        }
        //La ligne droite verticale
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.drawLine(40, 20, 40, hauteur-20);

        //La flèche au bout de la droite
        secondPinceau.drawLine(40, 20, 35, 30);
        secondPinceau.drawLine(40, 20, 45, 30);

        //Les petits traits
        secondPinceau.drawString("100", 10, un*10+5);
        secondPinceau.drawLine(35, un*10  , 45, un*10);

        secondPinceau.drawString("90", 10, un*10*2+5);
        secondPinceau.drawLine(35, un*10*2, 45, un*10*2);

        secondPinceau.drawString("80", 10, un*10*3+5);
        secondPinceau.drawLine(35, un*10*3, 45, un*10*3);

        secondPinceau.drawString("70", 10, un*10*4+5);
        secondPinceau.drawLine(35, un*10*4, 45, un*10*4);

        secondPinceau.drawString("60", 10, un*10*5+5);
        secondPinceau.drawLine(35, un*10*5, 45, un*10*5);

        secondPinceau.drawString("50", 10, un*10*6+5);
        secondPinceau.drawLine(35, un*10*6, 45, un*10*6);

        secondPinceau.drawString("40", 10, un*10*7+5);
        secondPinceau.drawLine(35, un*10*7, 45, un*10*7);

        secondPinceau.drawString("30", 10, un*10*8+5);
        secondPinceau.drawLine(35, un*10*8, 45, un*10*8);

        secondPinceau.drawString("20", 10, un*10*9+5);
        secondPinceau.drawLine(35, un*10*9, 45, un*10*9);

        secondPinceau.drawString("10", 10, (hauteur-40)+5);
        secondPinceau.drawLine(35, (hauteur-40), 45, (hauteur-40));
        

        //La ligne horizontale
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.drawLine(40, hauteur-20, 450, hauteur-20);

        
        //Premier rectangle (3 ans)
        secondPinceau.setColor(Color.BLACK);
        
        secondPinceau.drawString("Ratio sur 3 ans", 65, hauteur-5);
        if (ratioComp > 0){
            secondPinceau.setColor(Color.BLUE);
            secondPinceau.fillRect(80, hauteur-20-un*this.ratioComp, 40, un*this.ratioComp);
            secondPinceau.setColor(Color.BLACK);
            secondPinceau.drawString(""+this.ratioComp, 100, hauteur-20-un*this.ratioComp-10);
        } else if (ratioComp == 0){
            secondPinceau.drawString(""+this.ratioComp, 100, hauteur-30);
            secondPinceau.setColor(Color.BLUE);
            secondPinceau.fillRect(80, hauteur-20-30*un, 40, 30*un);
        }
        
        //Deuxième rectangle (1 semaine, 1 mois ou 3 mois)
        secondPinceau.setColor(Color.BLACK);
        if (this.retour==0){
            secondPinceau.drawString("Ratio de la semaine demandée", 220, hauteur-5);
        } else if (this.retour==1){
            secondPinceau.drawString("Ratio du mois demandé", 220, hauteur-5);
        }else if (this.retour==2){
            secondPinceau.drawString("Ratio des trois mois demandés", 220, hauteur-5);
        }
        if (ratio > 0){
            secondPinceau.setColor(Color.ORANGE);
            secondPinceau.fillRect(290, hauteur-20-30*un, 40, 30*un);
            //secondPinceau.fillRect(290, hauteur-20-un*this.ratio, 40, un*this.ratio);
            secondPinceau.setColor(Color.BLACK);
            secondPinceau.drawString(""+this.ratio, 310, hauteur-20-un*this.ratio-10);
        } else if(ratio == 0){
            secondPinceau.drawString(""+this.ratio, 310, hauteur-30);
        }
        
    }
}
