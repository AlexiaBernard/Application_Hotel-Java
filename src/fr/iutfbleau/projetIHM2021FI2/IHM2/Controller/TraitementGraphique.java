package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;
import java.time.*;
import java.time.format.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.ReservationFactory;
import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.VerificationDateGraphique;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

public class TraitementGraphique implements ActionListener {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param centre
     */
    public TraitementGraphique(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = JOptionPane.showInputDialog(this.fenetre,"Veuillez entrer la première date afin d'obtenir le graphique. (JJ/MM/AAAA)");
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate dateDeb = LocalDate.parse(input, inputFormat);
            Object[] options = {"1 sem", "1 mois", "3 mois", "Annuler" };
            int retour = JOptionPane.showOptionDialog(this.fenetre,
                                        "Sur quelle durée voulez vous faire le graphique ? ",
                                        "Choix de la durée",
                                        JOptionPane.OK_CANCEL_OPTION,
                                        JOptionPane.QUESTION_MESSAGE,
                                        null,
                                        options,
                                        options[0] );

            if (retour == 0 || retour == 1 || retour ==2){
                LocalDate dateFin = null;
                if (retour == 0){
                    dateFin = dateDeb.plusWeeks(1);
                    System.out.println("Trai date dateDeb ="+ dateDeb);
                    System.out.println("Trai date dateFin = "+dateFin);
                    System.out.println("Trai date +7j ="+ dateDeb.plusDays(7));
                } else if (retour == 1) {
                    dateFin = dateDeb.plusMonths(1);
                } else if (retour == 2) {
                    dateFin = dateDeb.plusMonths(3);
                }
                VerificationDateGraphique verif = new VerificationDateGraphique(this.fenetre, this.grandLivreDOrAPISeulement, dateDeb, dateFin, retour, this.centre);
                verif.run();
            } else {
                Bureau bureau = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
                bureau.run();
            }
        }catch (DateTimeParseException f){
            JOptionPane.showMessageDialog(this.fenetre,"Le format de la date n'est pas correct.");
                Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
                bur.run();
        }
    }
}
