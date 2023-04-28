package BlackJack;

import java.util.Scanner;

    public class Player extends Person {
        Scanner input = new Scanner(System.in);

        //Create a new Player
        public Player() {
            super.setName("Player");

        }

        //Allow Player to make decision
        //public void DecisionOfPlayer(Deck deck, Deck discard)
        public void DecisionOfPlayer(Deck deck) {

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
                this.hit(deck);
                if(this.getHand().calculatedValue()>21){
                    return;
                }
                else{
                    this.DecisionOfPlayer(deck);
                }

            } else {
                System.out.println("You stand.");
            }


            }


        }


    }

