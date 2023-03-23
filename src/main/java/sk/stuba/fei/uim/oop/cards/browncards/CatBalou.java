package sk.stuba.fei.uim.oop.cards.browncards;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.bluecards.BlueCard;
import sk.stuba.fei.uim.oop.cards.bluecards.Prison;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.Random;

import java.util.List;

public class CatBalou extends BrownCard {
    Random rand;
    public CatBalou() {
        super( "CatBalou");
        rand = new Random();
    }

    @Override
    public void play(List<Player> players, Player currentPlayer){
        System.out.print("Choose a player to discard his cards, avaible players: \n");
        Player attackedPlayer = choosePlayerToAttack(players, currentPlayer);
        int discardFlag = 0;
        while(discardFlag < 1 || discardFlag > 2 ){
            discardFlag = KeyboardInput.readInt("Choose where to discard from:\n1.Discard from hand\n2. Discard from table\nPick(1/2)");
        }
        int randomIndex;
        if(discardFlag == 1 ){
            if(attackedPlayer.getHand().size()>0){
                randomIndex = rand.nextInt(attackedPlayer.getHand().size());
                Card discardCard = attackedPlayer.getCardFromHand(randomIndex);
                attackedPlayer.addToDiscardPile(discardCard);
                attackedPlayer.removeCardFromHand(discardCard);
                removeAndDiscard(currentPlayer,this);
                System.out.println("\n\u001B[32m" + attackedPlayer.getName() + " lost the card "+ discardCard.getName()+" from Hand!\u001B[0m");
            }
            else{
                System.out.println("\n\u001B[31mPlayer has no cards in hand!\u001B[0m");
            }
        }
        else{
            if(attackedPlayer.getTable().size()>0){
                randomIndex = rand.nextInt(attackedPlayer.getTable().size());
                BlueCard discardCard = attackedPlayer.getCardFromTable(randomIndex);
                if(discardCard instanceof Prison){
                    attackedPlayer.setIsInPrison(false);
                }
                attackedPlayer.addToDiscardPile(discardCard);
                attackedPlayer.removeCardFromTable(discardCard);
                System.out.println("\n\u001B[32m" + attackedPlayer.getName() + " lost the card "+ discardCard.getName()+" from Table!\u001B[0m");
                removeAndDiscard(currentPlayer,this);
            }
            else{
                System.out.println("\n\u001B[31mPlayer has no cards on table!\u001B[0m");
            }
        }


    }
}
