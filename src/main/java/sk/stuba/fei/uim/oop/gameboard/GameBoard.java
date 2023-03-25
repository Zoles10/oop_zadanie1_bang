package sk.stuba.fei.uim.oop.gameboard;

import sk.stuba.fei.uim.oop.cards.Card;
import sk.stuba.fei.uim.oop.cards.bluecards.*;
import sk.stuba.fei.uim.oop.cards.browncards.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class GameBoard {
    private final List<Card> deck;
    private final List<Card> discardPile;

    public GameBoard() {
        this.discardPile = new ArrayList<>();
        this.deck = new ArrayList<>();
        createDeck();
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public List<Card> getDiscardPile() {
        return this.discardPile;
    }

    private void createDeck() {
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

    public Card drawCard() {
        if (deck.size() == 0 && discardPile.size() != 0) {
            refillDeck();
        }
        Card card = deck.get(0);
        deck.remove(card);
        return card;
    }

    public void removeFromDeck(Card card) {
        this.deck.remove(card);
    }

    private void refillDeck() {
        List<Card> temp = new ArrayList<>(deck);
        deck.clear();
        deck.addAll(discardPile);
        discardPile.clear();
        discardPile.addAll(temp);
        Collections.shuffle(deck);
        System.out.println("Refilling cards from discard pile!");
    }

    public void addToDiscardPile(Card card) {
        this.discardPile.add(card);
    }

    public void status() {
        System.out.println("\nCards in deck: " + this.getDeck().size());
        System.out.println("Cards in discardPile: " + this.getDiscardPile().size());
    }
}
