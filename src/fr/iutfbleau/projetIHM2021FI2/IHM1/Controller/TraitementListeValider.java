package fr.iutfbleau.projetIHM2021FI2.IHM1.Controller;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import fr.iutfbleau.projetIHM2021FI2.API.*;
import fr.iutfbleau.projetIHM2021FI2.IHM1.Model.*;

/**
 * <code>TraitementListe</code> est un contrôleur. Il gère le bouton
 * <b>Valider</b> après la liste de chambres disponibles
 * 
 * @author Enora GERMOND, Aléxia Bernard
 * @version 1.0
 */
public class TraitementListeValider implements ActionListener {

	private PrereservationFactory bookingPointComAPISeulement;
	private ReservationFactory grandLivreDOrAPISeulement;
	private JFrame fenetre;
	private Prereservation prereservation;
	private ButtonGroup group;
	private Set<Chambre> disponibles;
	private JPanel centre;

	/**
	 * Constructeur permettant d'accéder à la chambre sélectionnée dans la liste des
	 * chambres libres par la suite
	 * 
	 * @param bookingPointComAPISeulement interface PreservationFactory de l'API correspondant à la préreservation
	 * @param grandLivreDOrAPISeulement   modèle non persistant de Réservation
	 * @param fenetre                     la fenetre
	 * @param prereservation              la préreservation
	 * @param group                       Boutons-radio
	 * @param disponibles                 chambres disponibles
	 * @param centre                      centre de la fenetre
	 */
	public TraitementListeValider(PrereservationFactory bookingPointComAPISeulement,
			ReservationFactory grandLivreDOrAPISeulement, JFrame fenetre, Prereservation prereservation,
			ButtonGroup group, Set<Chambre> disponibles, JPanel centre) {
		this.bookingPointComAPISeulement = bookingPointComAPISeulement;
		this.grandLivreDOrAPISeulement = grandLivreDOrAPISeulement;
		this.fenetre = fenetre;
		this.prereservation = prereservation;
		this.group = group;
		this.disponibles = disponibles;
		this.centre = centre;
	}

	/**
	 * Lors du clic sur le bouton
     * @param e the action data
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String retour = group.getSelection().getActionCommand();
		String val = null;
		Chambre choisi = null;

		for(Chambre c : this.disponibles){
			val =""+c.getNumero();
			if(val.equals(retour)){
				choisi = c;
				break;
			}
		}
		if (choisi != null) {
			AjoutReservation reser = new AjoutReservation(this.bookingPointComAPISeulement,
					this.grandLivreDOrAPISeulement, this.fenetre, choisi, prereservation, this.centre, this.disponibles,
					0);
			reser.run();
		} else {
			JOptionPane.showMessageDialog(this.fenetre, "Problème avec la chambre selectionnée.");
			TraitementFin fin = new TraitementFin(this.grandLivreDOrAPISeulement, this.fenetre);
			fin.run();
		}     
	}
}
 