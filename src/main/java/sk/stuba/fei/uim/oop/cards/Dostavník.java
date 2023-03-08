package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Dostavník extends BrownCard{
    public Dostavník() {
        super( "Dostavník");
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);

        currentPlayer.addToDeck(gameDeck.get(gameDeck.size()-1));
        discardPile.add(gameDeck.get(gameDeck.size()-1));
        gameDeck.remove(gameDeck.size()-1);

        currentPlayer.addToDeck(gameDeck.get(gameDeck.size()-1));
        discardPile.add(gameDeck.get(gameDeck.size()-1));
        gameDeck.remove(gameDeck.size()-1);

        discardPile.add(currentPlayer.getCardFromDeck(indexOfPlayedCard));

        currentPlayer.removeCardFromDeck(indexOfPlayedCard);
    }

}
