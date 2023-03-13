package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;


import java.util.List;

public class Bang extends BrownCard {
    public Bang(){
        super("Bang");
    }

    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard){

        Player currentPlayer = players.get(indexOfCurrentPlayer);
        Player attackedPlayer = choosePlayerToAttack(players,indexOfCurrentPlayer);
        Card playedCard = currentPlayer.getCardFromHand(indexOfPlayedCard);
        System.out.print("Pick which player to attack, players: \n");

        if(attackedPlayer.hasBarrelOnTable()){
            for(Card card : attackedPlayer.getTable()) {
                if (card instanceof Barrel && card.didExecute(players, indexOfCurrentPlayer)) {
                    System.out.print("\u001B[33mThe player succesfully blocked your attack with a Barrel!\u001B[0m");
                    return;
                }
            }
        }

        if(attackedPlayer.hasMissedOnHand()){
            attackedPlayer.removeMissedFromHand();
            System.out.println("\u001B[33mThe player used Missed!\u001B[0m");
        }
        else {
            attackedPlayer.decrementHp(1);
            System.out.println("\u001B[31mThe player " + attackedPlayer.getName()+ " got hit by Bang, he now has \u001B[32m"+attackedPlayer.getHp()+"HP\u001B[0m");
        }
        currentPlayer.addToDiscardPile(playedCard);
        currentPlayer.removeCardFromHand(playedCard);
    }
}
