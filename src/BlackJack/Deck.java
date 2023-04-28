package BlackJack;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    //the array of cards in the deck, there the top card is in the first index
    private Card[] myCards;
    private ArrayList<Card> deck;

    public ArrayList<Card> getDeck() {
        return deck;
    }



    //the number of cards currently in the deck
    private static int numCards;

    public Deck(){
        deck = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        Deck.add(card);
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
        Card temp;
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

    public Card dealNextCard(){
        //deal next card
        Card top = this.myCards[0];
        //shift all the subsequent cards to the left by one index
        for(int c = 1; c < numCards; c++){
            myCards[c-1] = this.myCards[c];
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


    //no cards left in the deck
    public void hasCards (){
        for(int c = 0; c < 52; c++ ){
            this.myCards[c] = null;

        }
        this.numCards = 0;
    }

    //take card from deck to the hand
    public boolean takeCardFromDeck(Deck deck){
        this.deck[this.numCards]= myCards;
        this.numCards--;

        return (this.getHandSum() <= 21);
    }
    //get the sum of the cards in the players hand- shouldn't it be in players clas??
    public int getHandSum(){
        int handSum = 0;
        int cardNum;
        int numAces = 0;

        //calculate each hands contribution to the hand sum
        for(int c = 0; c < this.numCards; c++ ){

            //get the number of the current card
            cardNum = this.hand[c].getNumber();
            
            if(cardNum == 1){ //Ace
                numAces++;
                handSum +=11;
            } else if (cardNum > 10) { //face card
                handSum += 10;
            }else {
                handSum += cardNum;
            }
        }

        //if we have aces and our sum is > 21 set some/ all of them to value 1 instead
        while (handSum > 21 && numAces > 0){
            handSum -= 10;
            numAces--;
        }
        return handSum;
    }

    //print the cards in players hand
    //show first card whether the first card is hidden or not (it is useful when we have dealer
    public void printHand(boolean showFirstCard){
        System.out.printf("%s's cards:\n",this.name);
        for (int c = 0; c < this.numCards; c++){
            if(c == 0 && !showFirstCard){
                System.out.println(" [hidden]");
            }else {
                System.out.printf(" %s\n", this.hand[c].toString);
            }

        }

    }


}
