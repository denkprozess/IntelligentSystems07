package UE3;

import java.util.ArrayList;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.UnreadableException;

public class PlayerBehaviour extends CyclicBehaviour {

    private static final long serialVersionUID = -6750322553571517866L;
    ArrayList<String> cards;

    @SuppressWarnings("unchecked")
    @Override
    public void action() {
        ACLMessage msg = myAgent.receive();
        if (msg != null) {
            try {
                // Starthand
                if (msg.getContent().contains("ArrayList")) {
                    cards = (ArrayList<String>) msg.getContentObject();
                    System.out.println(myAgent.getName() + " erhält die Handkarten: " + getPlayerHand());
                    //System.out.println("-----------------------------------------------------------------------");
                }

                // Neuer Spielzug
                else if (msg.getContent().startsWith("DECKCARD;")) {
                    sendPlayCard(getPlayCard(msg.getContent().substring(9)));
                }

                // Zusatzkarte
                else if (msg.getContent().startsWith("PENALTYC;")) {
                    System.out.println(myAgent.getLocalName() + ":   Zusätzliche Karte erhalten ("
                            + msg.getContent().substring(9) + ").");
                    cards.add(msg.getContent().substring(9));
                    //System.out.println("-----------------------------------------------------------------------");
                }
            } catch (UnreadableException e) {
            }
        } else {
            block();
        }
    }

    /**
     * Gibt Spielerhand als String zurück.
     */
    protected String getPlayerHand() {
        String hand = "";
        for (String card : cards) {
            hand = hand + card + "  ";
        }
        return hand.trim();
    }

    /**
     * Prüft ob eine spielbare Karte existiert und ob es die letzte Karte ist. Falls
     * ja, wird diese verschickt. Falls nicht wird gepasst.
     */
    protected String getPlayCard(String topCard) {
        System.out.println(myAgent.getLocalName() + ":   Was nehme ich denn? (" + getPlayerHand() + ").");
        for (String card : cards) {
            if (card.charAt(0) == topCard.charAt(0) && (topCard.charAt(1) != '7' && topCard.charAt(1) != '8' ||
                    topCard.length() < 3) || card.charAt(1) == topCard.charAt(1)) {
                cards.remove(card);
                if (cards.size() == 0) {
                    System.out.println(myAgent.getLocalName() + ":   Ich spiele meine letzte Karte " + card + ".");
                    return "LASTCARD;" + card;
                } else {
                    System.out.println(myAgent.getLocalName() + ":   Ich spiele die Karte " + card + ".");
                    card = chooseColorIfJack(card);
                    return "PLAYCARD;" + card;
                }
            }
        }
        System.out.println(myAgent.getLocalName() + ":   Ich passe.");
        return "PASSTURN;";
    }

    /**
     * Verschickt einen Spielzug an den Gamemaster.
     */
    private void sendPlayCard(String playCard) {
        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
        msg.addReceiver(new AID(("Gamemaster"), AID.ISLOCALNAME));
        msg.setContent(playCard);
        myAgent.send(msg);
    }

    private String chooseColorIfJack(String card) {
        if (card.charAt(1) == 'B') {
            String returnCard = new String();
            switch (card.charAt(0)) {
                case 'C':
                    returnCard = "CBH";
                    break;
                case 'H':
                    returnCard = "HBP";
                    break;
                case 'P':
                    returnCard = "PBK";
                    break;
                case 'K':
                    returnCard = "KBC";
                    break;
            }
            System.out.println(myAgent.getLocalName() + ":   Ich wünsche mir die Farbe " + returnCard.charAt(2) + ".");
            return returnCard;
        } else {
            return card;
        }
    }
}
