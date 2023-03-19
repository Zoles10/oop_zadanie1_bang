package sk.stuba.fei.uim.oop.cards.bluecards;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;
import java.util.Random;

import java.util.List;

public class Barrel extends BlueCard {

    Random rand;

    public Barrel(){
        super("Barrel");
        rand = new Random();
    }

    @Override
    public void play(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        //Card playedCard = currentPlayer.getCardFromHand(indexOfPlayedCard);
        if(currentPlayer.hasBarrelOnTable()){
            System.out.println("You cannot play Barrel, you are have one on the table!");
            return;
        }
        currentPlayer.addToTable(this);
        currentPlayer.removeCardFromHand(this);

    }

    @Override
    public boolean didExecute(List<Player> playerList, Card playedCard,int indexOfCurrentPlayer){
        int chance = rand.nextInt(4);
        return chance == 0;
    }


}