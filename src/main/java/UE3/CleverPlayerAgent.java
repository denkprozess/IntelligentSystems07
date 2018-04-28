package UE3;

import jade.core.Agent;

/**
 * Created by Lasse Hammer on 28.04.2018.
 */
public class CleverPlayerAgent extends Agent {

    protected void setup() {
        addBehaviour(new SendRegisterMessageBehaviour());
        addBehaviour(new CleverBehaviour());
    }
}
