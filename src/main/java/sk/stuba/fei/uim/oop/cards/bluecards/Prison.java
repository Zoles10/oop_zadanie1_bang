package sk.stuba.fei.uim.oop.cards.bluecards;
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
    public void play(List<Player> players, int indexOfCurrentPlayer) {
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.print("Pick which player to imprison, players: \n");
        Player attackedPlayer = choosePlayerToAttack(players,indexOfCurrentPlayer);
        if(attackedPlayer.hasPrisonOnTable()){
            System.out.println("Cannot place Prison on the players table, he is already imprisoned!");
            return;
        }
        attackedPlayer.setIsInPrison(true);
        attackedPlayer.addToTable(this);
        currentPlayer.removeCardFromHand(this);
    }

    @Override
    public boolean didExecute(List<Player> playerList,int indexOfCurrentPlayer){
        int chance = rand.nextInt(4);
        Player currentPlayer = playerList.get(indexOfCurrentPlayer);
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
