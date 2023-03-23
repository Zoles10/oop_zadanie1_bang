package sk.stuba.fei.uim.oop.player;
import sk.stuba.fei.uim.oop.gameboard.GameBoard;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.cards.bluecards.*;
import sk.stuba.fei.uim.oop.cards.browncards.Missed;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final int index;
    private int hp;
    private final List<Card> hand;
    private final List<BlueCard> table;
    private boolean isInPrison;
    private final GameBoard gameBoard;
    public Player(String name,int index ,GameBoard gameBoard) {
        this.name = name;
        isInPrison = false;
        this.index = index;
        hp = 4;
        hand = new ArrayList<>();
        table = new ArrayList<>();
        this.gameBoard = gameBoard;
    }

    public String getName() {
        return this.name;
    }
    public int getHp() {
        return Math.max(this.hp, 0);
    }
    public int getIndex(){
        return this.index;
    }
    public void incrementHp(int num){
        this.hp = this.hp + num;
    }
    public void decrementHp(int num){
        this.hp = this.hp - num;
    }
    public List<Card> getHand() {
        return this.hand;
    }
    public List<BlueCard> getTable(){
        return this.table;
    }
    public BlueCard getCardFromTable(int i) {
        return this.table.get(i);
    }
    public void removeCardFromTable(BlueCard card) {
        this.table.remove(card);
    }
    public Card getCardFromHand(int i) {
        return this.hand.get(i);
    }
    public void removeCardFromHand(int i) {
        this.hand.remove(i);
    }
    public void removeCardFromHand(Card card) {
        this.hand.remove(card);
    }
    public void addToTable(BlueCard card) {
        this.table.add(card);
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

    public void checkTable(List<Player> playerList){
        for(BlueCard card : getTable()){
            if(card instanceof Dynamite && card.didExecute(playerList, this)){
                this.table.remove(card);
                break;
            }
        }
        if(this.hp < 1){
            return;
        }
        for(BlueCard card : getTable()){
            if(card instanceof Prison && card.didExecute(playerList, this)){
                this.table.remove(card);
                break;
            }
        }
    }
    public boolean playCards(List<Player> playerList){
        while(getHand().size() > 0){
            if(gameNotInProgress(playerList)){
                return false;
            }
            if(this.hp < 1){
                return true;
            }

            int cardPlayedIndex = -2;
            while(cardPlayedIndex < -1 || cardPlayedIndex > hand.size() - 1 ) {
                cardPlayedIndex = ZKlavesnice.readInt("Pick which card to play (Enter 0 to stop): ") - 1;
            }
            if (cardPlayedIndex != -1) {
                getCardFromHand(cardPlayedIndex).play(playerList, this);
                if(gameNotInProgress(playerList)){
                    return false;
                }
            }
            else{
                break;
            }
            gameBoard.status();
            status();

        }
        return true;
    }

    public void drawCards(int num){
        for(int i = 0;i < num;i++){
            if(gameBoard.getDeck().size() == 0 && gameBoard.getDiscardPile().size()==0){
                System.out.println(i == 0 ? "There are no card to be drawn, sorry!" : "You could olny draw "+i+" card, there are no more left!");
                return;
            }
            Card drawnCard = gameBoard.drawCard();
            this.hand.add(drawnCard);
            gameBoard.removeFromDeck(drawnCard);
        }
    }

    public void addToDiscardPile(Card card){
        gameBoard.addToDiscardPile(card);
    }

    private boolean gameNotInProgress(List<Player> playerList){
        int playersAlive = 0;
        for(Player player : playerList){
            if(!player.isDead()){
                playersAlive++;
            }
        }
        if(playersAlive==1){
            for(Player player : playerList){
                if(!player.isDead()){
                    System.out.println("\u001B[32m \u001B[1mThe winner is "+player.getName()+"\u001B[0m");
                    return true;
                }
            }

        }
        return false;
    }

    public void discardCards(){
        while(this.hand.size() > this.hp){
            printHand();
            int indexOfDiscardCard = -1;
            while(indexOfDiscardCard < 0 || indexOfDiscardCard > this.hand.size()-1) {
                indexOfDiscardCard = ZKlavesnice.readInt("Pick which card to dicard") - 1;
            }
            gameBoard.addToDiscardPile(getCardFromHand(indexOfDiscardCard));
            removeCardFromHand(indexOfDiscardCard);
        }
    }

    public boolean isDead(){
        return this.hp < 1;
    }

    public boolean defendWithBarrel(List<Player> players){
        for(BlueCard card : this.getTable()) {
            if (card instanceof Barrel && card.didExecute(players, this)) {
                System.out.print("\u001B[33mThe player succesfully blocked your attack with a Barrel!\u001B[0m");
                return true;
            }
        }
        return false;
    }

    public boolean defendWithMissed(){
        for (Card card : this.hand) {
            if (card instanceof Missed) {
                this.removeCardFromHand(card);
                this.addToDiscardPile(card);
                System.out.println("\u001B[33mThe player used Missed!\u001B[0m");
                return true;
            }
        }
        return false;
    }
}
