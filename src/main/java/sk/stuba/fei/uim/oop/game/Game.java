package sk.stuba.fei.uim.oop.game;
import sk.stuba.fei.uim.oop.gameboard.Gameboard;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private boolean gameInProgress;
    private int playerCount;
    private List<Player> playerList;
//    private List<Card> cardStack;
//    private List<Card> discardPile;

    private Gameboard gameboard;

    public Game() {
        this.gameInProgress = true;
        this.playerList = new ArrayList<>();
//        this.discardPile = new ArrayList<>();
//        this.cardStack = new ArrayList<>(66);
        this.gameboard= new Gameboard();
        playGame();
    }

    public void playGame(){
//        createDeck();
        setUpPlayers();

        System.out.println("\u001B[1m\n-------LET THE GAME BEGIN!-------\u001B[1m");
        while (gameInProgress) {
            int currentPlayerIndex = 0;
            for(Player currentPlayer : playerList){
                if(currentPlayer.isDead()){
                    currentPlayerIndex++;
                    continue;
                }
                System.out.println("\u001B[36m--------------PLAYER "+(currentPlayerIndex+1) + " TURN: "+currentPlayer.getName()+"---------------- \u001B[0m");
                currentPlayer.checkTable(currentPlayerIndex, playerList);
                if(currentPlayer.getIsInPrison()){
                    currentPlayer.status();
                    currentPlayerIndex++;
                    currentPlayer.setIsInPrison(false);
                    continue;
                }
                currentPlayer.drawCards(2);
                currentPlayer.status();
                gameInProgress = currentPlayer.playCards(playerList,currentPlayerIndex);
                if(!gameInProgress){
                    break;
                }
                currentPlayer.discardCards();
                currentPlayer.status();
                checkIfAPlayerDied();
                currentPlayerIndex++;
            }
        }
    }

//    public void createDeck() {
//        for (int i = 0; i < 30; i++) {
//            cardStack.add(new Bang());
//        }
//        for (int i = 0; i < 15; i++) {
//            cardStack.add(new Missed());
//        }
//        for (int i = 0; i < 8; i++) {
//            cardStack.add(new Beer());
//        }
//        for (int i = 0; i < 6; i++) {
//            cardStack.add(new CatBalou());
//        }
//        for (int i = 0; i < 4; i++) {
//            cardStack.add(new Stagecoach());
//        }
//        cardStack.add(new Prison());
//        cardStack.add(new Prison());
//        cardStack.add(new Prison());
//        cardStack.add(new Indians());
//        cardStack.add(new Indians());
//        cardStack.add(new Barrel());
//        cardStack.add(new Barrel());
//        cardStack.add(new Dynamite());
//        Collections.shuffle(cardStack);
//    }

    public void setUpPlayers() {
        //set up of player
        while(playerCount < 2 || playerCount > 4) {
            playerCount = ZKlavesnice.readInt("Enter number of player (2-4): ");
        }
        for (int i = 0; i < playerCount; i++) {
            String name = ZKlavesnice.readString("Enter name of Player: " + (i + 1) );
            playerList.add(new Player(name,gameboard));
            playerList.get(i).drawCards(4);
        }
    }

    public void checkIfAPlayerDied(){
        for(Player player : this.playerList){
            if(player.isDead()){
                removePlayerCards(player);
            }
        }
    }

    public void removePlayerCards(Player currentPlayer){
        for(Card card : currentPlayer.getHand() ){
            currentPlayer.addToDiscardPile(card);
            currentPlayer.removeCardFromHand(card);
        }
        for(Card card : currentPlayer.getTable() ){
            currentPlayer.addToDiscardPile(card);
            currentPlayer.removeCardFromHand(card);
        }
    }
}