package UE3;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lasse Hammer on 27.04.2018.
 */
public class CleverBehaviour extends PlayerBehaviour {

    @Override
    protected String getPlayCard(String topCard) {
        System.out.println(myAgent.getLocalName() + ":   Was nehme ich denn? (" + getPlayerHand() + ").");
        char topCardColor = topCard.charAt(0);
        char topCardValue = topCard.charAt(1);
        String playCard = new String();

        if(topCardValue == '7'){
            List<String> cardsOfValue = getAllCardsOfValue('7');
            // erstmal nur erste 7 in Liste wählen
            if(cardsOfValue.size()>0) {
                playCard = cardsOfValue.get(0);
            }
        }
        else if(topCardValue == '8'){
            List<String> cardsOfValue = getAllCardsOfValue('8');
            // erstmal nur erste 8 in Liste wählen
            if(cardsOfValue.size()>0) {
                playCard = cardsOfValue.get(0);
            }
        } else{
            List<String> cardsOfSameColor = getAllCardsOfColor(topCardColor);
            List<String> cardsOfSameValue = getAllCardsOfValue(topCardValue);
            List<String> playableCards = new ArrayList<>(cardsOfSameColor);
            playableCards.addAll(cardsOfSameValue);
            if(playableCards.size()>0) {
                String notSpecialCard = getCardThatIsNoSpecialCard(playableCards);
                if (notSpecialCard != null) {
                    playCard = notSpecialCard;
                } else {
                    playCard = getLeastValuableSpecialCard(playableCards);
                }
            }
        }
        if(!playCard.equals("")){
            cards.remove(playCard);
            if (cards.size() == 0) {
                System.out.println(myAgent.getLocalName() + ":   Ich spiele meine letzte Karte " + playCard + ".");
                return "LASTCARD;" + playCard;
            } else {
                System.out.println(myAgent.getLocalName() + ":   Ich spiele die Karte " + playCard + ".");
                playCard = chooseColorIfJack(playCard);
                return "PLAYCARD;" + playCard;
            }
        }
        System.out.println(myAgent.getLocalName() + ":   Ich passe.");
        return "PASSTURN;";
    }



    private int countCardsOfColor(char color){
        int count = 0;
        for(String card:cards){
            if(card.charAt(0)==color){
                count++;
            }
        }
        return count;
    }

    private List<String> getAllCardsOfColor(char color){
        List<String> list = new ArrayList<>();
        for(String card:cards){
            if(card.charAt(0)==color){
                list.add(card);
            }
        }
        return list;
    }

    private List<String> getAllCardsOfValue(char value){
        List<String> list = new ArrayList<>();
        for(String card:cards){
            if(card.charAt(1)==value){
                list.add(card);
            }
        }
        return list;
    }

    private char getColorWithMostCards(){
        int cCount = countCardsOfColor('C');
        int hCount = countCardsOfColor('H');
        int pCount = countCardsOfColor('P');
        int kCount = countCardsOfColor('K');

        int maxCount = cCount;
        char currentMax = 'C';
        if(hCount>maxCount){
            maxCount = hCount;
            currentMax = 'H';
        }
        if(pCount>maxCount){
            maxCount = pCount;
            currentMax = 'P';
        }
        if(kCount>maxCount){
            maxCount = kCount;
            currentMax = 'K';
        }
        return currentMax;
    }

    private String chooseColorIfJack(String card){
        if(card.charAt(1)=='B') {
            char color = getColorWithMostCards();
            return card + color;
        }else {
            return card;
        }
    }

    private String getCardThatIsNoSpecialCard(List<String> cards){
        for(String card: cards){
            //erstmal nur erstbeste nicht spezialkarte zurück geben
            if(card.charAt(1) != '7' && card.charAt(1) != '8' && card.charAt(1) != 'B'){
                return card;
            }
        }
        return null;
    }
    private String getLeastValuableSpecialCard(List<String> cards) {
        for(String card: cards){
            //8 ist am wertlosesten
            if(card.charAt(1) != '7' && card.charAt(1) != 'B'){
                return card;
            }
        }
        for(String card: cards){
            //7 wertloser als bube
            if(card.charAt(1) != 'B'){
                return card;
            }
        }
        // sonst ersten buben ausgeben (bei Buben ist es egal, welcher gespielt wird)
        return cards.get(0);
    }
}