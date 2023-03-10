package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;


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

        for(int playerIndex = 0; playerIndex < players.size(); playerIndex++){
            if(playerIndex != indexOfCurrentPlayer) {
                System.out.print((playerIndex + 1)+". " + players.get(playerIndex).getName()+"\n");
            }

        }
        int attackedPlayerIndex = KeyboardInput.readInt("Pick a player") - 1;

        Player attackedPlayer = players.get(attackedPlayerIndex);

        if(attackedPlayer.hasBarrelOnTable()){
            Barrel barrel = new Barrel();
            if(barrel.didExecute()){
                attackedPlayer.removeBarrel(discardPile);
                System.out.printf("\u001B[33mThe player succesfully blocked your attack with a Barrel!\u001B[0m");
                return;
            }
        }

        if(attackedPlayer.hasVedla()){
            discardPile.add(new Vedľa());
            attackedPlayer.removeVedlaFromDeck();
            System.out.println("\u001B[33mThe player used Vedľa!\u001B[0m");
        }
        else {
            attackedPlayer.setHp(attackedPlayer.getHp() - 1);
            System.out.println("\u001B[31mThe player lost an HP, he now has "+attackedPlayer.getHp()+"\u001B[0m");
        }

        discardPile.add(currentPlayer.getCardFromDeck(indexOfPlayedCard));

        currentPlayer.removeCardFromDeck(indexOfPlayedCard);



    }
}
