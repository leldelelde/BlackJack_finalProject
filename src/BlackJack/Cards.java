package BlackJack;

public class Cards {
    //An enum is a special "class" that represents a group of constants (unchangeable variables, like final variables)
    enum suit{
        Diamonds,
        Clubs,
        Hearts,
        Spades;
    }
    //One of four valid suits of this card
    private suit mySuit;

    //A number of hit card
    private int myNumber;

    //Card constructor

    public Cards(suit suit, int Number){
        this.mySuit= suit;
        this.myNumber= Number;

    }

    public int getMyNumber() {
        return myNumber;
    }

    //e.g. Diamonds 10, another solution could be String array
    public String toString(){
        String numStr = " ";

        switch (this.myNumber){
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
                numStr = "Invalid number";
                break;
        }

        return numStr + " of " + mySuit.toString();
    }
}
