package UE1.agents;

import UE1.behaviours.WaitForRoleBehaviour;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import org.apache.axis.utils.Admin;

/**
 * Created by Lasse Hammer on 15.04.2018.
 */
public class Worker extends Agent {
    @Override
    protected void setup() {
        System.out.println("I am a worker!");
        // to account for the randomness of agent initialization order
        // workers can be created before the admin which is why they wait
        addBehaviour(new WakerBehaviour(this, 1000) {
            @Override
            protected void onWake() {
                ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                msg.addReceiver(new AID("Administrator", AID.ISLOCALNAME));
                msg.setContent("registration");
                myAgent.send(msg);
            }
        });
        addBehaviour(new WaitForRoleBehaviour());
    }
}
