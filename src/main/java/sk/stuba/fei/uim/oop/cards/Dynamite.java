package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;
import java.util.Random;

public class Dynamite extends BlueCard {
    Random rand;
    public Dynamite() {
        super( "Dynamite");
        rand = new Random();
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);

        if(currentPlayer.hasDynamiteOnTable()){
            System.out.println("You cannot place dynamite, you already have one on the table!");
        }

        currentPlayer.addToTable(currentPlayer.getCardFromHand(indexOfPlayedCard));
        currentPlayer.removeCardFromHand(indexOfPlayedCard);

    }

    @Override
    public boolean didExecute(){
        int chance = rand.nextInt(8);
        return chance == 0 ? true : false;
    }
}
