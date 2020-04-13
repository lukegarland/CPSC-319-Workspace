/**
 * 			User-defined exception to handle an incorrectly formatted file.
 * 			@author lukeg
 * 			@since	Apr. 8, 2020
 * 
 */

public class IllegalFileFormatException extends Exception
{

	private static final long serialVersionUID = 1L;

	public IllegalFileFormatException()
	{
	}

	public IllegalFileFormatException(String message)
	{
		super(message);
	}

	public IllegalFileFormatException(Throwable cause)
	{
		super(cause);
	}

	public IllegalFileFormatException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public IllegalFileFormatException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace)
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
