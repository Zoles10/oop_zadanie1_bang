package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;
import java.util.Scanner;

import java.util.List;

public class Bang extends BrownCard {
    public Bang(){
        super("Bang");
    }

    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){

        //TODO add check if enemy player has BARREL on TABLE

        Scanner scanner = new Scanner(System.in);
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.print("Pick which player to attack, players: ");

        for(int playerIndex = 0; playerIndex < players.size(); playerIndex++){
            if(playerIndex != indexOfCurrentPlayer) {
                System.out.print((playerIndex + 1)+". " + players.get(playerIndex).getName()+"\n");
            }

        }
        System.out.print("Pick a player: ");
        int attackedPlayerIndex = scanner.nextInt() - 1;

        Player attackedPlayer = players.get(attackedPlayerIndex);

        if(attackedPlayer.hasVedla()){
            discardPile.add(new Vedľa());
            attackedPlayer.removeVedlaFromDeck();
            System.out.println("The player used Vedľa!");
        }
        else {
            attackedPlayer.setHp(attackedPlayer.getHp() - 1);
            System.out.println("The player lost an HP, he now has "+attackedPlayer.getHp());
        }

        discardPile.add(currentPlayer.getCardFromDeck(indexOfPlayedCard));

        currentPlayer.removeCardFromDeck(indexOfPlayedCard);



    }
}
