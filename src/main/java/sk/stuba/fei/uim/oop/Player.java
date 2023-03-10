package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.cards.*;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int hp;
    private List<Card> deck;

    private List<Card> passiveEffects;

    private boolean isInPrison;

    Player(String name) {
        this.name = name;
        isInPrison = false;
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
    public void setIsInPrison(boolean value){
        this.isInPrison = value;
    }
    public boolean getIsInPrison(){
        return this.isInPrison;
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
        this.passiveEffects.add(card);
    }
    public List<Card> getPassiveEffects(){
        return this.passiveEffects;
    }

    public void status() {
        printHp();
        printHand();
        printTable();
    }

    public void printHp(){
        System.out.println("\u001B[32m\n---" + this.name + "---"+this.getHp()+" HP \u001B[0m");
    }

    public void printHand(){
        System.out.println("\u001B[35mHand: ");
        if(this.deck.size()>0) {
            for (int i = 0; i < deck.size(); i++) {
                System.out.println((i + 1) + ". " + deck.get(i).name + " ");
            }
            System.out.println("\n\u001B[0m");
        }
        else{
            System.out.println("---Empty---\n\u001B[0m");
        }

    }

    public void printTable(){
        System.out.println("\u001B[34mTable: ");
        if (this.passiveEffects.size() > 0) {

            for (int i = 0; i < passiveEffects.size(); i++) {
                System.out.print((i + 1) + ". " + passiveEffects.get(i).name + " ");
            }
            System.out.println("\n\u001B[0m");
        } else {
            System.out.println("---Empty---\n\u001B[0m");
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
    public boolean hasBarrelOnTable() {
        for (Card card : passiveEffects) {
            if (card instanceof Barrel) {
                return true;
            }
        }
        return false;
    }
    public void removeBarrel(List<Card> discardPile) {
        for (Card card : passiveEffects) {
            if (card instanceof Barrel) {
                passiveEffects.remove(card);
                discardPile.add(card);
                break;
            }
        }
    }
    public void removeDynamite(List<Card> discardPile) {
        for (Card card : passiveEffects) {
            if (card instanceof Dynamit) {
                passiveEffects.remove(card);
                discardPile.add(card);
                break;
            }
        }
    }
    public void removePrison(List<Card> discardPile) {
        for (Card card : passiveEffects) {
            if (card instanceof Väzenie) {
                passiveEffects.remove(card);
                discardPile.add(card);
                break;
            }
        }
    }
    public void removeBangFromDeck() {
        for (Card card : deck) {
            if (card instanceof Bang) {
                deck.remove(card);
                break;
            }
        }
    }


    //TODO when playing BLUE cards, check if player has that one on table already

    //TODO fix DYNAMITE, crash

    //TODO fix PRISON, player has turns until he escapes
    public void checkTable(int currentPlayerIndex, List<Player> playerList, List<Card> discardPile){
        Boolean removePrisonFlag = false;
        Boolean removeDynamiteFlag = false;

        for (Card blueCard : this.getPassiveEffects()) {

            if (blueCard instanceof Dynamit) {
                if(((Dynamit) blueCard).didExecute()){
                    this.setHp(this.getHp() - 3);
                    System.out.println("\u001B[32mDynamite exploded!\u001B[0m");
                    removeDynamiteFlag = true;
                }

                else{
                    this.removeDynamite(discardPile);
                    System.out.println("Dynamite didnt explode and passed to another player");
                    if(currentPlayerIndex == 0){
                        playerList.get(playerList.size()-1).addToPassiveEffects(new Dynamit());
                    }

                    else{
                        playerList.get(currentPlayerIndex-1).addToPassiveEffects(new Dynamit());
                    }
                    removeDynamiteFlag = true;
                }
            }

            else if(blueCard instanceof Väzenie){

                if(((Väzenie) blueCard).didExecute()){
                    System.out.println("You escape prison!");
                    removePrisonFlag = true;

                    this.setIsInPrison(false);
                }
                else {

                    this.setIsInPrison(true);
                    System.out.println("You didnt escape prison and skip a turn");
                }
            }
        }

        if(removePrisonFlag){
            this.removePrison(discardPile);
        }

        if(removeDynamiteFlag){
            this.removeDynamite(discardPile);
        }
    }

    public boolean isDead(){
        return this.hp < 1 ? true : false;
    }
}
