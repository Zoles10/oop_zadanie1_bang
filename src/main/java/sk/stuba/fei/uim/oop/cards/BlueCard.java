package sk.stuba.fei.uim.oop.cards;
import sk.stuba.fei.uim.oop.Card;
import sk.stuba.fei.uim.oop.Player;

import java.util.List;

public abstract class BlueCard extends Card {
    BlueCard(String meno){
        super(meno);
    }

    public abstract boolean didExucute();
}
