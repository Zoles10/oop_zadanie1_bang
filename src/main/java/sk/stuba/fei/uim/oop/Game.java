package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;
import java.util.List;
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
        System.out.println("\u001B[1m\n-------LET THE GAME BEGIN!-------\u001B[1m");
        //Main game loop
        while (gameInProgress) {

            for (int currentPlayerIndex = 0; currentPlayerIndex < playerList.size(); currentPlayerIndex++) {

                Player currentPlayer = playerList.get(currentPlayerIndex);

                System.out.println("\u001B[36m--------------PLAYER TURN: "+currentPlayer.getName()+"---------------- \u001B[0m");

                currentPlayer.checkTable(currentPlayerIndex, playerList, discardPile);

                if(currentPlayer.getIsInPrison()){
                    break;
                }

                drawCards(2,currentPlayer);

                currentPlayer.status();

                this.gameInProgress = currentPlayer.playCards(playerList,currentPlayerIndex,cardStack,discardPile);

                if(!gameInProgress){
                    break;
                }

                currentPlayer.discardCards(discardPile);

                currentPlayer.status();

            }

        }
    }

    public void createDeck() {
        for (int i = 0; i < 30; i++) {
            cardStack.add(new Prison());
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
        while(playerCount < 2 || playerCount > 4) {
            playerCount = KeyboardInput.readInt("Enter number of player (2-4)",2,"Invalid input, try again.");
        }

        //create players and get their names
        for (int i = 0; i < playerCount; i++) {

            String name = KeyboardInput.readString("Enter name of Player" + (i + 1) );
            playerList.add(new Player(name));

            //draw 4 cards
            drawCards(4,playerList.get(i));

        }

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