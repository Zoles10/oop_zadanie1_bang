package sk.stuba.fei.uim.oop.cards.browncards;

import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Beer extends BrownCard {
    public Beer() {
        super("Beer");
    }

    @Override
    public void play(List<Player> players, Player currentPlayer) {
        currentPlayer.incrementHp(1);
        System.out.println("\u001B[32mYou played Beer and healed one HP, you now have " + currentPlayer.getHp() + "\u001B[0m");
        removeAndDiscard(currentPlayer, this);
    }
}
