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
                        while (!isEmailValid(newEmail)){
                        System.out.println("Invalid email address, please enter again:");
                        newEmail = scanner.nextLine();
                    }
                addDataOfRegistrationToDB(conn, newUserName, newPassword, newFullName, ageOfPlayer, newEmail);
            }
            }else{
            System.out.println("Ok, maybe next time!");
        }

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
    }

    // Start of the game



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