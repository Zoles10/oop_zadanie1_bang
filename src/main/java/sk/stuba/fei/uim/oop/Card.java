package sk.stuba.fei.uim.oop;

import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;

abstract public class Card {

    String name;

    public Card(){

    }

    public Card( String name) {
        this.name = name;
    }

    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){

    }

    public Player choosePlayerToAttack(List<Player> players, int indexOfCurrentPlayer ){
        for(int playerIndex = 0; playerIndex < players.size(); playerIndex++){
            if(playerIndex != indexOfCurrentPlayer) {
                System.out.println((playerIndex + 1)+". " + players.get(playerIndex).getName()+"\n");
            }

        }
        int attackedPlayerIndex = -1;
        while(attackedPlayerIndex < 0 || attackedPlayerIndex > players.size() -1 || attackedPlayerIndex == indexOfCurrentPlayer) {
            attackedPlayerIndex = KeyboardInput.readInt("Pick a player") - 1;
        }

        return players.get(attackedPlayerIndex);
    }

}
