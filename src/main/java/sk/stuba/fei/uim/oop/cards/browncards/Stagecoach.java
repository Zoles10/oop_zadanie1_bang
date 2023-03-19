package sk.stuba.fei.uim.oop.cards.browncards;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Stagecoach extends BrownCard {
    public Stagecoach() {
        super( "Stagecoach");
    }

    @Override
    public void play(List<Player> players, int indexOfCurrentPlayer){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        currentPlayer.drawCards(2);
        removeAndDiscard(currentPlayer,this);
    }
}
