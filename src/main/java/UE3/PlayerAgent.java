package UE3;

import jade.core.Agent;

public class PlayerAgent extends Agent {

	private static final long serialVersionUID = 2073522955156823880L;
	
	protected void setup() {
		addBehaviour(new SendRegisterMessageBehaviour());
		addBehaviour(new PlayerBehaviour());
	}

}
