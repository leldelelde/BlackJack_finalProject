package BlackJack;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BlackjackGame {
    private Deck deck;
    private List<Card> userCards;
    private List<Card> dealerCards;
    private Scanner scanner;
    private int userScore;
    private int dealerScore;

    public BlackjackGame() {
        deck = new Deck();
        userCards = new ArrayList<>();
        dealerCards = new ArrayList<>();
        scanner = new Scanner(System.in);
        userScore = 0;
        dealerScore = 0;
    }

    public void play() {
        deck.shuffle();
        dealInitialCards();
        printGameStatus(true);

        while (true) {
            System.out.print("Hit or stand? (h/s): ");
            String choice = scanner.nextLine();
            if (choice.equalsIgnoreCase("h")) {
                userCards.add(deck.dealCard());
                userScore = calculateScore(userCards);
                printGameStatus(true);
                if (userScore > 21) {
                    System.out.println("You lost! Dealer wins.");
                    return;
                }
            } else {
                dealerScore = calculateScore(dealerCards);
                while (dealerScore < 17) {
                    dealerCards.add(deck.dealCard());
                    dealerScore = calculateScore(dealerCards);
                }
                printGameStatus(false);
                if (dealerScore > 21) {
                    System.out.println("Dealer lost! You win.");
                    return;
                } else if (dealerScore > userScore) {
                    System.out.println("Dealer wins!");
                    return;
                } else if (dealerScore < userScore) {
                    System.out.println("You win!");
                    return;
                } else {
                    System.out.println("It's a tie!");
                    return;
                }
            }
        }
    }

    private void dealInitialCards() {
        userCards.add(deck.dealCard());
        userCards.add(deck.dealCard());
        dealerCards.add(deck.dealCard());
        dealerCards.add(deck.dealCard());
        userScore = calculateScore(userCards);
        dealerScore = calculateScore(dealerCards);
    }

    private void printGameStatus(boolean showDealerCard) {
        System.out.println("Your cards: " + userCards);
        System.out.println("Your score: " + userScore);
        if (showDealerCard) {
            System.out.println("Dealer's up card: " + dealerCards.get(0));
        } else {
            System.out.println("Dealer's cards: " + dealerCards);
            System.out.println("Dealer's score: " + dealerScore);
        }
    }

    private int calculateScore(List<Card> cards) {
        int score = 0;
        int numAces = 0;
        for (Card card : cards) {
            if (card.getRank() == Rank.ACE) {
                numAces++;
            }
            score += card.getPointValue();
        }
        while (numAces > 0 && score > 21) {
            score -= 10;
            numAces--;
        }
        return score;
    }

    public static void main(String[] args) {
        BlackjackGame game = new BlackjackGame();
        game.play();
    }
}

enum Suit {
    CLUBS, DIAMONDS, HEARTS, SPADES;
}

enum Rank {
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10);

    private int pointValue;

    Rank(int pointValue) {
        this.pointValue = pointValue;
    }

    public int getPointValue() {
        return pointValue;
    }
}

class Card {
    private Rank rank;
    private Suit suit;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public int getPointValue() {
        return rank.getPointValue();
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }
}
