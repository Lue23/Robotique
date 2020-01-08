package projet;

import lejos.robotics.subsumption.Behavior;
import lejos.hardware.Button;
import lejos.robotics.navigation.MovePilot;
/**
 * 
 * <b> 
 * Premier objectif : d�placement du robot jusqu'au camp militaire. 
 * </b>
 *
 * <p>
 * Le robot doit se d�placer jusqu'� son camp militaire. Pour cela, on utilise MovePilot, qui permet l'actionnement des moteurs des roues. 
 * On consid�re ici que le robot connait la taille des cases. 
 * </p>
 * 
 * <p>
 * Cette classe impl�mente la classe Behavior car correspond � un comportement. 
 * </p>
 */
public class Objectif1 implements Behavior {
	private boolean camp; // True == Garde de nuit 
	private MovePilot pilot;

	/**
	 * 
	 * @param c : Equipe du robo (Sauvageon, Garde)
	 * @param p : Objet MovePilot pour faire avancer le robot. 
	 */
	public Objectif1 (boolean c, MovePilot p){
		this.camp = c;
		this.pilot = p;
	}
	/**
	 * Permet de lancer le comportement. 
	 */
	public boolean takeControl() {
		return(Button.RIGHT.isDown());
	}
	
	public void suppress() {
	}
	
	/**
	 * Ce qui est effectu� lorsque le comportement est �tabli. 
	 */
	public void action() {
		
		
		pilot.setLinearSpeed(60);
		pilot.setAngularSpeed(25);     
		
		if (!camp) {
			pilot.travel(540);
		}
		else {
			pilot.travel(405);
			pilot.rotate(-81);
			pilot.travel(120);
		}		

	}
}
