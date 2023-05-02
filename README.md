This is a Java-based console application for playing the classic game of **BLACKJACK**

**INSTALLATION:**
To install and run the game, follow these steps:
1. Use the command git clone https://github.com/leldelelde/BlackJack_finalProject.git to copy the repository to your local machine
2. To play the game, open the project, navigate to MainPageToStartGame, and select "Run MainPageToStartGame.main()" from the context menu

**USAGE:**

When the game is launched, the user will be given three options: register for the game, login to the game, or quit.

1. Register for the game:
if you choose to register for the game, you will be asked to enter your age, username, password, full name, and email address,
(if you are younger than 21 years old, you will not be allowed to play)

2. Login to the game:
If you choose to log in to the game, you will be asked to enter your username and password,
(if your login information is invalid, you will not be allowed to play)

3. Quit:
Is used as a signal to stop the program from running further
 
 
After each game, player will be given the option to play again, see results, or quit, if player choose to see results, the program will display  win/loss record

The program stores player and game information in a MySQL database:

**UserBJ table:**

The usersBJ table stores information about the players who play the game -  this includes their unique userID which is an integer and serves as the primary key,
their username, password, full name, age, and email address

**GamesBJ table:**

The gamesBJ table stores information about each game that a player has played,
including their username and  result of the game (win or loss) scores

**IMPLEMENATION DETAILS:**

The project consists of classes that are responsible for different aspects of the game:

**Deck:**
The Deck class represents a deck of playing cards. It is responsible for shuffling the deck and dealing cards to players, it is played with a standard deck of 52 cards. The goal is to have a hand that is closer in value to 21 than the dealer's hand, without going over 21.

**BlackJackGame:**
Responsible for managing the game's state, including the deck of cards, the player's hand, and the dealer's hand, the game is played by prompting the player to make a bet and then dealing two cards to the player and two cards to the dealer, the player can then choose to hit (take another card) or stand (keep their current hand). The dealer must hit until their hand is worth at least 17 points.Once both the player and dealer have finished their turns, the winner is determined based on who has the higher hand value without going over 21





















