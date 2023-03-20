package sk.stuba.fei.uim.oop.cards.bluecards;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;
import java.util.Random;

public class Prison extends BlueCard {
    private final Random rand;
    public Prison(){
        super("Prison");
        rand = new Random();
    }

    @Override
    public void play(List<Player> players, Player currentPlayer) {
        System.out.print("Pick which player to imprison, players: \n");
        Player attackedPlayer = choosePlayerToAttack(players,currentPlayer);
        for (Card card : attackedPlayer.getTable()) {
            if (card instanceof Prison) {
                System.out.println("\u001B[31mCannot place Prison on the players table, he is already imprisoned!\u001B[0m");
                return;
            }
        }
        attackedPlayer.setIsInPrison(true);
        attackedPlayer.addToTable(this);
        currentPlayer.removeCardFromHand(this);
    }

    @Override
    public boolean didExecute(List<Player> playerList,Player currentPlayer){
        int chance = rand.nextInt(4);
        if(chance == 0){
            currentPlayer.setIsInPrison(false);
            System.out.println("\u001B[33mYou escape prison!\u001B[0m");
        }
        else {
            currentPlayer.setIsInPrison(true);
            System.out.println("\u001B[31mYou didnt escape prison and skip a turn\u001B[0m");
        }
        currentPlayer.addToDiscardPile(this);
        return true;
    }
}
