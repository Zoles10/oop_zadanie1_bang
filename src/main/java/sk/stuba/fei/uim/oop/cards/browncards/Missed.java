package sk.stuba.fei.uim.oop.cards.browncards;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Missed extends BrownCard {
    public Missed() {
        super("Missed");
    }


    @Override
    public void play(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard) {
        System.out.println("You cannot play Missed!");
    }
}
