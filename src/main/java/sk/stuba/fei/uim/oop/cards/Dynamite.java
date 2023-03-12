package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;
import java.util.Random;

public class Dynamite extends BlueCard {
    Random rand;
    public Dynamite() {
        super( "Dynamite");
        rand = new Random();
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        if(currentPlayer.hasDynamiteOnTable()){
            System.out.println("You cannot place dynamite, you already have one on the table!");
        }
        currentPlayer.addToTable(currentPlayer.getCardFromHand(indexOfPlayedCard));
        currentPlayer.removeCardFromHand(indexOfPlayedCard);

    }

    @Override
    public boolean didExecute(List<Player> playerList, int indexOfCurrentPlayer, List<Card> discardPile){
        int chance = rand.nextInt(8);
        Player currentPlayer = playerList.get(indexOfCurrentPlayer);
        if(chance == 0){
            currentPlayer.setHp(currentPlayer.getHp() - 3);
            System.out.println("\u001B[31mDynamite exploded! You got 3 dmg and you have \u001B[32m"+ currentPlayer.getHp()+" HP.\u001B[0m");
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
