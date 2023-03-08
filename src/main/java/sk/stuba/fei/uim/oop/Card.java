package sk.stuba.fei.uim.oop;

import java.util.List;

abstract public class Card {

    String name;

    public Card(){

    }

    public Card( String name) {
        this.name = name;
    }

    public void useEffect(List<Player> players, int indexOfCurrentPlayer, List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
    }


}
