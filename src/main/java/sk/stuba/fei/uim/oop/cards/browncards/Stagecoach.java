package sk.stuba.fei.uim.oop.cards.browncards;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Stagecoach extends BrownCard {
    public Stagecoach() {
        super( "Stagecoach");
    }

    @Override
    public void play(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        Card playedCard = currentPlayer.getCardFromHand(indexOfPlayedCard);
        currentPlayer.drawCards(2);
        currentPlayer.addToDiscardPile(playedCard);
        currentPlayer.removeCardFromHand(playedCard);
    }


}
