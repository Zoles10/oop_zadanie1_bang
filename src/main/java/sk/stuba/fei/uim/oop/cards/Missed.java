package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Missed extends BrownCard{
    public Missed() {
        super("Missed");
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard,List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        Card currentPlayedCard = currentPlayer.getCardFromHand(indexOfPlayedCard);
        discardPile.add(currentPlayedCard);
        currentPlayer.removeCardFromHand(indexOfPlayedCard);
    }
}
