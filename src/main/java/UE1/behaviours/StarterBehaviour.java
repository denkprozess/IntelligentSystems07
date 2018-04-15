package UE1.behaviours;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Lasse Hammer on 15.04.2018.
 */
public class StarterBehaviour extends CyclicBehaviour {
    private boolean auctioneerSelected = false;
    private AID auctioneer;
    private List<AID> participants = new ArrayList<AID>();
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            String content = msg.getContent();
            // start the auction if user sends go message
            if (content.equals("Go") || content.equals("go") || content.equals("Start") || content.equals("start")) {
                //send messages with roles to workers
                ACLMessage auctioneerStartMsg = new ACLMessage(ACLMessage.INFORM);
                auctioneerStartMsg.addReceiver(auctioneer);
                auctioneerStartMsg.setContent("auctioneer");
                myAgent.send(auctioneerStartMsg);

                ACLMessage participantsStartMsg = new ACLMessage(ACLMessage.INFORM);
                participantsStartMsg.setContent("participant");
                for(AID participant: participants){
                    participantsStartMsg.addReceiver(participant);
                }
                myAgent.send(participantsStartMsg);

                // assigns roles to workers when they first register and safe them to assign their roles when start
                // is called by the user
            } else if(content.equals("registration"));{
                if(!auctioneerSelected) {
                    auctioneer = msg.getSender();
                    auctioneerSelected = true;
                } else{
                    participants.add(msg.getSender());
                }
            }
        } else{
            block();
        }
    }

}
