package BlackJack;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Deck myDeck = new Deck();
        //Deck myDeck = new Deck (1, true); the deck is shuffled
        //number is optional, in this case we can see two suits
        myDeck.printDeck(24);

        //initialize the player objects
        String newPlayer = scanner.nextLine();//need to connect with registered -logged user also
        Player dealer = new Player("Dealer");

        newPlayer.addCard(Deck.dealNextCard());
        dealer.addCard(Deck.dealNextCard());
        newPlayer.addCard(Deck.dealNextCard());
        dealer.addCard(Deck.dealNextCard());

        //print initial hands
        System.out.println("Cards are dealt\n");
        newPlayer.printHand(true);
        dealer.printHand(false);
        System.out.println("\n");

        //flags for each player is finished hitting
        boolean newPlayerDone = false;
        boolean dealerDone = false;
        String ans;

        while ( !newPlayerDone || !dealerDone){
            //player's turn
            if (!newPlayerDone){
                System.out.println("Hit or Stay? H/S");
                ans = scanner.next();
                System.out.println();

                //if player hits
                if(ans.compareToIgnoreCase("H") == 0){//make ignorelowercase
                    //add next card in the deck and store whether player is busted
                    newPlayerDone = !newPlayer.addCard(Deck.dealNextCard());
                    dealer.printHand(true);

                }else {
                    newPlayerDone = true;
                }

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
            System.out.println();

        }

        //print final hands
        newPlayer.printHand(true);
        dealer.printHand(true);

        int mySum = newPlayer.getHandSum();
        int dealerSum = dealer.getHandSum();

        if(mySum > dealerSum && mySum <= 21 || dealerSum > 21){
            System.out.println("You win!");

        }else {
            System.out.println("Dealer wins!");
        }
    }


}
