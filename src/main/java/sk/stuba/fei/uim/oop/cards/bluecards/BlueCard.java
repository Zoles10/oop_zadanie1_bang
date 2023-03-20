package sk.stuba.fei.uim.oop.cards.bluecards;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.List;

public abstract class BlueCard extends Card {
    BlueCard(String meno){
        super(meno);
    }

    public abstract boolean didExecute(List<Player> playerList,Player currentPlayer);

}
