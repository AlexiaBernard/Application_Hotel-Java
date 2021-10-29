package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.*;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.*;

public class TraitementTauxType implements ActionListener {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param centre
     */
    public TraitementTauxType(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(this.fenetre,"Veuillez entrer la date du jour ou vous souhaitez avoir ce taux. (JJ/MM/AAAA)");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(input, inputFormat);
        System.out.println(date);
        Object[] options = {"1LS", "2LS", "1LD", "Annuler" };
        int retour = JOptionPane.showOptionDialog(this.fenetre,
                                    "De quel type voulez vous faire le ratio ?(L = lit, S = simple, D = double)",
                                    "Type de chambre",
                                    JOptionPane.OK_CANCEL_OPTION,
                                    JOptionPane.QUESTION_MESSAGE,
                                    null,
                                    options,
                                    options[0] );

        TypeChambre type = null;

        if (retour == 0 || retour == 1 || retour ==2){
            if (retour == 0){
                type = TypeChambre.UNLS;
            } else if (retour == 1) {
                type = TypeChambre.DEUXLS;
            } else if (retour == 2) {
                type = TypeChambre.UNLD;
            }
            VerificationDateType verif = new VerificationDateType(this.fenetre, this.grandLivreDOrAPISeulement, date, type, this.centre);
            verif.run();
        } else {
            Bureau bureau = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bureau.run();
        }

    }
    
}
