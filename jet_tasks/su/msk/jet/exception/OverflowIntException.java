/**
 * Author: Alykov Gali
 * Date:   29.02.2012
 */
package su.msk.jet.exception;
@SuppressWarnings("serial")
public class OverflowIntException extends Exception 
{
	public OverflowIntException()
	{
		System.err.println("Переполнение переменной типа int, данные потеряны");
	}

	public OverflowIntException(String arg0) 
	{
		super(arg0);
		System.err.println("Переполнение переменной типа int, данные потеряны");
	}

	public OverflowIntException(Throwable arg0) 
	{
		super(arg0);
		System.err.println("Переполнение переменной типа int, данные потеряны");
	}

	public OverflowIntException(String arg0, Throwable arg1) 
	{
		super(arg0, arg1);
		System.err.println("Переполнение переменной типа int, данные потеряны");
	}

}
