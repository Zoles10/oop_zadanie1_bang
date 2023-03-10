package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;
import java.util.Random;

public class Prison extends BlueCard{
    Random rand;
    public Prison(){
        super("Prison");
        rand = new Random();
    }

    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard,List<Card> gameDeck, List<Card> discardPile) {
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.print("Pick which player to INPRISON, players: ");

        Player attackedPlayer = choosePlayerToAttack(players,indexOfCurrentPlayer);

        if(attackedPlayer.hasPrisonOnTable()){
            System.out.println("Cannot place Prison on the players table, he is already imprisoned.");
            return;
        }

        attackedPlayer.addToTable(currentPlayer.getCardFromHand(indexOfPlayedCard));
        attackedPlayer.setIsInPrison(true);
        currentPlayer.removeCardFromHand(indexOfPlayedCard);
    }

    @Override
    public boolean didExecute(){
        int chance = rand.nextInt(4);
        return chance == 0 ? true : false;
    }
}
