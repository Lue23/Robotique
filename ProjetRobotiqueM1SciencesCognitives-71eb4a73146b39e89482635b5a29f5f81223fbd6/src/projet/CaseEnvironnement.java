package projet;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.Color;

/**
 * <b>
 * Classe permettant de d�finir l'environnement du robot (diff�rentes cases de la carte)
 * </b>
 * 
 * <p>
 * Cette classe permet de param�trer divers �l�ments correspondant aux diff�rentes cases de la carte. 
 * Ces derni�res sont d�finis par leur couleur, qui correspond � leur co�t pour le robot, 
 * leur abscisse, et leur ordonn�e. 
 * On indique �galement la taille des cases. 
 * </p>
 * 
 *
 */

public class CaseEnvironnement {
	
	private int color;
	private int SIZE;
	private int cost;
	private int abs;
	private int ord;
	
	/**
	 * Permet de d�finir la couleur et les coordonn�es des cases. 
	 * D�finit �galement le co�t en fonction de la couleur (switch case)
	 * 
	 * @param col Couleur de la case
	 * @param x Abscisse de la case
	 * @param y Ordonn�e de la case 
	 * 
	 * 
	 */
	
	public CaseEnvironnement(int col, int x, int y){
		this.color = col;
		this.SIZE = 12;
		this.abs = x;
		this.ord = y;
		
		switch(col) {
		  case 0: // red
			  this.cost = 1;
			  break;
		  case 1 : // green
			 this.cost = 1;
			 break;
		  case 5 : // orange
		    this.cost = 5;
		    break;
		  case 2 : // blue
		    this.cost = 10;
		    break;
		  case 7:  // black
		    this.cost = 12;
		    break;
		  case 6: // white
		    this.cost = 1;
		    break;
		  default:
			  this.cost = 0;
			  break;
		}
	}
	
	/**
	 * D�finit la couleur de la case consid�r�e. 
	 * 
	 * @param c : couleur de la case
	 */
	public void setColor(int c){
		color = c;
	}
	
	/**
	 * D�finit le co�t de la case consid�r�e. 
	 * 
	 * @param cost : co�t de la case
	 */
	public void setCost (int cost){
		this.cost = cost;
	}
	
	/**
	 * Retourne les coordonn�es de la case consid�r�e. 
	 * 
	 * @return  Les coordon�es de la case. 
	 */
	public String getCase(){
		return(Integer.toString(this.abs)+";"+Integer.toString(this.ord));
	}
		
	/**
	 * Retourne la couleur de la case consid�r�e.
	 * 
	 * @return La couleur de la case. 
	 */
	public int getColor(){
		 return color;
	 }
	
	/**
	 * Retourne l'abscisse de la case consid�r�e. 
	 * 
	 * @return L'abscisse de la case. 
	 */
		
	public int getAbs(){
		return abs;
	}
	
	
	/**
	 * Retourne l'ordonn�e de la case consid�r�e. 
	 * 
	 * @return L'ordonn�e de la case. 
	 */
	public int getOrd () {
		return ord;
	}
}
