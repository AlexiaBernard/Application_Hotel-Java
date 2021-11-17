package fr.iutfbleau.projetIHM2021FI2.IHM2.View;

import javax.swing.*;
import java.awt.*;

public class DessinerGraphique extends JComponent {

    private JPanel centre;
    private int retour;
    private int ratio;
    private int ratioComp;

    /**
     * 
     * @param centre
     * @param retour
     * @param ratio
     * @param ratioComp
     */
    public DessinerGraphique(JPanel centre, int retour, int ratio, int ratioComp) {
        super();
        this.centre = centre;
        this.retour = retour;
        this.ratio = ratio;
        this.ratioComp = ratioComp;
    }

    @Override
    protected void paintComponent(Graphics pinceau) {
        System.out.println("dans dessiner graphique");
        int hauteur = this.getWidth()-180;
        int largeur = this.getHeight();
        int trait = (hauteur-40)/10;
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
        secondPinceau.drawString("100", 10, trait+5);
        secondPinceau.drawLine(35, trait  , 45, trait);

        secondPinceau.drawString("90", 10, trait*2+5);
        secondPinceau.drawLine(35, trait*2, 45, trait*2);

        secondPinceau.drawString("80", 10, trait*3+5);
        secondPinceau.drawLine(35, trait*3, 45, trait*3);

        secondPinceau.drawString("70", 10, trait*4+5);
        secondPinceau.drawLine(35, trait*4, 45, trait*4);

        secondPinceau.drawString("60", 10, trait*5+5);
        secondPinceau.drawLine(35, trait*5, 45, trait*5);

        secondPinceau.drawString("50", 10, trait*6+5);
        secondPinceau.drawLine(35, trait*6, 45, trait*6);

        secondPinceau.drawString("40", 10, trait*7+5);
        secondPinceau.drawLine(35, trait*7, 45, trait*7);

        secondPinceau.drawString("30", 10, trait*8+5);
        secondPinceau.drawLine(35, trait*8, 45, trait*8);

        secondPinceau.drawString("20", 10, trait*9+5);
        secondPinceau.drawLine(35, trait*9, 45, trait*9);

        secondPinceau.drawString("10", 10, (hauteur-40)+5);
        secondPinceau.drawLine(35, (hauteur-40), 45, (hauteur-40));
        

        //La ligne horizontale
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.drawLine(40, hauteur-20, largeur-20, hauteur-20);

        
        //Premier rectangle (3 ans)
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.fillRect(40, hauteur-20, un*this.ratioComp, 40);
        secondPinceau.drawString("Ratio sur 3 ans", 50, hauteur-5);
        if (ratioComp > 0){
            secondPinceau.drawString(""+this.ratioComp, 60, un*this.ratioComp-10);
        } else if (ratioComp == 0){
            secondPinceau.drawString(""+this.ratioComp, 60, 1);
        }
        
        //Deuxième rectangle (1 semaine, 1 mois ou 3 mois)
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.fillRect(40+40+20, hauteur-20, un*this.ratio, 40);
        if (this.retour==0){
            secondPinceau.drawString("Ratio de la semaine demandée", 40+40+20+10, hauteur-5);
        } else if (this.retour==1){
            secondPinceau.drawString("Ratio du mois demandé", 40+40+20+10, hauteur-5);
        }else if (this.retour==2){
            secondPinceau.drawString("Ratio des trois mois demandés", 40+40+20+10, hauteur-5);
        }
        if (ratio  >0){
            secondPinceau.drawString(""+this.ratio, 40+40+20+20, un*this.ratioComp-10);
        } else if(ratio == 0){
            secondPinceau.drawString(""+this.ratio, 40+40+20+20, 1);
        }
        
    }
}
