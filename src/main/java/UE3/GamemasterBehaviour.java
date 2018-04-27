package UE3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;

public class GamemasterBehaviour extends CyclicBehaviour {

    private static final long serialVersionUID = -134666868876186342L;
    private boolean gameStarted = false;
    private ArrayList<AID> players;
    private AID actualPlayer = null;
    private ArrayList<String> talon;
    private ArrayList<String> playStack;
    private int previousSevens;
    private boolean activeEight;
    private char colorWish;

    public GamemasterBehaviour(ArrayList<AID> players) {
        this.players = players;

        playStack = new ArrayList<String>();

        talon = new ArrayList<String>();
        talon.addAll(Arrays.asList("K7", "K8", "K9", "KZ", "KB", "KD", "KK", "KA", "P7", "P8", "P9", "PZ", "PB", "PD",
                "PK", "PA", "H7", "H8", "H9", "HZ", "HB", "HD", "HK", "HA", "C7", "C8", "C9", "CZ", "CB", "CD", "CK",
                "CA"));
        Collections.shuffle(talon);
    }

    @Override
    public void action() {
        if (!gameStarted) {
            gameStarted = true;

            // Starthand austeilen
            sendFirstCardsToPlayers();

            // Erster Spielzug
            playStack.add(0, talon.remove(0));
            sendMessage(getNextPlayer(), ("DECKCARD;" + playStack.get(0)));
            block();

        } else {
            ACLMessage msg = myAgent.receive();

            if (msg != null) {
                String prefix = msg.getContent().substring(0, 8);
                AID sender = msg.getSender();
                switch (prefix) {
                    case "PLAYCARD":
                        String playerCard = msg.getContent().substring(9);
                        playCardRoutine(playerCard);
                        block();
                        break;

                    case "LASTCARD":
                        String playerCard2 = msg.getContent().substring(9);
                        lastCardRoutine(playerCard2, sender);
                        break;

                    case "PASSTURN":

                        passTurnRoutine(sender);
                        block();
                        break;

                    default:
                        block();
                }
            }
        }
    }

    private void playCardRoutine(String playerCard){
        playerCard = readColorWishIfJack(playerCard);
        if (validMove(playerCard)) {
            playStack.add(0, playerCard);
            System.out.println(myAgent.getLocalName() + ": Spielzug wurde akzeptiert.");
            if (playerCard.charAt(1) == '7') {
                previousSevens += 1;
                // Benachrichtigung, dass die 7 aktiv ist
                sendMessage(getNextPlayer(), ("DECKCARD;" + playStack.get(0) + "a"));
            } else if (playerCard.charAt(1) == '8') {
                activeEight = true;
                sendMessage(getNextPlayer(), ("DECKCARD;" + playStack.get(0) + "a"));

            } else if(playerCard.charAt(1)=='B'){
                sendMessage(getNextPlayer(), ("DECKCARD;" + colorWish +"B"));
            } else{
                // nachricht an den nächsten spieler
                sendMessage(getNextPlayer(), ("DECKCARD;" + playStack.get(0)));
            }
        }
    }

    private void lastCardRoutine(String playerCard, AID sender){
        if (validMove(playerCard)) {
            playStack.add(0, playerCard);
            System.out.println(myAgent.getLocalName() + ": Spielzug wurde akzeptiert.");
            System.out.println(sender.getLocalName() + ":   MAU MAU!!!");
        }
    }

    private void passTurnRoutine(AID sender){
        // Zusatzkarte schicken
        if (playStack.get(0).charAt(1) == '7' && previousSevens > 0) {
            //zwei karten für jede vorherige 7 in Folge
            for (int i = 0; i < previousSevens; i++) {
                sendMessage(sender, ("PENALTYC;" + getCards(1).get(0)));
                sendMessage(sender, ("PENALTYC;" + getCards(1).get(0)));
            }
            previousSevens = 0;
        } else if (playStack.get(0).charAt(1) == '8' && activeEight) {
            activeEight = false;
        }else {
            sendMessage(sender, ("PENALTYC;" + getCards(1).get(0)));
        }
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        if(playStack.get(0).charAt(1)=='B'){
            sendMessage(getNextPlayer(), ("DECKCARD;" + colorWish +"B"));
        }else{
            sendMessage(getNextPlayer(), ("DECKCARD;" + playStack.get(0)));
        }
    }
    private String readColorWishIfJack(String playerCard) {
        if(playerCard.length()>2){
            colorWish = playerCard.charAt(2);
            playerCard = playerCard.substring(0,2);
        }
        return playerCard;
    }

    /**
     * Verschickt entweder eine Spielzugaufforderung oder eine Zusatzkarte an einen
     * Player.
     */
    private void sendMessage(AID receiver, String content) {

        if (content.startsWith("DECKCARD;")) {
            System.out.println("-----------------------------------------------------------------------");
            System.out
                    .println(myAgent.getLocalName() + ": Oberste aufgedeckte Karte ist " + content.substring(9) + ".");
            System.out.println(myAgent.getLocalName() + ": " + receiver.getLocalName() + " ist am Zug.");
        }

        ACLMessage message = new ACLMessage(ACLMessage.PROPOSE);
        message.addReceiver(receiver);
        message.setContent(content);
        myAgent.send(message);
    }

    /**
     * Überprüft ob die gespielte Karte ein gültiger Zug ist. Im Moment nicht
     * notwendig, aber just in case.
     */
    private boolean validMove(String playerCard) {
        // nur 7 auf 7 und 8 auf 8
        if (playStack.get(0).charAt(1) == '7' && playerCard.charAt(1) != '7' && previousSevens > 0 || playStack.get(0)
                .charAt(1) ==
                '8' &&
                playerCard.charAt(1) != '8' && activeEight) {
            return false;
        }
        // Bube auf alles außer 7 und 8
        if (playerCard.charAt(1) == 'B' && playStack.get(0).charAt(1) != '7' && playStack.get(0).charAt(1) != '7') {
            return true;
        }
        // bei Bube Farbwünsche berücksichtigen
        if(playStack.get(0).charAt(1) == 'B' && colorWish==playerCard.charAt(0)){
            return true;
        }
        if (playStack.get(0).charAt(0) == playerCard.charAt(0) || playStack.get(0).charAt(1) == playerCard.charAt(1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Gibt @param quantity Karten aus dem Talon zurück.
     */
    private ArrayList<String> getCards(int quantity) {

        reshuffleCardsToTalon();

        ArrayList<String> cards = new ArrayList<String>();
        for (int i = 0; i < quantity; i++) {
            cards.add(talon.remove(i));
        }
        return cards;
    }

    /**
     * Prüft ob der Talon leer ist und mischt n - 1 abgelegte Karten zurück in den
     * Talon.
     */
    private void reshuffleCardsToTalon() {
        if (talon.size() == 0) {
            System.out.println(myAgent.getLocalName() + ": Abgelegte Karten werden in den Talon zurückgemischt.");
            System.out.println("----------------------------------------------------------");
            for (int i = 1; i < playStack.size(); i++) {
                talon.add(playStack.remove(i));
            }
        }
        Collections.shuffle(talon);
    }

    /**
     * Verschickt die Starthand an alle Player.
     */
    private void sendFirstCardsToPlayers() {
        for (AID player : players) {
            ACLMessage initialCards = new ACLMessage(ACLMessage.INFORM);
            initialCards.addReceiver(player);
            try {
                initialCards.setContentObject(getCards(5));
            } catch (IOException e) {
                System.out.println(this.getClass() + ": Initiale Karten konnten der Hand nicht hinzugefügt werden.");
            }
            myAgent.send(initialCards);

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Ermittelt den nächsten Player, da diese sich ungeordnet registrieren.
     */
    private AID getNextPlayer() {
        String prefix = "";
        String nextPlayer = "";
        if (this.actualPlayer != null) {
            prefix = this.actualPlayer.getName().substring(0, 8);
        }

        switch (prefix) {
            case "Player 1":
                nextPlayer = "Player 2";
                break;
            case "Player 2":
                nextPlayer = "Player 3";
                break;
            case "":
            case "Player 3":
                nextPlayer = "Player 1";
                break;
            default:
                System.out.println("Der nächste Spieler konnte nicht ermittelt werden.");
        }

        for (AID p : this.players) {
            if (p.getName().startsWith(nextPlayer)) {
                this.actualPlayer = p;
                break;
            }
        }

        return this.actualPlayer;
    }

}
