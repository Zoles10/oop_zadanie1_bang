package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;
import java.util.Random;

public class Dynamit extends BlueCard {
    Random rand;
    public Dynamit() {
        super( "Dynamit");
        rand = new Random();
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        currentPlayer.addToPassiveEffects(currentPlayer.getCardFromDeck(indexOfPlayedCard));
        currentPlayer.removeCardFromDeck(indexOfPlayedCard);

    }

    @Override
    public boolean didExucute(){
        int chance = rand.nextInt(8);
        if(chance==0){
            return true;
        }
        return false;
    }
}
