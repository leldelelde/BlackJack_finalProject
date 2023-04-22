package BlackJack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StartingGame {
    public static void main(String[] args) {

        System.out.println("Welcome to BlackJack!");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Do you want to register for the game? yes/no");
        String answer = scanner.nextLine().toLowerCase().trim();

        if (answer.equals("yes")) {
            System.out.println("Great! Let's get started with your registration");

            // there is the registration code
            System.out.println("Please enter your age");
            int ageOfPlayer = scanner.nextInt();

            if (ageOfPlayer < 21) {
                System.out.println("Sorry, you are too young for this game!");
            } else {
                System.out.println("Please enter your username");
                String newUserName = scanner.nextLine();

                System.out.println("Please enter your password (it should be at least 7 characters long and consist at least one capital letter and at least one number)");
                String newPassword = scanner.nextLine();
                //there might be validation part for password - checking the length and use of capital letters and numbers

                    if (Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9]).{7,}$", newPassword)){
                        System.out.println("Your password does not meet the requirements!");
                    }
                    // I suppose there should be an option to fill the password untill it meets the requirements
                System.out.println("Please enter your fullName");
                String newFullName = scanner.nextLine();

                System.out.println("Please enter your email");
                String newEmail = scanner.nextLine();
                //there should be validation part, if email consists @ and "."

                addDataOfRegistrationToDB(conn, newUserName, newPassword, newFullName, ageOfPlayer, newEmail);
                //at the moment not sure how to solve the error of "conn"
                // I realise that it is not recognised, but not sure, where to resolve it
                System.out.println(newUserName + ", let's start the game!");
            }
        } else {
            System.out.println("Ok, maybe next time!");
        }

        // Start of the game - probably should be as a separate method, to make main class shorter

    }

    public static void addDataOfRegistrationToDB (Connection conn, String username, String password, String fullName, int age, String email) throws SQLException {
        String sql = "INSERT INTO users (username, password, fullName, age, email) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, fullName);
        preparedStatement.setInt(4, age);
        preparedStatement.setString(5, email);

        int rowInserted = preparedStatement.executeUpdate();

        if (rowInserted > 0){
            System.out.println("Registration completed successfully");
        } else {
            System.out.println("Something went wrong!");
        }
    }
}
