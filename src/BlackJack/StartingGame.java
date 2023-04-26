package BlackJack;

import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;

public class StartingGame {
    public static void main(String[] args) throws SQLException {

        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/black_jack", "root", "java23");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database!");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to BlackJack!");

        boolean quit = false;
        int choice = 0;
        printOptions();
        while (!quit) {

            System.out.println("Enter your choice");

            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 0:
                    //Register for the game
                    registrationForGame();
                    break;
                case 1:
                    //login for the game
                    loginRegisteredUser();
                    break;
                case 2:
                    //see the results

                    break;
                case 3:
                    //quit
                    quit = true;
                    break;
            }
        }
    }

    private static void printOptions(){
        System.out.println("\nPress");
        System.out.println("\t 0 - To register");
        System.out.println("\t 1 - To login");
        System.out.println("\t 2 - To see results");
        System.out.println("\t 3 - To quit");
    }
        public static void registrationForGame(){

            Scanner scanner = new Scanner(System.in);

            System.out.println("Let's get started with your registration");

            System.out.println("Please enter your age");
            int ageOfPlayer = scanner.nextInt();

            if (ageOfPlayer < 21) {
                System.out.println("Sorry, you are too young for this game!");
            } else {
                System.out.println("Please enter your username");
                scanner.nextLine();
                String newUserName = scanner.nextLine();

                System.out.println("Please enter your password (it should be at least 7 characters long and consist at least one capital letter and at least one number)");
                String newPassword = scanner.nextLine();

                while (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{7,}$", newPassword)) {
                    System.out.println("Your password does not meet the requirements! Please enter your password!");
                    newPassword = scanner.nextLine();
                }

                System.out.println("Please enter your fullName");
                String newFullName = scanner.nextLine();

                System.out.println("Please enter your email");
                String newEmail = scanner.nextLine();
                while (!isEmailValid(newEmail)) {
                    System.out.println("Invalid email address, please enter again:");
                    newEmail = scanner.nextLine();
                }
                addDataOfRegistrationToDB(conn, newUserName, newPassword, newFullName, ageOfPlayer, newEmail);
            }
            loginRegisteredUser();
        }

        public static void loginRegisteredUser(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("To start the game, please login!");
        System.out.println("Please enter your username");
        String loginUsername = scanner.nextLine();

        System.out.println("Please enter your password");
        String loginPassword = scanner.nextLine();

        if(!isLoginValid(conn, loginUsername, loginPassword)) {
            System.out.println("Invalid username or password");
        } else{
            System.out.println(loginUsername + ", let's start the game!");
        }
        //there should be an actual game method
            // when the game is played, there should be option to play again, see results or quit
    }

    public static void addDataOfRegistrationToDB(Connection conn, String username, String password, String fullName, int age, String email) throws SQLException {
        String sql = "INSERT INTO usersBJ (username, password, fullName, age, email) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        preparedStatement.setString(3, fullName);
        preparedStatement.setInt(4, age);
        preparedStatement.setString(5, email);

        int rowInserted = preparedStatement.executeUpdate();

        if (rowInserted > 0) {
            System.out.println("Registration completed successfully");
        } else {
            System.out.println("Something went wrong!");
        }
    }

    public static boolean isEmailValid (String email){
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }
    
    public static boolean isLoginValid (Connection conn, String username, String password) throws SQLException {
        String sql = "SELECT * FROM usersBJ WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet.next();
    }
}