package sk.stuba.fei.uim.oop.gameboard;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.bluecards.Barrel;
import sk.stuba.fei.uim.oop.cards.bluecards.Dynamite;
import sk.stuba.fei.uim.oop.cards.bluecards.Prison;
import sk.stuba.fei.uim.oop.cards.browncards.*;
import sk.stuba.fei.uim.oop.player.Player;

import java.util.ArrayList;
import java.util.List;

import java.util.Collections;

public class Gameboard {
    private List<Card> deck;
    private List<Card> discardPile;

    public Gameboard(){
        this.discardPile = new ArrayList<>();
        this.deck = new ArrayList<>();
        createDeck();

    }

    public void createDeck() {
        for (int i = 0; i < 30; i++) {
            deck.add(new Bang());
        }
        for (int i = 0; i < 15; i++) {
            deck.add(new Missed());
        }
        for (int i = 0; i < 8; i++) {
            deck.add(new Beer());
        }
        for (int i = 0; i < 6; i++) {
            deck.add(new CatBalou());
        }
        for (int i = 0; i < 4; i++) {
            deck.add(new Stagecoach());
        }
        deck.add(new Prison());
        deck.add(new Prison());
        deck.add(new Prison());
        deck.add(new Indians());
        deck.add(new Indians());
        deck.add(new Barrel());
        deck.add(new Barrel());
        deck.add(new Dynamite());
        Collections.shuffle(deck);
    }

    public Card drawCard(){
        if(deck.size()<1){
            refillDeck();
        }
        Card card  = deck.get(deck.size()-1);
        deck.remove(card);
        return card;
    }


    public void refillDeck(){
        List<Card> temp = new ArrayList<>(deck);
        deck.clear();
        deck.addAll(discardPile);
        discardPile.clear();
        discardPile.addAll(temp);
        Collections.shuffle(deck);
        System.out.println("Refilling cards");
    }

    public void addToDiscardPile(Card card){
        this.discardPile.add(card);
    }
}
