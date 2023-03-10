package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;
import java.util.Random;

import java.util.List;

public class Barrel extends BlueCard{

    Random rand;

    public Barrel(){
        super("Barrel");
        rand = new Random();
    }

    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){

        Player currentPlayer = players.get(indexOfCurrentPlayer);

        if(currentPlayer.hasBarrelOnTable()){
            System.out.println("You cannot play Barrel, you are have one on the table!");
        }
        currentPlayer.addToTable(currentPlayer.getCardFromHand(indexOfPlayedCard));
        currentPlayer.removeCardFromHand(indexOfPlayedCard);

    }

    @Override
    public boolean didExecute(){
        int chance = rand.nextInt(4);
        return chance == 0;
    }


}
