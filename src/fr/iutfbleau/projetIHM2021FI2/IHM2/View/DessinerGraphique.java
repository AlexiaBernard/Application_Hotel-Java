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
        int hauteur = this.getWidth()-150;
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
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.drawLine(40, 20, 35, 30);
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.drawLine(40, 20, 45, 30);

        
        //Les petits traits
        secondPinceau.setColor(Color.RED);
        secondPinceau.drawString("100", 10, trait);
        secondPinceau.drawLine(15, trait  , 25, trait);

        secondPinceau.setColor(Color.ORANGE);
        secondPinceau.drawString("90", 10, trait*2);
        secondPinceau.drawLine(15, trait*2, 25, trait*2);

        secondPinceau.setColor(Color.PINK);
        secondPinceau.drawString("80", 10, trait*3);
        secondPinceau.drawLine(15, trait*3, 25, trait*3);

        secondPinceau.setColor(Color.BLUE);
        secondPinceau.drawString("70", 10, trait*4);
        secondPinceau.drawLine(15, trait*4, 25, trait*4);

        secondPinceau.setColor(Color.GREEN);
        secondPinceau.drawString("60", 10, trait*5);
        secondPinceau.drawLine(15, trait*5, 25, trait*5);

        secondPinceau.setColor(Color.GRAY);
        secondPinceau.drawString("50", 10, trait*6);
        secondPinceau.drawLine(15, trait*6, 25, trait*6);

        secondPinceau.setColor(Color.DARK_GRAY);
        secondPinceau.drawString("40", 10, trait*7);
        secondPinceau.drawLine(15, trait*7, 25, trait*7);

        secondPinceau.setColor(Color.LIGHT_GRAY);
        secondPinceau.drawString("30", 10, trait*8);
        secondPinceau.drawLine(15, trait*8, 25, trait*8);

        secondPinceau.setColor(Color.MAGENTA);
        secondPinceau.drawString("20", 10, trait*9);
        secondPinceau.drawLine(15, trait*9, 25, trait*9);

        secondPinceau.setColor(Color.CYAN);
        secondPinceau.drawString("10", 10, (hauteur-40));
        secondPinceau.drawLine(15, (hauteur-40), 25, (hauteur-40));
        

        //La ligne horizontale
        secondPinceau.setColor(Color.YELLOW);
        secondPinceau.drawLine(40, hauteur-20, largeur-40, hauteur-20);

        /*
        //Premier rectangle (3 ans)
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.fillRect(40, hauteur-20, un*this.ratioComp, 40);
        secondPinceau.drawString("Ratio sur 3 ans", 50, hauteur-10);
        secondPinceau.drawString(""+this.ratioComp, 60, 10);

        //Deuxième rectangle (1 semaine, 1 mois ou 3 mois)
        secondPinceau.setColor(Color.BLACK);
        secondPinceau.fillRect(40+40+20, hauteur-20, un*this.ratio, 40);
        if (this.retour==0){
            secondPinceau.drawString("Ratio de la semaine demandée", 40+40+20+10, hauteur-10);
        } else if (this.retour==1){
            secondPinceau.drawString("Ratio du mois demandé", 40+40+20+10, hauteur-10);
        }else if (this.retour==2){
            secondPinceau.drawString("Ratio des trois mois demandés", 40+40+20+10, hauteur-10);
        }
        secondPinceau.drawString(""+this.ratio, 40+40+20+20, 10);
        */
    }
}
