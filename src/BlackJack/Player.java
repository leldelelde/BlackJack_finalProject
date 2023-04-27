package BlackJack;

import java.util.Scanner;

    public class Player extends Person {
        Scanner input = new Scanner(System.in);

        //Create a new Player
        public Player() {
            super.setName("Player");

        }

        //Allow Player to make decision
        public void DecisionOfPlayer(Deck deck, Deck discard) {

            int  decision = 0;
            boolean getNum = true;

            while(getNum){

                try{
                    System.out.println("Would you like to: 1) Hit or 2) Stand");
                    decision = input.nextInt();
                    getNum = false;
                }
                catch(Exception e){
                    System.out.println("Invalid");
                    input.next();
                }

            }

            if (decision == 1) {
                this.hit(deck, discard);
                if(this.getHand().calculatedValue()>20){
                    return;
                }
                else{
                    this.DecisionOfPlayer(deck, discard);
                }

            } else {
                System.out.println("You stand.");
            }

            //add a card to players hand?


            }


        }


    }

