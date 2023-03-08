package sk.stuba.fei.uim.oop;
import sk.stuba.fei.uim.oop.cards.*;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Collections;

public class Game {

    String winner;
    boolean gameInProgress;

    int playerCount;
    List<Player> playerList;

    List<Card> cardStack;

    List<Card> discardStack;

    Game() {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        this.winner = null;
        this.gameInProgress = true;
        this.playerList = new ArrayList<>();
        this.discardStack = new ArrayList<>();
        this.cardStack = new ArrayList<>(66);

        createDeck();
        setUpPlayers(scanner);



        //Main game loop
        while (gameInProgress) {

            //all player have a turn
            for (int currentPlayerIndex = 0; currentPlayerIndex < playerList.size(); currentPlayerIndex++) {

                Player currentPlayer = playerList.get(currentPlayerIndex);
                //print the players stats


                //if player is dead, discard cards and delete from playerList
                if(playerList.get(currentPlayerIndex).getHp() < 1){
                    for(int cardIndex = 0; cardIndex < currentPlayer.getDeck().size(); cardIndex++){
                        this.discardStack.add(currentPlayer.getCardFromDeck(cardIndex));
                        currentPlayer.removeCardFromDeck(cardIndex);
                    }
                    playerList.remove(currentPlayerIndex);
                    break;
                }

                if(playerList.size()==1){
                    gameInProgress = false;
                    System.out.println("The winner is "+currentPlayer.getName());
                    break;
                }

                currentPlayer.status();

                //check cards on table, if any, "play" them

                //after they are played, play any number of cards, then move those cards to discard pile

                //TODO add ability to play multiple cards


                //draw 2 cards at the start of the round
                drawCards(2,currentPlayer);

                System.out.printf("Pick card to play (number), if you dont want o play any, press 0: ");

                int card = scanner.nextInt() - 1;
                scanner.nextLine();

                if(card != -1){
                    //if a card is chosen, play it and remove from hand
                    currentPlayer.getCardFromDeck(card).useEffect(playerList, currentPlayerIndex, cardStack, discardStack );

                    discardStack.add(currentPlayer.getCardFromDeck(card));

                    currentPlayer.removeCardFromDeck(card);


                }
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

    public void setUpPlayers(Scanner scanner) {
        //set up of player
        System.out.print("Enter number of player: ");
        playerCount = scanner.nextInt();
        scanner.nextLine();

        //create players and get their names
        for (int i = 0; i < playerCount; i++) {

            System.out.print("Enter name of Player" + (i + 1) + ": ");
            String name = scanner.nextLine();
            playerList.add(new Player(name));

            //draw 4 cards
            drawCards(4,playerList.get(i));

        }

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

    //swaps the cardStack and discard pile if the cardStack is empty
    public void refillDeck(){
        List<Card> temp = new ArrayList<>(cardStack);
        cardStack.clear();
        cardStack.addAll(discardStack);
        discardStack.clear();
        discardStack.addAll(temp);
    }
}