package sk.stuba.fei.uim.oop.cards.bluecards;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;
import java.util.Random;

public class Dynamite extends BlueCard {
    Random rand;
    public Dynamite() {
        super( "Dynamite");
        rand = new Random();
    }


    @Override
    public void play(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        if(currentPlayer.hasDynamiteOnTable()){
            System.out.println("You cannot place dynamite, you already have one on the table!");
            return;
        }
        currentPlayer.addToTable(currentPlayer.getCardFromHand(indexOfPlayedCard));
        currentPlayer.removeCardFromHand(indexOfPlayedCard);
    }

    @Override
    public boolean didExecute(List<Player> playerList, Card playedCard, int indexOfCurrentPlayer){
        int chance = rand.nextInt(8);
        Player currentPlayer = playerList.get(indexOfCurrentPlayer);
        if(chance == 0){
            currentPlayer.decrementHp(3);
            System.out.println("\u001B[31mDynamite exploded! You got 3 dmg and you have \u001B[32m"+ currentPlayer.getHp()+" HP.\u001B[0m");
            currentPlayer.addToDiscardPile(playedCard);
        }
        else{
            int indexOfNextPlayer = indexOfCurrentPlayer == 0 ? playerList.size()-1 : indexOfCurrentPlayer-1;
            while(playerList.get(indexOfNextPlayer).isDead()){
                if(indexOfNextPlayer == 0){
                    indexOfNextPlayer = playerList.size()-1;
                }
                else{
                    indexOfNextPlayer--;
                }
            }
            System.out.println("\u001B[33mDynamite didnt explode and passed to "+ playerList.get(indexOfNextPlayer).getName()+"\u001B[0m");
            playerList.get(indexOfNextPlayer).addToTable(new Dynamite());
        }
        return true;
    }
}
