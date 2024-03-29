package broker;

/**
 * This class represent an object of type Piece which will contain the necessary
 * data for each piece that will be played by either player. 
 * 	NOTE: THIS CLASS IS NOT USED BUT IS LEFT IN JUST IN CASE IT BECOME REQUIRED
 *         
 * @author GROUP 22 Code implementation by Leslie Nguyen This class will
 * 
 */
public class Piece
{
    private char blackOrWhite;

    /**
     * This constructs a piece with default values.
     */
    public Piece()
    {
    }

    /**
     * This constructs a player by creating a copy of another piece.
     * 
     * @param originalPiece is the original piece that will be copied
     */
    public Piece(Piece originalPiece)
    {
	this.blackOrWhite = originalPiece.getBlackOrWhite();
    }

    /**
     * This constructs a player with a char.
     * 
     * @param letter this char given in letter is assigned into the blackOrWhit
     *               instance variable
     */
    public Piece(char letter)
    {
	setBlackOrWhite(letter);
    }

    /**
     * This returns the char stored in the instance variable of backOrWhite.
     * 
     * @return the piece's colour
     */
    public char getBlackOrWhite()
    {
	return blackOrWhite;
    }

    /**
     * This sets the value of the char in the instance variable blackOrWhite.
     * 
     * @param blackOrWhite the char given is assigned to blackOrWhite
     */
    public void setBlackOrWhite(char blackOrWhite)
    {
	this.blackOrWhite = blackOrWhite;
    }
}
