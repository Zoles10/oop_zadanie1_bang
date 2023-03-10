package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.cards.*;
import sk.stuba.fei.uim.oop.utility.KeyboardInput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Game {

    boolean gameInProgress;
    int playerCount;
    List<Player> playerList;
    List<Card> cardStack;
    List<Card> discardStack;

    Game() {
        Random random = new Random();
        this.gameInProgress = true;
        this.playerList = new ArrayList<>();
        this.discardStack = new ArrayList<>();
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

                currentPlayer.checkTable(currentPlayerIndex, playerList, discardStack);

                currentPlayer.status();

                if(currentPlayer.getIsInPrison()){
                    System.out.println("This player skips a turn because he is imprisoned");
                    break;
                }

                //draw 2 cards at the start of the round
                drawCards(2,currentPlayer);

                //while player has cards, he can play cards, or stop by entering 0
                playCards(currentPlayer,currentPlayerIndex);

                //player must have max card equal tu Hp at the end of turn
                currentPlayer.status();

                discardCards(currentPlayer);


            }

        }
    }

    public void createDeck() {
        for (int i = 0; i < 30; i++) {
            cardStack.add(new Bang());
        }

        for (int i = 0; i < 15; i++) {
            cardStack.add(new Vedľa());
        }

        for (int i = 0; i < 8; i++) {
            cardStack.add(new Pivo());
        }

        for (int i = 0; i < 6; i++) {
            cardStack.add(new CatBalou());
        }

        for (int i = 0; i < 4; i++) {
            cardStack.add(new Dostavník());
        }

        cardStack.add(new Indiáni());
        cardStack.add(new Indiáni());

        cardStack.add(new Barrel());
        cardStack.add(new Barrel());

        cardStack.add(new Dynamit());

        cardStack.add(new Väzenie());
        cardStack.add(new Väzenie());
        cardStack.add(new Väzenie());

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
        for(int cardIndex = 0; cardIndex < currentPlayer.getDeck().size(); cardIndex++){
            this.discardStack.add(currentPlayer.getCardFromDeck(cardIndex));
            currentPlayer.removeCardFromDeck(cardIndex);
        }
        playerList.remove(currentPlayerIndex);
    }

    public void  drawCards(int numberOfCards, Player player){
        int randomStackIndex;
        for (int i = 0; i < numberOfCards; i++) {

            if(cardStack.size()<1){
                refillDeck();
            }
            player.addToDeck(cardStack.get(cardStack.size()-1));
            cardStack.remove(cardStack.size()-1);
        }
    }

    public void playCards(Player currentPlayer, int currentPlayerIndex){
        while(currentPlayer.getDeck().size() > 0){

            currentPlayer.printHand();
            int cardPlayedIndex = KeyboardInput.readInt("Pick which card to play") - 1;

            if (cardPlayedIndex != -1) {
                //if a card is chosen, play it and remove from hand
                currentPlayer.getCardFromDeck(cardPlayedIndex).useEffect(playerList, currentPlayerIndex, cardPlayedIndex, cardStack, discardStack);
            }
            else{
                break;
            }
        }
    }

    public void discardCards(Player currentPlayer){

        int indexOfDiscardCard = 0;
        while(currentPlayer.getDeck().size() > currentPlayer.getHp()){

            currentPlayer.printHand();
            indexOfDiscardCard = KeyboardInput.readInt("Pick which card to dicard") - 1;
            currentPlayer.removeCardFromDeck(indexOfDiscardCard);
            discardStack.add(currentPlayer.getCardFromDeck(indexOfDiscardCard));

        }
    }

    //swaps the cardStack and discard pile if the cardStack is empty
    public void refillDeck(){
        List<Card> temp = new ArrayList<>(cardStack);
        cardStack.clear();
        cardStack.addAll(discardStack);
        discardStack.clear();
        discardStack.addAll(temp);
        Collections.shuffle(cardStack);
    }


}