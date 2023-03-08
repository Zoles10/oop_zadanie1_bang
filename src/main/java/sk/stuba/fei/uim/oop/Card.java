package sk.stuba.fei.uim.oop;

import java.util.List;

abstract public class Card {
    String color;

    String name;

    public Card(String color, String name) {
        this.color = color;
        this.name = name;
    }

    public void useEffect(List<Player> players, int indexOfCurrentPlayer, List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
    }


}
