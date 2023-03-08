package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class CatBalou extends BrownCard{
    public CatBalou() {
        super( "CatBalou");
    }

    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.print("Choose a player to discard his cards: ");

    }
}
