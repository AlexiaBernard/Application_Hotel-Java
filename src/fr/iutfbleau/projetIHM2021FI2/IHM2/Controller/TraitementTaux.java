package fr.iutfbleau.projetIHM2021FI2.IHM2.Controller;

import java.awt.event.*;
import java.text.*;
import java.time.*;
import java.time.format.*;

import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.IHM2.Model.*;

public class TraitementTaux implements ActionListener {

    private JFrame fenetre;
    private BD baseDeDonnées;

    /**
     * 
     * @param fenetre
     * @param baseDeDonnées
     */
    public TraitementTaux(JFrame fenetre, BD baseDeDonnées) {
        this.fenetre = fenetre;
        this.baseDeDonnées = baseDeDonnées;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String input = JOptionPane.showInputDialog(this.fenetre,"Veuillez entrer la date du jour ou vous souhaitez avoir ce taux. (JJ/MM/AAAA)");
        DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date = LocalDate.parse(input, inputFormat);
        System.out.println(date);
        VerificationDate verif = new VerificationDate(this.fenetre, this.baseDeDonnées, date);
        verif.run();

    }
    
}
