package BlackJack;

import java.util.ArrayList;

public class Hand {
    private ArrayList<Cards> cards;

    public Hand() {
        cards = new ArrayList<Cards>();
    }

    public void addCard(Cards card) {
        cards.add(card);
    }

    public int getValue() {
        int value = 0;
        int numAces = 0;
        for (Cards card : cards) {
            if (card.getRank() == Cards.Rank.ACE) {
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
        for (Cards card : cards) {
            sum += card.getValue();
        }
        return sum;
    }

    public int getHandSum() {
        int sum = 0;
        for (Cards card : cards) {
            sum += card.getRank().ordinal() + 1;
        }
        return sum;
    }

    public ArrayList<Cards> getCards() {
        return cards;
    }

    public boolean hasBlackjack() {
        return (cards.size() == 2 && getValue() == 21);
    }

    public String toString() {
        String str = "";
        for (Cards card : cards) {
            str += card.toString() + "\n";
        }
        return str;
    }
}
