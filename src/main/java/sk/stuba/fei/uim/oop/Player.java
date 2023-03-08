package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import  java.util.Iterator;

public class Player {
    private String name;
    private int hp;
    private List<Card> deck;

    private List<Card> passiveEffects;

    Player(String name) {
        this.name = name;
        hp = 4;
        deck = new ArrayList<>();
        passiveEffects = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public int getHp() {
        return this.hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public List<Card> getDeck() {
        return this.deck;
    }

    public Card getCardFromDeck(int i) {
        return this.deck.get(i);
    }

    public void removeCardFromDeck(int i) {
        this.deck.remove(i);
    }

    public void addToDeck(Card card) {
        this.deck.add(card);
    }

    public Card getCardFromPassiveEffects(int i) {
        return this.passiveEffects.get(i);
    }

    public void removeCardFromPassiveEffects(int i) {
        this.passiveEffects.remove(i);
    }

    public void addToPassiveEffects(Card card) {
        this.deck.add(card);
    }


    public void status() {
        System.out.println("\nPlayer name: " + this.name);
        System.out.println("Hp: " + this.hp);
        System.out.println("Cards on hand: ");
        for (int i = 0; i < deck.size(); i++) {
            System.out.print((i + 1) + ". " + deck.get(i).name + " ");
        }

        if (this.passiveEffects.size() > 0) {
            System.out.println("Blue cards in front of player: ");
            for (int i = 0; i < passiveEffects.size(); i++) {
                System.out.print((i + 1) + ". " + passiveEffects.get(i).name + " ");
            }
            System.out.println("\n");
        } else {
            System.out.println("\nPlayer has no cards in front\n");
        }
    }

    public boolean hasVedla() {
        for (Card card : deck) {
            if (card instanceof Vedľa) {
                return true;
            }
        }
        return false;
    }

    public void removeVedlaFromDeck() {
        for (Card card : deck) {
            if (card instanceof Vedľa) {
                deck.remove(card);
                break;
            }
        }
    }

    public boolean hasBang() {
        for (Card card : deck) {
            if (card instanceof Bang) {
                return true;
            }
        }
        return false;
    }

    public void removeBangFromDeck() {
        for (Card card : deck) {
            if (card instanceof Bang) {
                deck.remove(card);
                break;
            }
        }
    }
}
