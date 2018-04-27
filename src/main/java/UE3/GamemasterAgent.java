package UE3;

import java.util.ArrayList;
import jade.core.AID;
import jade.core.Agent;

public class GamemasterAgent extends Agent {

	private static final long serialVersionUID = -4042555251235993394L;
	private ArrayList<AID> players;
	
	protected void setup() {
		
		players = new ArrayList<AID>();
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Agent " + getAID().getLocalName() + " gestartet.");
		System.out.println("-----------------------------------------------------------------------");
		addBehaviour(new PreGameBehaviour(players));
	}

}
