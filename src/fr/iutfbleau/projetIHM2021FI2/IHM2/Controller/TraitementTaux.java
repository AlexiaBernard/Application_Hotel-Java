package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;
import java.time.*;
import java.time.format.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.*;
import fr.iutfbleau.projetIHM2021FI2.IHM2.View.Bureau;

public class TraitementTaux implements ActionListener {

    private JFrame fenetre;
    private ReservationFactory grandLivreDOrAPISeulement;
    private JPanel centre;

    /**
     * 
     * @param fenetre
     * @param grandLivreDOrAPISeulement
     * @param centre
     */
    public TraitementTaux(JFrame fenetre, ReservationFactory grandLivreDOrAPISeulement, JPanel centre) {
        this.fenetre = fenetre;
        this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
        this.centre = centre;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String input = JOptionPane.showInputDialog(this.fenetre,"Veuillez entrer la date du jour ou vous souhaitez avoir ce taux. (JJ/MM/AAAA)");
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate date = LocalDate.parse(input, inputFormat);
            VerificationDate verif = new VerificationDate(this.fenetre, this.grandLivreDOrAPISeulement, date, this.centre);
            verif.run();
        } catch (DateTimeParseException f) {
            JOptionPane.showMessageDialog(this.fenetre,"Le format de la date n'est pas correct.");
            Bureau bur = new Bureau(this.fenetre, this.grandLivreDOrAPISeulement, this.centre);
            bur.run();
        }
    } 
}
