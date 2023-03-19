package sk.stuba.fei.uim.oop.cards.browncards;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Indians extends BrownCard {
    public Indians() {
        super("Indians");
    }


    @Override
    public void play(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        Card playedCard = currentPlayer.getCardFromHand(indexOfPlayedCard);
        for(int attackedPlayerIndex = 0; attackedPlayerIndex < players.size(); attackedPlayerIndex++){
            Player attackedPlayer = players.get(attackedPlayerIndex);
            if(attackedPlayerIndex != indexOfCurrentPlayer && !players.get(attackedPlayerIndex).isDead()) {
                if(attackedPlayer.hasBangOnHand()){
                    attackedPlayer.removeBangFromHand();
                    System.out.println("\u001B[33mThe enemy player "+players.get(attackedPlayerIndex).getName()+" blocked the attack and used Bang!\u001B[0m");
                }
                else if(!attackedPlayer.isDead()){
                    attackedPlayer.decrementHp(1);
                    System.out.println("\u001B[31m The enemy player "+players.get(attackedPlayerIndex).getName()+" didnt have Bang and lost and HP, now he has\u001B[32m "+players.get(attackedPlayerIndex).getHp()+" HP!\u001B[0m");
                }
            }
        }
        removeAndDiscard(currentPlayer,playedCard);
    }
}
