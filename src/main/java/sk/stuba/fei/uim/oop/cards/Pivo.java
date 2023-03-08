package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Pivo extends BrownCard{
    public Pivo() {
        super( "Pivo");
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard,List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        currentPlayer.setHp(currentPlayer.getHp()+1);
        System.out.println("played pivo");

        discardPile.add(currentPlayer.getCardFromDeck(indexOfPlayedCard));

        currentPlayer.removeCardFromDeck(indexOfPlayedCard);
    }
}
