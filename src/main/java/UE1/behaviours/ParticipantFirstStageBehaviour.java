package UE1.behaviours;

import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

/**
 * Created by Lasse Hammer on 15.04.2018.
 */
public class ParticipantFirstStageBehaviour extends CyclicBehaviour {
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            // read start and end time from message and generate offered time.
            String content = msg.getContent();
            String taskIDString = msg.getConversationId();
            int indexOfComma = content.lastIndexOf(',');
            String startTimeString = content.substring(0,indexOfComma);
            String endTimeString = content.substring(indexOfComma+2);
            int startTime = Integer.parseInt(startTimeString);
            int endTime = Integer.parseInt(endTimeString);
            int offeredTime = startTime+1 + (int)(Math.random() * ((endTime - startTime+1) + 1));

            //send proposal
            ACLMessage proposal = new ACLMessage(ACLMessage.PROPOSE);
            proposal.setConversationId(taskIDString);
            proposal.addReceiver(msg.getSender());
            proposal.setContent(""+offeredTime);
            myAgent.send(proposal);
            System.out.println(myAgent.getAID().getLocalName() + ": proposed task completion time " + offeredTime + "" +
                    ".");

            //set next stage behaviour
            myAgent.addBehaviour(new ParticipantSecondStageBehaviour());
            myAgent.removeBehaviour(this);
        } else{
            block();
        }
    }
}
