/**
 * Author: Alykov Gali
 * Date:   29.02.2012
 */
package su.msk.jet.exception;
@SuppressWarnings("serial")
public class InFormatException extends Exception 
{

	public InFormatException() 
	{
		System.err.println("Неверный формат входных данных");
	}

	public InFormatException(String arg0) 
	{
		super(arg0);
		System.err.println("Неверный формат входных данных");
	}

	public InFormatException(Throwable arg0)
	{
		super(arg0);
		System.err.println("Неверный формат входных данных");
	}

	public InFormatException(String arg0, Throwable arg1) 
	{
		super(arg0, arg1);
		System.err.println("Неверный формат входных данных");
	}

}
