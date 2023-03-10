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

        for(int playerIndex = 0; playerIndex < players.size(); playerIndex++){
            if(playerIndex != indexOfCurrentPlayer) {
                System.out.print((playerIndex + 1)+". " + players.get(playerIndex).getName()+"\n");
            }

        }
        int attackedPlayerIndex = KeyboardInput.readInt("Pick a player") - 1;

        Player attackedPlayer = players.get(attackedPlayerIndex);

        int discardFlag = KeyboardInput.readInt("Choose where to discard from:\n1.Discard from hand\n2. Discard from table\n");
        int randomIndex;
        Random rand = new Random();


        if(discardFlag==1){
            if(attackedPlayer.getDeck().size() > 0 ) {
                randomIndex = rand.nextInt(attackedPlayer.getDeck().size());
                discardPile.add(attackedPlayer.getCardFromDeck(randomIndex));
                attackedPlayer.removeCardFromDeck(randomIndex);
                discardPile.add(currentPlayer.getCardFromDeck(indexOfPlayedCard));
                currentPlayer.removeCardFromDeck(indexOfPlayedCard);
            }
            else{
                System.out.println("Player has no card in hand");
            }

        }

        else if(discardFlag == 2){
            if(attackedPlayer.getPassiveEffects().size()>0) {
                randomIndex = rand.nextInt(attackedPlayer.getPassiveEffects().size());
                discardPile.add(attackedPlayer.getCardFromPassiveEffects(randomIndex));
                attackedPlayer.removeCardFromPassiveEffects(randomIndex);
                discardPile.add(currentPlayer.getCardFromDeck(indexOfPlayedCard));
                currentPlayer.removeCardFromDeck(indexOfPlayedCard);
            }
            else{
                System.out.println("Player has no card on table");
            }
        }
        else{
            System.out.printf("Not a valid choice");
        }



    }
}
