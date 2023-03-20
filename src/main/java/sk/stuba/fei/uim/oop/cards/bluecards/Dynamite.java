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
    public void play(List<Player> players, Player currentPlayer){
        for (Card card : currentPlayer.getTable()) {
            if (card instanceof Dynamite) {
                System.out.println("\u001B[31mYou cannot place dynamite, you already have one on the table!\u001B[0m");
                return;
            }
        }
        currentPlayer.addToTable(this);
        currentPlayer.removeCardFromHand(this);
    }

    @Override
    public boolean didExecute(List<Player> playerList, Player currentPlayer){
        int chance = rand.nextInt(8);
        if(chance == 0){
            currentPlayer.decrementHp(3);
            System.out.println("\u001B[31mDynamite exploded! You got 3 dmg and you have \u001B[32m"+ currentPlayer.getHp()+" HP.\u001B[0m");
            currentPlayer.addToDiscardPile(this);
        }
        else{
            int indexOfNextPlayer = currentPlayer.getIndex() == 0 ? playerList.size()-1 : currentPlayer.getIndex()-1;
            while(playerList.get(indexOfNextPlayer).isDead()){
                indexOfNextPlayer = indexOfNextPlayer == 0 ? playerList.size()-1 : indexOfNextPlayer - 1;
            }
            System.out.println("\u001B[33mDynamite didnt explode and passed to "+ playerList.get(indexOfNextPlayer).getName()+"\u001B[0m");
            playerList.get(indexOfNextPlayer).addToTable(this);
        }
        return true;
    }
}
