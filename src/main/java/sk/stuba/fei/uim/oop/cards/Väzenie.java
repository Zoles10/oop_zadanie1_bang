package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.List;
import java.util.Random;

public class Väzenie extends BlueCard{
    Random rand;
    public Väzenie(){
        super("Väzenie");
        rand = new Random();
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer, int indexOfPlayedCard,List<Card> gameDeck, List<Card> discardPile) {
        Player currentPlayer = players.get(indexOfCurrentPlayer);
        System.out.print("Pick which player to INPRISON, players: ");

        for(int playerIndex = 0; playerIndex < players.size(); playerIndex++){
            if(playerIndex != indexOfCurrentPlayer) {
                System.out.print((playerIndex + 1)+". " + players.get(playerIndex).getName()+"\n");
            }

        }
        int attackedPlayerIndex = KeyboardInput.readInt("Pick a player") - 1;

        Player attackedPlayer = players.get(attackedPlayerIndex);

        attackedPlayer.addToPassiveEffects(currentPlayer.getCardFromDeck(indexOfPlayedCard));
        attackedPlayer.setIsInPrison(true);
        currentPlayer.removeCardFromDeck(indexOfPlayedCard);
    }

    @Override
    public boolean didExecute(){
        int chance = rand.nextInt(4);
        if(chance==0){
            return true;
        }
        return false;
    }
}
