package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;
import java.util.Random;

public class Prison extends BlueCard{
    private Random rand;
    public Prison(){
        super("Prison");
        rand = new Random();
    }

    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard,List<Card> gameDeck, List<Card> discardPile) {
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.print("Pick which player to imprison, players: \n");
        Player attackedPlayer = choosePlayerToAttack(players,indexOfCurrentPlayer);
        if(attackedPlayer.hasPrisonOnTable()){
            System.out.println("Cannot place Prison on the players table, he is already imprisoned!");
            return;
        }
        attackedPlayer.addToTable(currentPlayer.getCardFromHand(indexOfPlayedCard));
        attackedPlayer.setIsInPrison(true);
        currentPlayer.removeCardFromHand(indexOfPlayedCard);
    }

    @Override
    public boolean didExecute(List<Player> playerList, int indexOfCurrentPlayer, List<Card> discardPile){
        int chance = rand.nextInt(4);
        Player currentPlayer = playerList.get(indexOfCurrentPlayer);
        if(chance == 0){
            System.out.println("\u001B[33mYou escape prison!\u001B[0m");
            currentPlayer.setIsInPrison(false);
            return true;
        }
        else {
            currentPlayer.setIsInPrison(true);
            System.out.println("\u001B[36m--------------PLAYER TURN: "+currentPlayer.getName()+"---------------- \u001B[0m");
            System.out.println("\u001B[31mYou didnt escape prison and skip a turn\u001B[0m");
            return false;
        }
    }
}
