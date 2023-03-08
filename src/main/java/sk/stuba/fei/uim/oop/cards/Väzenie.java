package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;
import java.util.Random;

public class Väzenie extends BlueCard{
    Random rand;
    public Väzenie(){
        super("Väzenie");
        rand = new Random();
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard,List<Card> gameDeck, List<Card> discardPile) {

    }


    @Override
    public boolean didExucute(){
        int chance = rand.nextInt(4);
        if(chance==0){
            return true;
        }
        return false;
    }
}
