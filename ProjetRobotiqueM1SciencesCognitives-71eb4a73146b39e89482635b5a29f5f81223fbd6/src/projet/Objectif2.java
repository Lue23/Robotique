package projet;


import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.remote.nxt.BTConnection;
import lejos.remote.nxt.BTConnector;
import lejos.remote.nxt.NXTConnection;
import lejos.robotics.subsumption.Behavior;
import java.io.IOException;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

/**
 * <b>
 * Second objectif : envoi des coordonn�es via Bluetooth. 
 * </b>
 * 
 * <p>
 * Il doit �tre possible d'envoyer les coordonn�es d'un robot vers un autre lorsque l'utilisateur appui sur le bouton bas. 
 * </p>
 * <p>
 * Cette classe impl�mente la classe Behavior car correspond � un comportement. 
 * </p>
 */
public class Objectif2 implements Behavior{
	private Robot rob;
	
	/**
	 * 
	 * @param r : Robot consid�r� (sauvageon ou garde)
	 */
	public Objectif2 (Robot r){
		this.rob = r;
	}
	/**
	 * Action permettant de lancer le comportement 
	 */
	@Override
	public boolean takeControl() {
		return(Button.UP.isDown());
	}
	/**
	 * Ce qui est effectu� lorsque le comportement est �tabli (envoi des coordonn�es)
	 * @see BluetoothEmettor
	 * @see BluetoothReceptor
	 */
	@Override
	public void action() {

		
		BTConnector bt = new BTConnector();
		BTConnection btc = bt.connect("00:16:53:43:4E:26", NXTConnection.PACKET);
		
		
		
		OutputStream os = btc.openOutputStream();
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(os);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CaseEnvironnement [][] env =  rob.getEnv() ;
		
		try {
			oos.writeObject(env);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		 LCD.clear();
		 LCD.drawString("LOLILO :",0,2);
		 LCD.asyncRefresh();
		 Button.waitForAnyPress();
		 LCD.clear();
	}

	@Override
	public void suppress() {
		
		
	}

}
