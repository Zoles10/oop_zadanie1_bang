package sk.stuba.fei.uim.oop.cards.browncards;

import sk.stuba.fei.uim.oop.player.Player;


import java.util.List;

public class Bang extends BrownCard {
    public Bang(){
        super("Bang");
    }

    @Override
    public void play(List<Player> players, int indexOfCurrentPlayer){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        Player attackedPlayer = choosePlayerToAttack(players,indexOfCurrentPlayer);
        removeAndDiscard(currentPlayer,this);
        System.out.print("Pick which player to attack, players: \n");
        handleDefense(attackedPlayer,players,indexOfCurrentPlayer);
    }

    private void handleDefense(Player attackedPlayer, List<Player> players, int indexOfCurrentPlayer){
        if(attackedPlayer.defendWithBarrel(players, indexOfCurrentPlayer)){
            return;
        }
        if(attackedPlayer.defendWithMissed()){
            return;
        }
        attackedPlayer.decrementHp(1);
        System.out.println("\u001B[31mThe player " + attackedPlayer.getName()+ " got hit by Bang, he now has \u001B[32m"+attackedPlayer.getHp()+"HP\u001B[0m");
    }
}
