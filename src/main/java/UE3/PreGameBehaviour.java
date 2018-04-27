package UE3;

import java.util.ArrayList;

import UE3.GamemasterBehaviour;
import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class PreGameBehaviour extends CyclicBehaviour {

	private static final long serialVersionUID = -134666868876186342L;
	ArrayList<AID> players;

	public PreGameBehaviour(ArrayList<AID> players) {
		this.players = players;
	}

	@Override
	public void action() {
		ACLMessage msg = myAgent.receive();
		if (msg != null) {
			if (msg.getContent().equals("REGISTER")) {
				players.add(msg.getSender());
				System.out.println(msg.getSender().getLocalName() + " hat sich registriert.");
			} else if (msg.getContent().equals("START") || msg.getContent().equals("start")) {
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("Das Spiel wird gestartet.");
				System.out.println("-----------------------------------------------------------------------");
				myAgent.addBehaviour(new GamemasterBehaviour(players));
				myAgent.removeBehaviour(this);
			}
		} else {
			block();
		}
	}
}
