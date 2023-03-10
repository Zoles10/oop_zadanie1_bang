package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Missed extends BrownCard{
    public Missed() {
        super("Missed");
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard,List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.println("You cannot play Missed!");
    }
}
