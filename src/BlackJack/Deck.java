package BlackJack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    //the array of cards in the deck, there the top card is in the first index
    private Cards [] myCards;
    private ArrayList<Cards> deck;
    //the number of cards currently in the deck
    private int numCards;

    public Deck(){
        deck = new ArrayList<Cards>();
    }

    //Constructor if we have one 52cards deck and with no shuffling
    //public Deck(){
        //this(1, false);
        //}
    //shuffle deck by random swapping pairs of cards
    public void Shuffle (){
        //random number generator
        Random rng = new Random();
        //temporary variable card
        Cards temp;
        int j;
        for (int i = 0; i < this.numCards; i++);

        //get a random card j to swap I value with
        j = rng.nextInt(this.numCards);

        //do swap//is int i = 0 correct?
        int i = 0;
        temp = this.myCards[i];
        this.myCards[i] = this.myCards[j];
        this.myCards[j] = temp;

    }
    public void addCard(Cards card){
        Deck.add(card);
    }

    private static void add(Cards card) {
    }
    public String toString(){
        //A string to hold everything we're going to return
        String output = "";

        //for each Card "card" in the deck

        for(Cards card: deck){
            //add the card and the escape character for a new line
            output += card;
            output += "\n";
        }
        return output;
    }





}
