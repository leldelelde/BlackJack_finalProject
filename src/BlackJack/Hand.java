package BlackJack;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Card> cards;

    public Hand() {
        cards = new ArrayList<Card>();
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public int getValue() {
        int value = 0;
        int numAces = 0;
        for (Card card : cards) {
            if (Card.Rank.ACE == card.getRank()) {
                numAces++;
            }
            value += card.getValue();
        }
        while (value > 21 && numAces > 0) {
            value -= 10;
            numAces--;
        }
        return value;
    }

    public int getHandValue() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    public int getHandSum() {
        int sum = 0;
        for (Card card : cards) {
            sum += card.getRank().ordinal() + 1;
        }
        return sum;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean hasBlackjack() {
        return (cards.size() == 2 && getValue() == 21);
    }

    public String toString() {
        String str = "";
        for (Card card : cards) {
            str += card.toString() + "\n";
        }
        return str;
    }

        ArrayList<Card> deck = new ArrayList<Card>();

    //take card from deck to the hand
    public boolean takeCardFromDeck(Deck deck){
        this.deck[this.numCards]= myCards;
        this.numCards--;

        return (this.getHandSum() <= 21);
    }
}
