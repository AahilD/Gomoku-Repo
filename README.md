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
https://github.com/manucote/UC-G22.git

# Run Instructions

Once you have imported this application into Eclipse, (for now) run the application from PlayerRegistrationForm.Java (this will launch the GUI based application. The default package will only contain a single class that will run the text based application.

# Project Architecture

The code is devided into 3 tiers:

1. broker
- This package contains all the Java Objects.

2. controller
- This package contains all the functionality (or, game mechanics) which manages all the information between the gui and the broker, and delegates specific tasks according to user input and limitations implemented in the broker.

3. gui
- this package contains all the front end code, using JavaFX library to diplay the graphical user interface (GUI) and the various gui-components that allow the user to interact with the application in a user friendly way.

3.1 gui.css
- This folder contains the CSS files that JavaFX will use to style the GUI.

3.2 gui.fonts
- This folder contains the fonts (.otf, .ttf, etc...) files that the CSS file uses

3.3 gui.images.
- this folder contains the image files (.jpg, .png, etc...) the CSS file will use to decorate certain aspects of the GUI.
Note: the controller does not communicate with the Broker, neither does the broker communicate with the GUI. The controller has the responsibility in mediating the information that goes back and forth.

# External (3rd party) Resources

- The image files used for the game pieces: black-piece.jpg; white-piece.jpg [1]
- The background image for the main game window graphical user interface: gui.images/background.jp [2]
- The font used for certain header text in the graphical user interface: gui.fonts/KnifeFightBallet-Regular.otf [3]
- https://stackoverflow.com/questions/20420065/loop-diagonally-through-two-dimensional-array
- https://www.youtube.com/watch?v=T8ErAYobcbc
- https://stackoverflow.com/questions/22488100/find-winner-in-a-tic-tac-toe-match
- https://codereview.stackexchange.com/questions/116830/check-if-a-game-of-tic-tac-toe-has-a-winner
- https://stackoverflow.com/questions/2438231/how-do-i-start-with-gomoku
- https://stackoverflow.com/questions/6952607/ai-strategy-for-gomoku-a-variation-of-tic-tac-toe
- https://stackoverflow.com/questions/22087006/using-arrays-to-detect-a-win-in-a-gomoku-game
- https://www.youtube.com/watch?v=lebCk5SLnrU
- https://www.youtube.com/watch?v=nj8zXSIa0kw
- http://faculty.washington.edu/jtenenbg/courses/360/f01/code/gomoku.html
- http://math.hws.edu/eck/cs124/javanotes3/c8/ex-8-6-answer.html

# Bibliography

[1]"Simple Gomoku", Play.simplegomoku.com, 2019. [Online]. Available: https://play.simplegomoku.com/. [Accessed: 26- Feb- 2019].

[2]Upload.wikimedia.org, 2019. [Online]. Available: https://upload.wikimedia.org/wikipedia/commons/a/a5/Tsunami_by_hokusai_19th_century.jpg. [Accessed: 26- Feb- 2019].

[3]"Knife Fight Ballet Font | dafont.com", Dafont.com, 2019. [Online]. Available: https://www.dafont.com/knife-fight-ballet.font. [Accessed: 26- Feb- 2019].
