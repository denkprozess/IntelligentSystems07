package UE1.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by Lasse Hammer on 15.04.2018.
 */
public class WaitForRoleBehaviour extends CyclicBehaviour {
    public void action() {
        ACLMessage msg = myAgent.receive();
        if(msg != null){
            String content = msg.getContent();
            System.out.println(myAgent.getAID().getLocalName() + ": received role " + content);
            if(content.equals("auctioneer")){
                //myAgent.addBehaviour(new WaitForStartBehaviour());
                myAgent.addBehaviour(new AuctineerFirstStageBehaviour());
                myAgent.removeBehaviour(this);
            } else if(content.equals("participant")){
                //myAgent.addBehaviour(new WaitForStartBehaviour());
                myAgent.addBehaviour(new ParticipantFirstStageBehaviour());
                myAgent.removeBehaviour(this);
            }
        } else{
            block();
        }
    }
}
