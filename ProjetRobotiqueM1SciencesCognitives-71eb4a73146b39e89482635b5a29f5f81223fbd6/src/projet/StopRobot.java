package projet;

import java.util.Arrays;
import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

/**
 * <b> Classe permettant de stopper le robot. </b>
 * 
 * <p> Cette classe permet d'arr�ter le robot compl�tement, c'est � dire que tous les capteurs sont arr�t�s, 
 * les ports sont ferm�s... 
 * 
 * Cela peut �galement servir en cas de probl�me lors de l'utilisation du robot. 
 * 
 * <p>
 * Cette classe impl�mente la classe Behavior car correspond � un comportement. 
 * </p>
 * 
 */
public class StopRobot implements Behavior{
	//private EV3UltrasonicSensor ultrasonic;
	//private EV3TouchSensor touch; 
	private EV3ColorSensor color;
	private float [] f;
	private Arbitrator action;
	
	/**
	 * 
	 * @param color : capteur de couleur EV3ColorSensor
	 * @param s : tableau des comportements 
	 */
	public StopRobot(EV3ColorSensor color, float [] s) {
		this.color = color; this.f = s;
		
	}
	
	public void setArbitrator (Arbitrator arby) {
		this.action = arby;
	}
	
	public boolean takeControl() {
		return(Button.LEFT.isDown());
	}
	
	public void suppress() {
	}
	
	/**
	 * Ce qui est effectu� lorsque le comportement est �tabli. 
	 */
	public void action() {
        LCD.clear();
		LCD.drawString("Time to die", 0,4);
        LCD.asyncRefresh();

		Motor.B.stop(true);
		Motor.C.stop(true);
		
		System.out.println(Arrays.toString(f));

		//ultrasonic.close();
		color.close();
		
		
		action.stop();
		System.exit(0);
	}
}
