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

    public Cards dealNextCard(){
        //deal next card
        Cards top = this.myCards[0];
        //shift all the subsequent cards to the left by one index
        for(int c = 1; c < this.numCards; c++){
            this.myCards[c-1] = this.myCards[c];
        }
        this.myCards[this.numCards -1] = null;

        //decrement the number of cards in our deck
        this.numCards --;

        return top;
    }

    //the number of cards from the top of the deck to print
    public void printDeck (int numToPrint){
        for(int c = 0; c < numToPrint; c++){
            System.out.printf("%3d/%d %s\n", c+1, this.numCards, this.myCards[c].toString() );
    }
        System.out.printf("\t\t[%d other]\n", this.numCards - numToPrint);

    }


}
