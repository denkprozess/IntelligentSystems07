package UE1.behaviours;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;


/**
 * Created by Lasse Hammer on 15.04.2018.
 */
public class AuctineerFirstStageBehaviour extends OneShotBehaviour {
    private static int taskID = 0;
    public void action() {
        // generate random start and end times with at least 10 time units in between
        int startTime = 10 + (int)(Math.random() * ((200 - 10) + 1));
        int endTime = startTime + 10 + (int)(Math.random() * ((200 - 10) + 1));

        ACLMessage taskMsg = new ACLMessage(ACLMessage.REQUEST);
        // add all worker AIDs since it is unknown which worker is the auctioneer
        taskMsg.addReceiver(new AID("Worker1", AID.ISLOCALNAME));
        taskMsg.addReceiver(new AID("Worker2", AID.ISLOCALNAME));
        taskMsg.addReceiver(new AID("Worker3", AID.ISLOCALNAME));
        // remove my own AID
        taskMsg.removeReceiver(myAgent.getAID());
        taskMsg.setContent(startTime + ", " + endTime);
        taskMsg.setConversationId("" + taskID);
        System.out.println(myAgent.getAID().getLocalName() + ": requested task with start time " + startTime +" and " +
                "end time " + endTime);

        //set next stage behaviour
        myAgent.send(taskMsg);
        myAgent.addBehaviour(new AuctioneerSecondStageBehaviour());
        myAgent.removeBehaviour(this);
    }
}
