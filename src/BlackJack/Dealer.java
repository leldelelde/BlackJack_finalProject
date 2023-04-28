package BlackJack;

public class Dealer extends Person {
    //Create a dealer
    private static final String dealersName = "Dealer";

    public Dealer() {
        super.setName(dealersName);
    }

    //Revealing dealers hands
    //for testing

    public void revealFirstCard() {
        Hand hand = super.getHand();
        if (hand == null || hand.size() == 0) {
            System.out.println("The dealer's hand is empty.");
            return;
        }
        StringBuilder sb = new StringBuilder("The dealer's hand looks like this:\n")
                .append(hand.getCard(0))
                .append("\nThe second card is face down.");
        System.out.println(sb.toString());
    }

    public void revealSecondCard() {
        Hand hand = super.getHand();
        if (hand.getHandSum() < 2) {
            System.out.println("The dealer's hand has less than 2 cards.");
            return;
        }
        System.out.println("The dealer's second card is " + Deck.dealNextCard());
    }
    //Dealer's turn
            if(!dealerDone){
        if (dealer.getHandSum() < 17){
            System.out.println("The Dealer hits\n");
            dealerDone = !dealer.addCard(Deck.dealNextCard());
            dealer.printHand(false);
        } else {
            System.out.println("The Dealer stays\n");
            dealerDone = true;
        }
    }
}






}
