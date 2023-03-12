package sk.stuba.fei.uim.oop.game;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class Game {
    private boolean gameInProgress;
    private int playerCount;
    private List<Player> playerList;
    private List<Card> cardStack;
    private List<Card> discardPile;

    public Game() {
        this.gameInProgress = true;
        this.playerList = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.cardStack = new ArrayList<>(66);
        playGame();
    }

    public void playGame(){
        createDeck();
        setUpPlayers();
        System.out.println("\u001B[1m\n-------LET THE GAME BEGIN!-------\u001B[1m");
        while (gameInProgress) {
            int currentPlayerIndex = 0;
            for(Player currentPlayer : playerList){
                currentPlayer.checkTable(currentPlayerIndex, playerList, discardPile);
                if(currentPlayer.getIsInPrison() || currentPlayer.isDead()){
                    continue;
                }
                System.out.println("\u001B[36m--------------PLAYER "+(currentPlayerIndex+1) + " TURN: "+currentPlayer.getName()+"---------------- \u001B[0m");
                drawCards(2,currentPlayer);
                currentPlayer.status();
                gameInProgress = currentPlayer.playCards(playerList,currentPlayerIndex,cardStack,discardPile);
                if(!gameInProgress){
                    break;
                }
                currentPlayer.discardCards(discardPile);
                currentPlayer.status();
                checkIfAPlayerDied(playerList,discardPile);
                currentPlayerIndex++;
            }
        }
    }

    public void createDeck() {
        for (int i = 0; i < 30; i++) {
            cardStack.add(new Bang());
        }
        for (int i = 0; i < 15; i++) {
            cardStack.add(new Missed());
        }
        for (int i = 0; i < 8; i++) {
            cardStack.add(new Beer());
        }
        for (int i = 0; i < 6; i++) {
            cardStack.add(new CatBalou());
        }
        for (int i = 0; i < 4; i++) {
            cardStack.add(new Stagecoach());
        }
        cardStack.add(new Prison());
        cardStack.add(new Prison());
        cardStack.add(new Prison());
        cardStack.add(new Indians());
        cardStack.add(new Indians());
        cardStack.add(new Barrel());
        cardStack.add(new Barrel());
        cardStack.add(new Dynamite());
        Collections.shuffle(cardStack);
    }

    public void refillDeck(){
        List<Card> temp = new ArrayList<>(cardStack);
        cardStack.clear();
        cardStack.addAll(discardPile);
        discardPile.clear();
        discardPile.addAll(temp);
        Collections.shuffle(cardStack);
    }

    public void setUpPlayers() {
        //set up of player
        while(playerCount < 2 || playerCount > 4) {
            playerCount = KeyboardInput.readInt("Enter number of player (2-4)",2,"Invalid input, try again.");
        }
        for (int i = 0; i < playerCount; i++) {
            String name = KeyboardInput.readString("Enter name of Player " + (i + 1) );
            playerList.add(new Player(name));
            drawCards(4,playerList.get(i));
        }
    }

    public void checkIfAPlayerDied(List<Player> players,List<Card> discardPile){
        for(Player player : players){
            if(player.isDead()){
                removePlayerCards(player,discardPile);
            }
        }
    }

    public void removePlayerCards(Player currentPlayer, List<Card> discardPile){
        for(int cardIndex = 0; cardIndex < currentPlayer.getHand().size(); cardIndex++){
            discardPile.add(currentPlayer.getCardFromHand(cardIndex));
            currentPlayer.removeCardFromHand(cardIndex);
        }
        for(int cardIndex = 0; cardIndex < currentPlayer.getTable().size(); cardIndex++){
            discardPile.add(currentPlayer.getCardFromTable(cardIndex));
            currentPlayer.removeCardFromTable(cardIndex);
        }
    }

    public void  drawCards(int numberOfCards, Player player){
        for (int i = 0; i < numberOfCards; i++) {
            if(cardStack.size()<1){
                System.out.print("The cards are being shuffled from the discard pile!");
                refillDeck();
            }
            player.addToHand(cardStack.get(cardStack.size()-1));
            cardStack.remove(cardStack.size()-1);
        }
    }
}