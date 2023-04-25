package BlackJack;

import java.util.Scanner;

public class Hand {
    // Will represent the name of the player
    private String loginUserName;

    // ?Create initializing constructor, with string parameter for loginUserName?
    public Hand(String loginUserName) {
        // Initialize the players name
        this.loginUserName = loginUserName;
    }

    //Accessor method for instance variable, loginUserName
    public String getLoginUserName() {
        return loginUserName;
    }

    public void setLoginUserName(String loginUserName) {
        this.loginUserName = loginUserName;
    }

    //Method hitOrStay: Gives the player to have the option to hit (draw another card)
    //or stand (end their round)
    //We have to add hitorStay method in StartingGame?

    public String hitOrStay() {

        Scanner input = new Scanner(System.in);

        // Declare string variable to store the player's answer
        String answer;

        System.out.print(loginUserName + ", please choose to 'Hit' or 'Stay': ");
        answer = input.next().toLowerCase();

        if (answer.equals("hit")) {
            return "hit";
        } else if (answer.equals("stay")) {
            return "stay";
        } else {
            return null;
        }
    }
}