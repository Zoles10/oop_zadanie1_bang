package sk.stuba.fei.uim.oop.cards;

import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.List;

abstract public class Card {

    private final String name;

    public Card(String name) {
        this.name = name;
    }

    public abstract void play(List<Player> players, int indexOfCurrentPlayer);

    protected Player choosePlayerToAttack(List<Player> players, int indexOfCurrentPlayer ){
        for(int playerIndex = 0; playerIndex < players.size(); playerIndex++){
            if(playerIndex != indexOfCurrentPlayer && !players.get(playerIndex).isDead()) {
                System.out.println((playerIndex + 1)+". " + players.get(playerIndex).getName()+"");
            }
        }
        int attackedPlayerIndex = -1;
        while(attackedPlayerIndex < 0 || attackedPlayerIndex > players.size() -1 || attackedPlayerIndex == indexOfCurrentPlayer || players.get(attackedPlayerIndex).isDead() ) {
            attackedPlayerIndex = ZKlavesnice.readInt("Pick a player: ") - 1;
        }
        return players.get(attackedPlayerIndex);
    }


    public String getName(){
        return this.name;
    }
}
