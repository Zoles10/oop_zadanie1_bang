package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public class Indians extends BrownCard{
    public Indians() {
        super("Indians");
    }


    @Override
    public void useEffect(List<Player> players, int indexOfCurrentPlayer,int indexOfPlayedCard, List<Card> gameDeck, List<Card> discardPile){
        Player currentPlayer = players.get(indexOfCurrentPlayer);

        for(int enemyPlayerIndex = 0; enemyPlayerIndex < players.size(); enemyPlayerIndex++){
            if(enemyPlayerIndex != indexOfCurrentPlayer) {
                if(players.get(enemyPlayerIndex).hasBangOnHand()){
                    players.get(enemyPlayerIndex).removeBangFromHand(discardPile);
                    System.out.println("\u001B[33mThe enemy player "+players.get(enemyPlayerIndex).getName()+" blocked the attack and used Bang!\u001B[0m");
                }
                else{
                    players.get(enemyPlayerIndex).setHp( players.get(enemyPlayerIndex).getHp() - 1);
                    System.out.println("\u001B[31m The enemy player "+players.get(enemyPlayerIndex).getName()+" didnt have Bang and lost and HP, now he has\u001B[32m "+players.get(enemyPlayerIndex).getHp()+" HP!\u001B[0m");
                }
            }

        }
        discardPile.add(currentPlayer.getCardFromHand(indexOfPlayedCard));

        currentPlayer.removeCardFromHand(indexOfPlayedCard);
    }
}
