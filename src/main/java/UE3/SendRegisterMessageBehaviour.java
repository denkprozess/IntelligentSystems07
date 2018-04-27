package UE3;

import jade.core.AID;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;

public class SendRegisterMessageBehaviour extends OneShotBehaviour {

	private static final long serialVersionUID = -134666868876186342L;

	@Override
	public void action() {
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(new AID(("Gamemaster"), AID.ISLOCALNAME));
		msg.setContent("REGISTER");
		myAgent.send(msg);
	}

}
