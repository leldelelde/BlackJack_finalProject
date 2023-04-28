package BlackJack;

//Card class - to represent a card and handle card operations. Each card needs a suit and a rank.
public class Card {

    private Card[] myCards;

    //An enum is a special "class" that represents a group of constants (unchangeable variables, like final variables)
    enum Suit {
        Diamonds,
        Clubs,
        Hearts,
        Spades;
    }

    //One of four valid suits of this card
    private Suit mySuit;

    //A number of hit card
    private int myNumber;

    //Card constructor

    public Card(Suit cardSuit, int cardNumber) {
        this.mySuit = cardSuit;
        this.myNumber = cardNumber;

    }

    public int getMyNumber() {
        return myNumber;
    }

    //e.g. Diamonds 10, another solution could be String array
    public String toString(){
        String numStr;
        switch (myNumber) {
            case 1:
                numStr = "Ace";
                break;
            case 2:
                numStr = "Two";
                break;
            case 3:
                numStr = "Three";
                break;
            case 4:
                numStr = "Four";
                break;
            case 5:
                numStr = "Five";
                break;
            case 6:
                numStr = "Six";
                break;
            case 7:
                numStr = "Seven";
                break;
            case 8:
                numStr = "Eight";
                break;
            case 9:
                numStr = "Nine";
                break;
            case 10:
                numStr = "Ten";
                break;
            case 11:
                numStr = "Jack";
                break;
            case 12:
                numStr = "Queen";
                break;
            case 13:
                numStr = "King";
                break;

            default:
                numStr = Integer.toString(myNumber);

        }


        return numStr + " of " + mySuit.toString();
    }

    public enum Rank {
        ACE("Ace", 11),
        TWO("Two", 2),
        THREE("Three", 3),
        FOUR("Four", 4),
        FIVE("Five", 5),
        SIX("Six", 6),
        SEVEN("Seven", 7),
        EIGHT("Eight", 8),
        NINE("Nine", 9),
        TEN("Ten", 10),
        JACK("Jack", 10),
        QUEEN("Queen", 10),
        KING("King", 10);

        public int ordinalValue() {
            return this.ordinal() + 1;
        }

        String rankName;
        int rankValue;

        Rank(String rankName, int rankValue) {
            this.rankName = rankName;
            this.rankValue = rankValue;
        }

        public String toString() {
            return rankName;
        }
    }


    /*public int getRank() {
        int rank = myNumber;
        if (rank == 1) {
            rank = 14;//treat Ace as high
        }
        return rank;
    }*/

    //What is the diiff between above and below getRank
    public int getRank() {
        if (myNumber == 1) {
            return 1;
        } else {
            return myNumber;
        }
    }

    /*public static void main(String[] args) {
        Suit mySuit = Suit.Diamonds;
        System.out.println(mySuit.ordinal());
    }*/


    public int getValue() {
        int rank = myNumber;
        if (rank == 1) {
            // Ace has a value of 1 or 11 depending on the game rules
            return 1;
        } else if (rank >= 2 && rank <= 10) {
            // Number cards have a value equal to their rank
            return rank;
        } else {
            // Face cards (Jack, Queen, King) have a value of 10
            return 10;
        }
    }
}
