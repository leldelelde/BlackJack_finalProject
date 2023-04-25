package BlackJack;

public class Main {
    public static void main(String[] args) {
        Deck myDeck = new Deck();
        //Deck myDeck = new Deck (1, true)- the deck is shuffled
        //number is optional, in this case we can see two suits
        myDeck.printDeck(24);
    }

}
