package sk.stuba.fei.uim.oop.cards.bluecards;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.Random;

import java.util.List;

public class Barrel extends BlueCard {

    private final Random rand;

    public Barrel() {
        super("Barrel");
        rand = new Random();
    }

    @Override
    public void play(List<Player> players, Player currentPlayer) {
        for (Card card : currentPlayer.getTable()) {
            if (card instanceof Barrel) {
                System.out.println("\u001B[31mYou cannot play Barrel, you already have one on the table!\u001B[0m");
                return;
            }
        }
        currentPlayer.addToTable(this);
        currentPlayer.removeCardFromHand(this);
    }

    @Override
    public boolean didExecute(List<Player> playerList, Player currentPlayer) {
        int chance = rand.nextInt(4);
        return chance == 0;
    }


}
