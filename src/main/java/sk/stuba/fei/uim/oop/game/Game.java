package sk.stuba.fei.uim.oop.game;
import sk.stuba.fei.uim.oop.cards.bluecards.BlueCard;
import sk.stuba.fei.uim.oop.gameboard.Gameboard;
import sk.stuba.fei.uim.oop.player.Player;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.ZKlavesnice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private boolean gameInProgress;
    private int playerCount;
    private final List<Player> playerList;
    private final Gameboard gameboard;

    public Game() {
        this.gameInProgress = true;
        this.playerList = new ArrayList<>();
        this.gameboard= new Gameboard();
        playGame();
    }

    public void playGame(){
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
                if(currentPlayer.isDead()){
                    currentPlayerIndex++;
                    continue;
                }
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

    public void setUpPlayers() {
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
        Iterator<Card> handIterator = currentPlayer.getHand().iterator();
        while(handIterator.hasNext()) {
            Card card = handIterator.next();
            currentPlayer.addToDiscardPile(card);
            handIterator.remove();
        }

        Iterator<BlueCard> tableIterator = currentPlayer.getTable().iterator();
        while(tableIterator.hasNext()) {
            Card card = tableIterator.next();
            currentPlayer.addToDiscardPile(card);
            tableIterator.remove();
        }
    }
}