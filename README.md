# University of Calgary
 CPSC 219 T05
 
 Winter 2019
 
 GROUP 22
 
# Team
- Aahil
- Emily
- Emmanuel
- Leslie
- Steven

# Gomoku

This Java based application is a reproduction of the traditional Japanese game Gomoku according to its standard rules.
# How to Play

This game will prompt the user if they wish to play against another player (PVP), or if they wish to play against the computer environment (PVE). Once the user(s) have entered their desired user name, they may proceed to begin playing by clicking on the start button. Once the game board appears, player 1 may play the first move. For now players will only be able to play agains the lowest level computer environment (level: 0). Eventually two more levels will be added.

# Rules

- Each player gets to place one Gomoku piece on the board per turn
- Turns alternate once a piece has been placed on the board.
- The first player to sucessfully place five consecutive pieces in a straight line in any direction wins.
- Directions include: Vertically, Horizontally, Diagonally (forward and back)
- However, if a player manages to connect more than 5 in a row on their turn, this will not be counted as a win.
- For instance, if you have three consecutive pieces on both sides of a single square, and you connect them, this will not count.
- Once a player has won, they wil be asked if they wish to play an other round.
- The player board panel on the right side of the board will keep track of each player's wins, loses, draws, and the bottom panel will show how many rounds have been played (not including the current round) and will show how many turns (cumulative of both players) have been played (not including current turn); Each new round will reset the turn count.

# Repository URL

1. for eclipse (egit): https://github.com/manucote/UC-G22.git

2. Linux only install: https://github.com/manucote/UC-G22/raw/dev-branch/Gomoku.tar.gz

3. windows install N/A:

4. Mac install N/A:

# Install and Run Instructions

Once you have imported this application into Eclipse, (for now) run the application from PlayerRegistrationForm.Java (this will launch the GUI based application. The default package will only contain a single class that will run the text based application.

1. download the zip file to /Downloads folder (or anywhere else)
2. extract contents and go into the extracted Directory /Gomoku
3. run the install script (run the install.sh script NEVER TRUST SCRIPTS, ALWAYS DOUBLE CHECK BEFORE RUNNNING)
- the Gomoku folder will be placed in the ~/ directory. Inside there is an uninstall.sh script you may run to remove the application.
- The installation script will move the application to ~/ and create a desktop launcher in your ~/Desktop directory
- Once installation is complete the launcher should appear on your desktop, run the program by clicking on the laucnher
- to uninstall, run the uninstall script located on your ~/Gomoku directory.
- for the text base version you will have to run the command "java -jar ~/Gomoku/TextBaseVersionGomoku.jar"

- if you do not wish to install the application, the files are set to be hidden by having a preceding "." before the name.
- you can view the hiden files by allowing your directory explorer to view hidden files
- you can run the commands "java -jar .Gomoku.jar"

- Download tar.gz from:
https://github.com/manucote/UC-G22/raw/dev-branch/Gomoku.tar.gz

# JUnit tests
To run the junits test to check the veritfy methods and fundametal logic of the game, run the class TestWinConditions.java in the jUnitTests package as a junit test. To run the junit test that tests the player test, run the class TestPlayerClass.java in the jUnitTests package.

# Project Architecture

The code is devided into 3 tiers:

1. broker
- This package contains all the Java Objects.
- Holds the fundamental logic behide the game.

2. controller
- This package contains all the functionality (or, game mechanics) which manages all the information between the gui and the broker, and delegates specific tasks according to user input and limitations implemented in the broker.

3. gui
- this package contains all the front end code, using JavaFX library to diplay the graphical user interface (GUI) and the various gui-components that allow the user to interact with the application in a user friendly way.

4. jUnitTests
- this package contains all the tests for the most cruicial parts of the application.

5.0
- this folder contains all resources that are not source code, such as fonts and images etc...

5.1 res.css
- This folder contains the CSS files that JavaFX will use to style the GUI.

5.2 res.fonts
- This folder contains the fonts (.otf, .ttf, etc...) files that the CSS file uses

5.3 res.images.
- this folder contains the image files (.jpg, .png, etc...) the CSS file will use to decorate certain aspects of the GUI.
Note: the controller does not communicate with the Broker, neither does the broker communicate with the GUI. The controller has the responsibility in mediating the information that goes back and forth.

# External (3rd party) Resources

Visual Content Resources (images used for GUI):
- The image files used for the game pieces: black-piece.jpg; white-piece.jpg [1]
- The background image for the main game window graphical user interface: gui.images/background.jp [2]
- The font used for certain header text in the graphical user interface: gui.fonts/KnifeFightBallet-Regular.otf [3]
- the background imge for the player registration [4]
- The image used for the winner and loser alert to replace the default question mark icon[5]
- The image used for the .desktop icon[6]

Instructional resources (Logic & Coding):
- stackoverflow
- youtube
- codereview
- Washington edu
- HWS edu
- freecodecamp

# Bibliography
# - Visual Content Resources (images used for GUI):

[1]"Simple Gomoku", Play.simplegomoku.com, 2019. [Online]. Available: https://play.simplegomoku.com/. [Accessed: 26- Feb- 2019].

[2]Upload.wikimedia.org, 2019. [Online]. Available: https://upload.wikimedia.org/wikipedia/commons/a/a5/Tsunami_by_hokusai_19th_century.jpg. [Accessed: 26- Feb- 2019].

[3]"Knife Fight Ballet Font | dafont.com", Dafont.com, 2019. [Online]. Available: https://www.dafont.com/knife-fight-ballet.font. [Accessed: 26- Feb- 2019].

[4]“kiMbjjMyT.png (768×768).” [Online]. Available: http://clipart-library.com/images/kiMbjjMyT.png. [Accessed: 28-Mar-2019].

[5]“1421321576980686818.png (256×256).” [Online]. Available: http://aux.iconspalace.com/uploads/1421321576980686818.png. [Accessed: 28-Mar-2019].

[6]“c00d55738e8694d6d5cfc4c837b45bd6_icon.png (256×256).” [Online]. Available: https://cdn6.aptoide.com/imgs/c/0/0/c00d55738e8694d6d5cfc4c837b45bd6_icon.png?w=256. [Accessed: 28-Mar-2019].

# - Instructional resources (Logic & Coding):

[1][Online]. Available: http://faculty.washington.edu/jtenenbg/courses/360/f01/code/gomoku.html. [Accessed: 28-Mar-2019].

[2]“algorithm - AI Strategy for gomoku (a variation of tic tac toe),” Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/6952607/ai-strategy-for-gomoku-a-variation-of-tic-tac-toe. [Accessed: 28-Mar-2019].

[3]“algorithm - How do i start with Gomoku? - Stack Overflow.” [Online]. Available: https://stackoverflow.com/questions/2438231/how-do-i-start-with-gomoku. [Accessed: 28-Mar-2019].

[4]“artificial intelligence - Gomoku array-based AI-algorithm?,” Stack Overflow. [Online]. Available: https://stackoverflow.com/questions/2753399/gomoku-array-based-ai-algorithm. [Accessed: 28-Mar-2019].

[5]“artificial intelligence - Gomoku array-based AI-algorithm? - Stack Overflow.” [Online]. Available: https://stackoverflow.com/questions/2753399/gomoku-array-based-ai-algorithm. [Accessed: 28-Mar-2019].

[6]“c - Find winner in a tic tac toe match - Stack Overflow.” [Online]. Available: https://stackoverflow.com/questions/22488100/find-winner-in-a-tic-tac-toe-match. [Accessed: 28-Mar-2019].

[7] ahmad abdolsaheb, “How to make your Tic Tac Toe game unbeatable by using the minimax algorithm,” freeCodeCamp.org, 18-Feb-2017. [Online]. Available: https://medium.freecodecamp.org/how-to-make-your-tic-tac-toe-game-unbeatable-by-using-the-minimax-algorithm-9d690bad4b37. [Accessed: 28-Mar-2019].

[8]“java - Check if a game of Tic-Tac-Toe has a winner - Code Review Stack Exchange.” [Online]. Available: https://codereview.stackexchange.com/questions/116830/check-if-a-game-of-tic-tac-toe-has-a-winner. [Accessed: 28-Mar-2019].

[9]“java - Gomoku AI: How to find out if a connect is blocked using bit boards? - Stack Overflow.” [Online]. Available: https://stackoverflow.com/questions/42853231/gomoku-ai-how-to-find-out-if-a-connect-is-blocked-using-bit-boards. [Accessed: 28-Mar-2019].

[10]“java - Loop diagonally through two dimensional array - Stack Overflow.” [Online]. Available: https://stackoverflow.com/questions/20420065/loop-diagonally-through-two-dimensional-array. [Accessed: 28-Mar-2019].

[11]Brian wu, JAVA gomoku AI. .

[12]“Java Programing: Solution to Programming Exercise.” [Online]. Available: http://math.hws.edu/eck/cs124/javanotes3/c8/ex-8-6-answer.html. [Accessed: 28-Mar-2019].

[13]Vivekanand Khyade - Algorithm Every Day, Print Matrix Diagonally (Diagonal order). .

[14]Quick Trixx, Tic Tac Toe Algorith In Artificial Intelligence | With Solved Example. .

[15]“YouTube.” [Online]. Available: https://www.youtube.com/watch?v=lebCk5SLnrU. [Accessed: 28-Mar-2019].

[16]“YouTube.” [Online]. Available: https://www.youtube.com/watch?v=AtjsHoalTEU. [Accessed: 28-Mar-2019].
