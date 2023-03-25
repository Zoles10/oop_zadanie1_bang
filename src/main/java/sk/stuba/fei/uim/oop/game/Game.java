package sk.stuba.fei.uim.oop.game;

import sk.stuba.fei.uim.oop.cards.bluecards.BlueCard;
import sk.stuba.fei.uim.oop.gameboard.GameBoard;
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
    private final GameBoard gameBoard;

    public Game() {
        this.gameInProgress = true;
        this.playerList = new ArrayList<>();
        this.gameBoard = new GameBoard();
        playGame();
    }

    private void playGame() {
        setUpPlayers();
        System.out.println("\u001B[1m\n--------------LET THE GAME BEGIN!--------------\u001B[1m");
        while (gameInProgress) {
            for (Player currentPlayer : playerList) {
                if (currentPlayer.isDead()) {
                    continue;
                }
                System.out.println("\u001B[36m--------------PLAYER " + (currentPlayer.getIndex() + 1) + " TURN: " + currentPlayer.getName() + "---------------- \u001B[0m");

                currentPlayer.checkTable(playerList);
                if (currentPlayer.isDead()) {
                    continue;
                }
                if (currentPlayer.getIsInPrison()) {
                    currentPlayer.status();
                    currentPlayer.setIsInPrison(false);
                    continue;
                }
                currentPlayer.drawCards(2);
                gameBoard.status();
                printPlayers();
                currentPlayer.status();
                gameInProgress = currentPlayer.playCards(playerList);
                if (!gameInProgress) {
                    break;
                }
                currentPlayer.discardCards();
                checkIfAPlayerDied();
            }
        }
    }

    private void setUpPlayers() {
        while (playerCount < 2 || playerCount > 4) {
            playerCount = ZKlavesnice.readInt("Enter number of player (2-4): ");
        }
        for (int i = 0; i < playerCount; i++) {
            String name = ZKlavesnice.readString("Enter name of Player: " + (i + 1));
            playerList.add(new Player(name, i, gameBoard));
            playerList.get(i).drawCards(4);
        }
    }

    private void checkIfAPlayerDied() {
        for (Player player : this.playerList) {
            if (player.isDead()) {
                removePlayerCards(player);
            }
        }
    }

    private void removePlayerCards(Player currentPlayer) {
        Iterator<Card> handIterator = currentPlayer.getHand().iterator();
        while (handIterator.hasNext()) {
            Card card = handIterator.next();
            currentPlayer.addToDiscardPile(card);
            handIterator.remove();
        }

        Iterator<BlueCard> tableIterator = currentPlayer.getTable().iterator();
        while (tableIterator.hasNext()) {
            Card card = tableIterator.next();
            currentPlayer.addToDiscardPile(card);
            tableIterator.remove();
        }
    }

    private void printPlayers() {
        System.out.println("\nPlayers: ");
        for (Player player : playerList) {
            if (player.isDead()) {
                System.out.println("\u001B[30m[Player " + (player.getIndex() + 1) + "] " + player.getName() + " is DEAD!\u001B[0m");
            } else {
                System.out.println("\u001B[34m[Player " + (player.getIndex() + 1) + "] " + player.getName() + " \u001B[32m" + player.getHp() + " HP\u001B[0m");
            }
        }
    }
}