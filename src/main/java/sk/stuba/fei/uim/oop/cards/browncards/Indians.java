package sk.stuba.fei.uim.oop.cards.browncards;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public class Indians extends BrownCard {
    public Indians() {
        super("Indians");
    }

    @Override
    public void play(List<Player> players, Player currentPlayer){
        removeAndDiscard(currentPlayer,this);
        boolean flag = false;
        for(Player attackedPlayer : players){
            if(!attackedPlayer.isDead() && !attackedPlayer.equals(currentPlayer)){
                for (Card card : attackedPlayer.getHand()) {
                    if (card instanceof Bang) {
                        attackedPlayer.removeBangFromHand();
                        System.out.println("\u001B[33mThe enemy player "+attackedPlayer.getName()+" blocked the attack and used Bang!\u001B[0m");
                        flag = true;
                        break;
                    }
                }
                if(flag){
                    continue;
                }
                attackedPlayer.decrementHp(1);
                System.out.println("\u001B[31m The enemy player "+attackedPlayer.getName()+" didnt have Bang and lost and HP, now he has\u001B[32m "+attackedPlayer.getHp()+" HP!\u001B[0m");
            }
        }
    }
}
