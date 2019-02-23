package gui;

public class CSSNotFoundException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String filename;
	
	public CSSNotFoundException(String toFilename)
	{
		filename = toFilename;
	}
	
	public String toString()
	{
		return "Warning: Failed to load the custom css: \"" + filename + 
				"\".\n Falling back on default JAVAFX css instead.";
	}

}
