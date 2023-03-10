package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Game {

    boolean gameInProgress;
    int playerCount;
    List<Player> playerList;
    List<Card> cardStack;
    List<Card> discardPile;

    Game() {
        this.gameInProgress = true;
        this.playerList = new ArrayList<>();
        this.discardPile = new ArrayList<>();
        this.cardStack = new ArrayList<>(66);
        playGame();
    }

    public void playGame(){

        createDeck();
        setUpPlayers();

        //Main game loop
        while (gameInProgress) {

            //all player have a turn
            for (int currentPlayerIndex = 0; currentPlayerIndex < playerList.size(); currentPlayerIndex++) {

                Player currentPlayer = playerList.get(currentPlayerIndex);

                //if player is dead, discard cards and delete from playerList
                if(currentPlayer.isDead()){
                    removePlayer(currentPlayer, currentPlayerIndex);
                    break;
                }

                //if currentPlayer is the last one alive, he wins
                if(playerList.size()==1){
                    gameInProgress = false;
                    System.out.println("The winner is "+currentPlayer.getName());
                    break;
                }

                System.out.println("\u001B[36m--------------PLAYER TURN: "+currentPlayer.getName()+"---------------- \u001B[0m");

                currentPlayer.checkTable(currentPlayerIndex, playerList, discardPile);



                if(currentPlayer.getIsInPrison()){
                    System.out.println("This player skips a turn because he is imprisoned");
                    break;
                }

                //draw 2 cards at the start of the round
                drawCards(2,currentPlayer);

                currentPlayer.status();

                currentPlayer.playCards(playerList,currentPlayerIndex,cardStack,discardPile);

                currentPlayer.discardCards(discardPile);

                currentPlayer.status();

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
        for (int i = 0; i < 4; i++) { cardStack.add(new Stagecoach());
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

    public void setUpPlayers() {
        //set up of player
        playerCount = KeyboardInput.readInt("Enter number of player");

        //create players and get their names
        for (int i = 0; i < playerCount; i++) {

            String name = KeyboardInput.readString("Enter name of Player" + (i + 1) );
            playerList.add(new Player(name));

            //draw 4 cards
            drawCards(4,playerList.get(i));

        }

    }

    public void removePlayer(Player currentPlayer, int currentPlayerIndex){
        for(int cardIndex = 0; cardIndex < currentPlayer.getHand().size(); cardIndex++){
            this.discardPile.add(currentPlayer.getCardFromHand(cardIndex));
            currentPlayer.removeCardFromHand(cardIndex);
        }
        playerList.remove(currentPlayerIndex);
    }

    public void  drawCards(int numberOfCards, Player player){
        for (int i = 0; i < numberOfCards; i++) {

            if(cardStack.size()<1){
                System.out.print("The cards are being shuffled from the discard pile");
                refillDeck();
            }
            player.addToHand(cardStack.get(cardStack.size()-1));
            cardStack.remove(cardStack.size()-1);
        }
    }

    //swaps the cardStack and discard pile if the cardStack is empty
    public void refillDeck(){
        List<Card> temp = new ArrayList<>(cardStack);
        cardStack.clear();
        cardStack.addAll(discardPile);
        discardPile.clear();
        discardPile.addAll(temp);
        Collections.shuffle(cardStack);
    }


}