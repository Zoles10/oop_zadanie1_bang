package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Barrel extends Card{
    public Barrel(){
        super("Blue","Barrel");
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, List<Card> gameDeck, List<Card> discardPile){

        Player currentPlayer = players.get(indexOfCurrentPlayer);



    }



}
