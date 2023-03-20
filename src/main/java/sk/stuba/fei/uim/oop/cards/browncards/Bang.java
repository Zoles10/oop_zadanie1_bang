package sk.stuba.fei.uim.oop.cards.browncards;

import sk.stuba.fei.uim.oop.player.Player;


import java.util.List;

public class Bang extends BrownCard {
    public Bang(){
        super("Bang");
    }

    @Override
    public void play(List<Player> players, Player currentPlayer){
        Player attackedPlayer = choosePlayerToAttack(players,currentPlayer);
        removeAndDiscard(currentPlayer,this);
        System.out.print("Pick which player to attack, players: \n");
        handleDefense(attackedPlayer,players);
    }

    private void handleDefense(Player attackedPlayer, List<Player> players){
        if(attackedPlayer.defendWithBarrel(players)){
            return;
        }
        if(attackedPlayer.defendWithMissed()){
            return;
        }
        attackedPlayer.decrementHp(1);
        System.out.println("\u001B[31mThe player " + attackedPlayer.getName()+ " got hit by Bang, he now has \u001B[32m"+attackedPlayer.getHp()+"HP\u001B[0m");
    }
}
