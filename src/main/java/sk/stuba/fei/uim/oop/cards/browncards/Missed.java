package sk.stuba.fei.uim.oop.cards.browncards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Missed extends BrownCard {
    public Missed() {
        super("Missed");
    }


    @Override
    public void play(List<Player> players, Player currentPlayer) {
        System.out.println("\u001B[31mYou cannot play Missed!\u001B[0m");
    }
}
