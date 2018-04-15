package UE1.behaviours;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Lasse Hammer on 15.04.2018.
 */
public class AuctioneerSecondStageBehaviour extends CyclicBehaviour {
    private final int EXPECTEDOFFERS = 2;
    private List<Offer> offers= new ArrayList<Offer>();
    public void action() {
        //execute if not all offers were received yet
        if(offers.size()<EXPECTEDOFFERS){
            ACLMessage msg = myAgent.receive();
            if (msg != null) {
                // read start and end time from message and generate offered time.
                String content = msg.getContent();
                int offeredTime = Integer.parseInt(content);
                Offer offer = new Offer(offeredTime, msg.getSender());
                offers.add(offer);
            }else{
                block();
            }
            //compare offers and select best one
        }else{
        	offers.sort(Comparator.comparing(Offer::getOfferedTime));
            Offer bestOffer = offers.get(0);
            offers.remove(bestOffer);
            ACLMessage acceptedMsg = new ACLMessage(ACLMessage.INFORM);
            acceptedMsg.setContent("accepted");
            acceptedMsg.addReceiver(bestOffer.getOffererID());
            myAgent.send(acceptedMsg);
            System.out.println(myAgent.getAID().getLocalName() + ": accepted the offer of " + bestOffer.getOffererID
                    ().getLocalName() + ".");

            //notify all other workers that their offer was rejected
            ACLMessage rejectedMsg = new ACLMessage(ACLMessage.INFORM);
            rejectedMsg.setContent("rejected");
            for(Offer offer:offers){
                rejectedMsg.addReceiver(offer.getOffererID());
            }
            myAgent.send(rejectedMsg);

            //set behaviour to participant behaviour of stage 1
            myAgent.addBehaviour(new ParticipantFirstStageBehaviour());
            myAgent.removeBehaviour(this);

        }
    }



    class Offer{


        public int offeredTime;
        public AID offererID;

        public Offer(int offeredTime, AID offererID){
            this.offeredTime = offeredTime;
            this.offererID = offererID;
        }

        public int getOfferedTime() {
            return offeredTime;
        }

        public AID getOffererID() {
            return offererID;
        }
    }
}
