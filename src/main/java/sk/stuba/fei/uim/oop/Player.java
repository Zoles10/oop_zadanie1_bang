package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String name;
    private int hp;
    private List<Card> hand;
    private List<Card> table;
    private boolean isInPrison;

    Player(String name) {
        this.name = name;
        isInPrison = false;
        hp = 4;
        hand = new ArrayList<>();
        table = new ArrayList<>();
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
    public List<Card> getHand() {
        return this.hand;
    }
    public Card getCardFromHand(int i) {
        return this.hand.get(i);
    }
    public void removeCardFromHand(int i) {
        this.hand.remove(i);
    }
    public void addToHand(Card card) {
        this.hand.add(card);
    }
    public Card getCardFromTable(int i) {
        return this.table.get(i);
    }
    public void removeCardFromTable(int i) {
        this.table.remove(i);
    }
    public void addToTable(Card card) {
        this.table.add(card);
    }
    public List<Card> getTable(){
        return this.table;
    }
    public void setIsInPrison(boolean value){
        this.isInPrison = value;
    }
    public boolean getIsInPrison(){
        return this.isInPrison;
    }

    public void status() {
        printHp();
        printHand();
        printTable();
    }

    public void printHp(){
        System.out.println("\u001B[32m\n---\u001B[1m" + this.name + "---"+this.getHp()+" HP \u001B[0m");
    }

    public void printHand(){
        System.out.println("\u001B[35mHand: ");
        if(this.hand.size()>0) {
            for (int i = 0; i < hand.size(); i++) {
                System.out.println((i + 1) + ". " + hand.get(i).getName() + " ");
            }
            System.out.println("\n\u001B[0m");
        }
        else{
            System.out.println("---Empty---\n\u001B[0m");
        }
    }

    public void printTable(){
        System.out.println("\u001B[34mTable: ");
        if (this.table.size() > 0) {

            for (int i = 0; i < table.size(); i++) {
                System.out.print((i + 1) + ". " + table.get(i).getName() + " ");
            }
            System.out.println("\n\u001B[0m");
        } else {
            System.out.println("---Empty---\n\u001B[0m");
        }
    }

    private boolean hasCardOfType(List<Card> cards,Class<? extends Card> type) {
        for (Card card : cards) {
            if (type.isInstance(card)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasMissedOnHand() {
        return hasCardOfType(this.hand,Missed.class);
    }
    public boolean hasBangOnHand() {
        return hasCardOfType(this.hand,Bang.class);
    }
    public boolean hasPrisonOnTable() {
        return hasCardOfType(this.table,Prison.class);
    }
    public boolean hasDynamiteOnTable() {
        return hasCardOfType(this.table,Dynamite.class);
    }
    public boolean hasBarrelOnTable() {
        return hasCardOfType(this.table,Barrel.class);
    }

    private void removeCardOfType(List<Card> cards,Class<? extends Card> type, List<Card> discardPile) {
        for (Card card : cards) {
            if (type.isInstance(card)) {
                cards.remove(card);
                discardPile.add(card);
                break;
            }
        }
    }
    public void removeMissedFromHand(List<Card> discardPile) {
        removeCardOfType(hand, Missed.class, discardPile);
    }
    public void removeBangFromHand(List<Card> discardPile) {
        removeCardOfType(hand, Bang.class, discardPile);
    }

    public void checkTable(int currentPlayerIndex, List<Player> playerList, List<Card> discardPile){
        this.getTable().removeIf(card -> card.didExecute(playerList, currentPlayerIndex, discardPile) && card.getClass() != Barrel.class);
    }

    public boolean playCards(List<Player> playerList,int currentPlayerIndex, List<Card> cardStack, List<Card> discardPile){
        while(getHand().size() > 0){

            if(checkIfWin(playerList)){
                return false;
            }
            int cardPlayedIndex = -2;
            while(cardPlayedIndex < -1 || cardPlayedIndex > hand.size() - 1 ) {
                cardPlayedIndex = KeyboardInput.readInt("Pick which card to play") - 1;
            }
            if (cardPlayedIndex != -1) {
                //if a card is chosen, play it and remove from hand
                getCardFromHand(cardPlayedIndex).useEffect(playerList, currentPlayerIndex, cardPlayedIndex, cardStack, discardPile);
                if(!checkIfWin(playerList)){
                    return false;
                }
            }
            else{
                break;
            }
            status();
        }
        return true;
    }

    public boolean checkIfWin(List<Player> playerList){
        int playersAlive = 0;
        for(Player player : playerList){
            if(!player.isDead()){
                playersAlive++;
            }
        }
        if(playersAlive==1){
            System.out.println("\u001B[32m \u001B[1mThe winner is "+this.getName()+"\u001B[0m");
            return true;
        }
        return false;
    }

    public void discardCards(List<Card> discardPile){

        while(this.hand.size() > this.hp){
            printHand();
            int indexOfDiscardCard = -1;
            while(indexOfDiscardCard < 0 || indexOfDiscardCard > this.hand.size()-1) {
                indexOfDiscardCard = KeyboardInput.readInt("Pick which card to dicard") - 1;
            }
            discardPile.add(getCardFromHand(indexOfDiscardCard));
            removeCardFromHand(indexOfDiscardCard);
        }
    }

    public boolean isDead(){
        return this.hp < 1;
    }
}
