package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;


import java.util.List;

public class Bang extends BrownCard {
    public Bang(){
        super("Bang");
    }

    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){

        //TODO add check if enemy player has BARREL on TABLE

        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.print("Pick which player to attack, players: ");

        Player attackedPlayer = choosePlayerToAttack(players,indexOfCurrentPlayer);

        if(attackedPlayer.hasBarrelOnTable()){
            Barrel barrel = new Barrel();
            if(barrel.didExecute()){
                attackedPlayer.removeBarrelFromTable(discardPile);
                System.out.print("\u001B[33mThe player succesfully blocked your attack with a Barrel!\u001B[0m");
                return;
            }
        }

        if(attackedPlayer.hasMissedOnHand()){
            discardPile.add(new Missed());
            attackedPlayer.removeMissedFromHand(discardPile);
            System.out.println("\u001B[33mThe player used Missed!\u001B[0m");
        }
        else {
            attackedPlayer.setHp(attackedPlayer.getHp() - 1);
            System.out.println("\u001B[31mThe player " + attackedPlayer.getName()+ " git hit by Bang, he now has \u001B[32m"+attackedPlayer.getHp()+"HP\u001B[0m");

            if(attackedPlayer.getHp() < 1){
                players.remove(attackedPlayer);
            }
        }

        discardPile.add(currentPlayer.getCardFromHand(indexOfPlayedCard));

        currentPlayer.removeCardFromHand(indexOfPlayedCard);



    }
}
