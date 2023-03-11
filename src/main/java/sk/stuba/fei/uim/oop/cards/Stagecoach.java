package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Stagecoach extends BrownCard{
    public Stagecoach() {
        super( "Stagecoach");
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> cardStack, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        for(int i = 0; i < 2; i++){
            refillDeckIfEmpty(cardStack,discardPile);
            currentPlayer.addToHand(cardStack.get(cardStack.size()-1));
            cardStack.remove(cardStack.size()-1);
        }
        discardPile.add(currentPlayer.getCardFromHand(indexOfPlayedCard));
        currentPlayer.removeCardFromHand(indexOfPlayedCard);
    }

    public void refillDeckIfEmpty(List<Card> cardStack, List<Card> discardPile){

        if(cardStack.size()>0){
            return;
        }
        System.out.print("CARD STACK REFILLED");
        List<Card> temp = new ArrayList<>(cardStack);
        cardStack.clear();
        cardStack.addAll(discardPile);
        discardPile.clear();
        discardPile.addAll(temp);
        Collections.shuffle(cardStack);
    }

}
