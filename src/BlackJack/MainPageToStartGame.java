package BlackJack;
import java.sql.*;
import java.util.Scanner;
import java.util.regex.Pattern;
public class MainPageToStartGame {
    static Connection conn = null;

    public static void main(String[] args) throws SQLException {

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

        while (!quit) {
            printOptions();
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
                    //quit
                    quit = true;
                    break;
            }
        }
    }

    private static void printOptions() {
        System.out.println("\nPress");
        System.out.println("\t 0 - To register");
        System.out.println("\t 1 - To login");
        System.out.println("\t 2 - To quit");
        System.out.println("Enter your choice:");
    }

    public static void registrationForGame() throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Let's get started with your registration!");
        System.out.println();
        System.out.println("Please enter your age:");
        int ageOfPlayer = scanner.nextInt();
        if (ageOfPlayer < 21) {
            System.out.println("Sorry, you are too young for this game!");
        } else {
            System.out.println("Please enter your username:");
            scanner.nextLine();
            String newUserName = scanner.nextLine();
            System.out.println("Please enter your password: (it should be at least 7 characters long and consist at least one capital letter and at least one number)");
            String newPassword = scanner.nextLine();
            while (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{7,}$", newPassword)) {
                System.out.println("Your password does not meet the requirements! Please enter your password!");
                newPassword = scanner.nextLine();
            }
            System.out.println("Please enter your full name:");
            String newFullName = scanner.nextLine();
            System.out.println("Please enter your email:");
            String newEmail = scanner.nextLine();
            while (!isEmailValid(newEmail)) {
                System.out.println("Invalid email address! Please enter again:");
                newEmail = scanner.nextLine();
            }
            addDataOfRegistrationToDB(conn, newUserName, newPassword, newFullName, ageOfPlayer, newEmail);
            loginRegisteredUser();
        }
    }

    public static void loginRegisteredUser() throws SQLException{

        Scanner scanner = new Scanner(System.in);
        System.out.println("To start the game, please login!");
        System.out.println("Please enter your username:");
        String loginUsername = scanner.nextLine();
        System.out.println("Please enter your password:");
        String loginPassword = scanner.nextLine();
        if (!isLoginValid(conn, loginUsername, loginPassword)) {
            System.out.println("Invalid username or password!");
        } else {
            System.out.println(loginUsername + ", let's start the game!");
            BlackjackGame newGame = new BlackjackGame(conn, loginUsername);
            newGame.play();
            playGameAgain(loginUsername);
        }
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

    public static boolean isEmailValid(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email.matches(regex);
    }

    public static boolean isLoginValid(Connection conn, String username, String password) throws SQLException {
        String sql = "SELECT * FROM usersBJ WHERE username = ? AND password = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, username);
        preparedStatement.setString(2, password);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    public static void playGameAgain(String username) {
        Scanner scanner = new Scanner(System.in);
        boolean playAgain = true;
        while (playAgain) {
            System.out.println("Press 1 - to play again, 2 - to see results, or 3 - to quit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    BlackjackGame newGame = new BlackjackGame(conn, username);
                    newGame.play();
                    break;
                case 2:
                    try {
                        viewGameResults(conn, username);
                    } catch (SQLException e) {
                        System.out.println("Couldn't view game results");
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    playAgain = false;
                    break;
                default:
                    System.out.println("Invalid choice");
                    break;
            }
        }
    }



    public static void viewGameResults(Connection conn, String username) throws SQLException {
        String userIdSql = "SELECT * FROM usersBJ WHERE username = ?";
        PreparedStatement userIdStatement = conn.prepareStatement(userIdSql);
        userIdStatement.setString(1, username);
        ResultSet userIdResult = userIdStatement.executeQuery();
        int user_id = -1;
        if (userIdResult.next()) {
            user_id = userIdResult.getInt("userID");
        }

        String sql = "SELECT * FROM gameResults WHERE user_id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, user_id);
        ResultSet resultSet = preparedStatement.executeQuery();

        int wins = 0;
        int losses = 0;

        while (resultSet.next()) {
            wins = wins + resultSet.getInt("wins");
            losses = losses + resultSet.getInt("losses");
        }

        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
    }
}