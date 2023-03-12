package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Beer extends BrownCard{
    public Beer() {
        super( "Beer");
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard,List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        currentPlayer.setHp(currentPlayer.getHp()+1);
        System.out.println("\u001B[32mYou played Beer and healed one HP, you now have "+currentPlayer.getHp()+"\u001B[0m");
        discardPile.add(currentPlayer.getCardFromHand(indexOfPlayedCard));
        currentPlayer.removeCardFromHand(indexOfPlayedCard);
    }
}
