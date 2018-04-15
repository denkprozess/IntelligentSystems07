package UE1.behaviours;

import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by Lasse Hammer on 15.04.2018.
 */
public class ParticipantSecondStageBehaviour extends CyclicBehaviour {
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String content = msg.getContent();
            if(content.equals("accepted")){
                System.out.println(myAgent.getAID().getLocalName() + ": will start a new auction in 8 seconds.");
                //starts the auctioneer behaviour after a delay
                myAgent.addBehaviour(new WakerBehaviour(myAgent, 8000) {
                    @Override
                    protected void onWake() {
                        myAgent.addBehaviour(new AuctineerFirstStageBehaviour());
                    }
                });
                myAgent.removeBehaviour(this);
            } else if(content.equals("rejected")){
                //starts the participant behaviour after a delay
                myAgent.addBehaviour(new WakerBehaviour(myAgent, 8000) {
                    @Override
                    protected void onWake() {
                        myAgent.addBehaviour(new ParticipantFirstStageBehaviour());
                    }
                });
                myAgent.removeBehaviour(this);
            }
        }else{
            block();
        }
    }
}
