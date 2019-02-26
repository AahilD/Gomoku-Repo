# University of Calgary
 CPSC 219 T05
 GROUP 22
# Team
- Aahil
- Emily
- Emmanuel
- Leslie

# Gomoku

This Java based application is a reproduction of the game called Gomoku (also known as, Go, or five-in-a-row)

# Run Instructions

Once you have imported this application into Eclipse, (for now) run the application from StartGameForm.Java.
The player registration form window will appear and prompt the user's to enter a username. For now, the game is a 2-Player game with no option to play against the computer. The option to play against the computer will come in a future iteration once the 2-Player version is working properly.

# Project Architecture

The code is devided into 3 tiers:
1. broker
- This package contains all the Java Objects
2. controller
- This package contains all the functionality (or, game mechanics) which manages all the information between the gui and the broker, and delegates specific tasks according to user input and limitations implemented in the broker
3. gui
- this package contains all the front end code, using JavaFX library to diplay the graphical user interface (GUI) and the various gui-components that allow the user to interact with the application in a user friendly way.

Note: the controller does not communicate with the Broker, neither does the broker communicate with the GUI. The controller has the responsibility in mediating the information that goes back and forth.

# External (3rd party) Resources

- The image files used for the game pieces: black-piece.jpg; white-piece.jpg [1]
- The background image for the main game window graphical user interface: gui.images/background.jp [2]
- The font used for certain header text in the graphical user interface: gui.fonts/KnifeFightBallet-Regular.otf [3]

# Bibliography

[1]"Simple Gomoku", Play.simplegomoku.com, 2019. [Online]. Available: https://play.simplegomoku.com/. [Accessed: 26- Feb- 2019].

[2]Upload.wikimedia.org, 2019. [Online]. Available: https://upload.wikimedia.org/wikipedia/commons/a/a5/Tsunami_by_hokusai_19th_century.jpg. [Accessed: 26- Feb- 2019].

[3]"Knife Fight Ballet Font | dafont.com", Dafont.com, 2019. [Online]. Available: https://www.dafont.com/knife-fight-ballet.font. [Accessed: 26- Feb- 2019].
