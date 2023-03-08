package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Indiáni extends BrownCard{
    public Indiáni() {
        super("Indiáni");
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);

        for(int enemyPlayerIndex = 0; enemyPlayerIndex < players.size(); enemyPlayerIndex++){
            if(enemyPlayerIndex != indexOfCurrentPlayer) {
                if(players.get(enemyPlayerIndex).hasBang()){
                    players.get(enemyPlayerIndex).removeBangFromDeck();
                }
                else{
                    players.get(enemyPlayerIndex).setHp( players.get(enemyPlayerIndex).getHp() - 1);
                }
            }

        }
        discardPile.add(currentPlayer.getCardFromDeck(indexOfPlayedCard));

        currentPlayer.removeCardFromDeck(indexOfPlayedCard);
    }
}
