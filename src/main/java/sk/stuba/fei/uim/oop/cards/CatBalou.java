package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.Random;

import java.util.List;

public class CatBalou extends BrownCard{
    public CatBalou() {
        super( "CatBalou");
    }

    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard,List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.print("Choose a player to discard his cards, avaible players: ");

        //TODO add check to see if player has cards

        Player attackedPlayer = choosePlayerToAttack(players,indexOfCurrentPlayer);

        int discardFlag = KeyboardInput.readInt("Choose where to discard from:\n1.Discard from hand\n2. Discard from table\n");
        int randomIndex;
        Random rand = new Random();

        if(discardFlag==1){
            if(attackedPlayer.getHand().size() > 0 ) {
                randomIndex = rand.nextInt(attackedPlayer.getHand().size());
                discardPile.add(attackedPlayer.getCardFromHand(randomIndex));
                attackedPlayer.removeCardFromHand(randomIndex);
                discardPile.add(currentPlayer.getCardFromHand(indexOfPlayedCard));
                currentPlayer.removeCardFromHand(indexOfPlayedCard);
            }
            else{
                System.out.println("Player has no card in hand");
            }
        }

        else if(discardFlag == 2){
            if(attackedPlayer.getTable().size()>0) {
                randomIndex = rand.nextInt(attackedPlayer.getTable().size());
                discardPile.add(attackedPlayer.getCardFromTable(randomIndex));
                attackedPlayer.removeCardFromTable(randomIndex);
                discardPile.add(currentPlayer.getCardFromHand(indexOfPlayedCard));
                currentPlayer.removeCardFromHand(indexOfPlayedCard);
            }
            else{
                System.out.println("Player has no card on table");
            }
        }
        else{
            System.out.print("Not a valid choice");
        }



    }
}
