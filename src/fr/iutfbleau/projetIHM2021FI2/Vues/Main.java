package fr.iutfbleau.projetIHM2021FI2.Vues;
import javax.swing.*;

public class Main {
 public static void main(String[] args) {

	JFrame fenetre = new JFrame("Menu");

	fenetre.setSize(800, 600);
	fenetre.setLocation(0, 0);
	fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	new Menu(fenetre, 0);
 }    
}
