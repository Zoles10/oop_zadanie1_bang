package sk.stuba.fei.uim.oop.cards.browncards;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

abstract public class BrownCard extends Card {
    BrownCard(String meno){
        super(meno);
    }
    protected void removeAndDiscard(Player player, Card card){
        player.addToDiscardPile(card);
        player.removeCardFromHand(card);
    }
}

