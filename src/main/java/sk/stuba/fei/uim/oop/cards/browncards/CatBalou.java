package sk.stuba.fei.uim.oop.cards.browncards;
import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.bluecards.BlueCard;
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
    public void play(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.print("Choose a player to discard his cards, avaible players: \n");
        Player attackedPlayer = choosePlayerToAttack(players,indexOfCurrentPlayer);
        Card playedCard = currentPlayer.getCardFromHand(indexOfPlayedCard);
        int discardFlag = 0;
        while(discardFlag < 1 || discardFlag > 2 ){
            discardFlag = KeyboardInput.readInt("Choose where to discard from:\n1.Discard from hand\n2. Discard from table\n");
        }
        int randomIndex;
        if(discardFlag == 1 ){
            if(attackedPlayer.getHand().size()>0){
                randomIndex = rand.nextInt(attackedPlayer.getHand().size());
                Card discardCard = attackedPlayer.getCardFromHand(randomIndex);
                attackedPlayer.addToDiscardPile(discardCard);
                attackedPlayer.removeCardFromHand(discardCard);
                removeAndDiscard(currentPlayer,playedCard);
            }
            else{
                System.out.println("Player has no cards in hand");
            }
        }
        else{
            if(attackedPlayer.getTable().size()>0){
                randomIndex = rand.nextInt(attackedPlayer.getTable().size());
                BlueCard discardCard = attackedPlayer.getCardFromTable(randomIndex);
                attackedPlayer.addToDiscardPile(discardCard);
                attackedPlayer.removeCardFromTable(discardCard);
                removeAndDiscard(currentPlayer,playedCard);
            }
            else{
                System.out.println("Player has no cards on table");
            }
        }

    }
}
