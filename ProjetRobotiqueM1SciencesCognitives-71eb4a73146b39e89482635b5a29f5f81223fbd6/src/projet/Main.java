package projet;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
/**
 * 
 * 
 * <b>La classe Main est la classe principale </b>
 * 
 * <p>Ici, elle comporte le param�trage de divers outils pour le robot tels que : 
 * <ul>
 * <li> Les roues et le chassis </li>
 * <li> La vitesse lin�aire et la vitesse angulaire </li>
 * <li> Le capteur couleur </li>
 * </ul>
 * </p>
 * 
 * <p>
 * Cette classe principale permet �galement de g�rer l'ensemble des comportements (Behavior)
 * </p>
 * 
 * <p>
 * Au d�but de cette classe, on retrouve �galement le code permettant de choisir le camp du robot (Sauvageon ou Garde)
 * Pour cela, l'utilisateur doit appuyer sur le bouton haut afin de s�lectionner les sauvageons, 
 * ou sur le bouton bas pour s�lectionner les gardes de nuit. 
 * Une fois le choix r�alis�, le nom de l'�quipe choisie est affich� sur l'�cran.
 * Puis, lors de l'appui sur une bouton, les coordonn�es du point de d�part son affich�es. 
 * </p>
 */

public class Main {
	public static void main(String[] args) {
		
		Robot warrior = null;
		
		LCD.drawString("Haut = Sauvageon", 0,0);
		LCD.drawString("Bas = Garde", 0,4);
		
        LCD.asyncRefresh();
        int bouton =Button.waitForAnyPress(); //waitForAnyPress renvoie un integer correspondant au bouton press�
 
        if (bouton==1) // Correspond au bouton du haut = sauvageon
        {
        	warrior = new Robot("Sauvageon", false);
        }
        else if (bouton==4)//Correspond au bouton du bas = garde
        {
        	warrior = new Robot("Garde", true);
        }        

        
        LCD.clear();
        LCD.drawString("Camps choisi :",0,2); 
        LCD.drawString(warrior.getNom(),0,4);
        LCD.asyncRefresh();
        Button.waitForAnyPress();

        
        warrior.CreationMap();
        
        LCD.clear();
        LCD.drawString("Coordonnees :",0,2); 
        LCD.drawString(warrior.getStart(), 0,4);
        LCD.asyncRefresh();
        Button.waitForAnyPress();
        LCD.clear();
        
        
        Wheel wheel1 = WheeledChassis.modelWheel(Motor.B, 56.).offset(-60);
        Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 56.).offset(60);
        Chassis chassis = new WheeledChassis(new Wheel[]{wheel1, wheel2}, 2);
        MovePilot pilot = new MovePilot(chassis);
        pilot.setLinearSpeed(30.);
        pilot.setAngularSpeed(30.);
        
		//EV3TouchSensor ts = new EV3TouchSensor(SensorPort.S1);
		EV3ColorSensor color = new EV3ColorSensor(SensorPort.S3);
		float[] s = new float[4];							// 0..2 couleur, 3 ultrason
		
		Behavior[] bArray= new Behavior [5];				// tableau de 3 Behavior (= 3 comportements)
		
		//Behavior bSearchLine = new SearchLine(pilot, color, ts);
		//Behavior bFollowBlackLine = new FollowBlackLine(color, ts);
		Behavior bCollision = new HitSomething(s);
		Behavior bObjectif2 = new Objectif2(warrior);
		Behavior bStop = new StopRobot(color, s);
		Behavior bObjectif1 = new Objectif1(warrior.getTeam(), pilot);
		Behavior bCalibrationColor = new CalibrationColor();

		bArray[0] = bObjectif1;
		bArray[1] = bObjectif2;
		bArray[2] = bCollision;
		bArray[3] = bStop;
		bArray[4] = bCalibrationColor;
		
		Arbitrator arby= new Arbitrator(bArray);
		LCD.clear();
        LCD.drawString("Pret",0,2);
        LCD.asyncRefresh();
		
		if (bStop instanceof StopRobot) {
			StopRobot b = (StopRobot)bStop;
			b.setArbitrator(arby);
		}
		
		arby.go();
	}
	
}
