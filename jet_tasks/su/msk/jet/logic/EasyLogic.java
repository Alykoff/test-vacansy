/**
 * Author: Alykov Gali
 * Date:   29.02.2012
 */
package su.msk.jet.logic;
import java.util.ArrayList;
import su.msk.jet.exception.InFormatException;
import su.msk.jet.exception.OverflowIntException;

public class EasyLogic
{

	public EasyLogic() 
	{
		
	}
	/** 
	 * Программа, возвращающая максимальный элемент набора чисел, 
	 * например, 5 в наборе 1,2,3,3,4,1,2,5,3,2,1
	 * @param args int[] массив входных чисел.
	 * @return
	 * @throws InFormatException 
	 */
	public static int task_1(int... args) throws  InFormatException
	{
		//входные проверки
		if (args == null)
			throw new InFormatException();
		//возвращаемая переменная
		int max_value = Integer.MIN_VALUE;
		//проход по всему входящему массиву с проверками.
		for (int arg : args)
			if (arg > max_value)
				max_value = arg;
		return max_value;
	}
	/** 
	 * Программа, возвращающая максимальный элемент набора чисел, 
	 * например, 5 в наборе 1,2,3,3,4,1,2,5,3,2,1
	 * @param args ArrayList<Integer> массив входных чисел.
	 * @return
	 * @throws InFormatException 
	 */
	public static int task_1(ArrayList<Integer> args) throws  InFormatException
	{
		//входные проверки
		if (args == null)
			throw new InFormatException();
		//возвращаемая переменная
		int max_value = Integer.MIN_VALUE;
		//проход по всему входящему массиву с проверками.
		for (int arg : args)
			if (arg > max_value)
				max_value = arg;
		return max_value;
	}
	/**
	 * Программа, возвращающая сумму набора чисел, например, 6 как сумма 
	 * 1,2,0,-1,3,0,1.
	 * @param args
	 * @return
	 * @throws InFormatException 
	 */
	public static  int task_2(int... args) throws OverflowIntException, InFormatException
	{
		//входные проверки
		if (args == null)
			throw new InFormatException();
		//переменная в которую заносится результат.
		int sum_value = 0;
		for (int arg : args)
		{	
			//проверки на переполнение.
			if (((double)sum_value + (double)arg) < (double)Integer.MIN_VALUE)
					throw new OverflowIntException();
			else if (((double)sum_value + (double)arg) > (double)Integer.MAX_VALUE)
					throw new OverflowIntException();
			//суммирование
			sum_value += arg;	
		}	
		return sum_value;
	}
	/**
	 * Программа, возвращающая список простых чисел, меньших заданного, 
	 * например, 2,3,5,7 для числа 10.
	 * @param arg простое число (2, 3, 5, 7,...)
	 * @return
	 */
	public static  int[] task_3(int arg)
	{
		//входные проверки
		if (arg <= 1)
			return new int[]{};
		//массив, в который заносится результат.
		ArrayList<Integer> list = new ArrayList<Integer>();
		//проходимся по всем целым положительным числам,
		//которые меньше входного числа.
		for (int i = arg-1; i > 1; i--)
		{
			//ключь записи в массив
			boolean key = true;
			//проверка на то является ли текущее
			// по счетчику число простым.
			for (int j = i - 1; j > 1; j--)
				if (i % j == 0)
					key = false;
			//запись в массив простого числа
			//с сохранившимся ключем.
			if (key)
				list.add(Integer.valueOf(i));
		}
		//выходной массив
		int[] out = new int[list.size()];
		for (int k = 0; k < list.size(); k++)
			out[k] = list.get(k).intValue();
		return out;
	}
	/**
	 * Программа, возвращающая признак вхождения элемента в набор чисел, 
	 * т.е. определяет, входит ли заданное число в заданный набор. Например, 3 
	 * входит в набор 4,5,6,3,1.
	 * @param args
	 * @param mainNumber
	 * @return
	 * @throws InFormatException 
	 */
	public static  boolean task_4(int[] args, int mainNumber) throws InFormatException
	{
		//входные проверки
		if (args == null)
			throw new InFormatException();
		//перебор массива и сравнение с эталлоном.
		for (int arg : args)
			if (mainNumber == arg)
				return true;
		return false;
	}
	/**
	 * Программа, выполняющая циклический сдвиг набора чисел. Например: 
	 * 1,2,3,4 циклически сдвигается влево в 2,3,4,1.
	 * @param args
	 * @return
	 * @throws InFormatException 
	 */
	public static  int[] task_5(int[] args) throws InFormatException
	{
		//входные проверки
		if (args == null)
			throw new InFormatException();
		//переменная - хранит
		//число, которое должно быть записано.
		int assistant = 0;
		for (int i = args.length - 1; i >= 0; i--)
		{
			//проверка на первую иттерацию.
			if (i == args.length - 1)
				assistant = args[args.length - 1];
			if ((i - 1) >= 0)
			{
				//вспомогательная переменная
				int temp = args[i - 1];
				args[i - 1] = assistant;
				assistant = temp;
			}
			else if (i == 0)//проверка на последнюю иттерацию
				args[args.length - 1] = assistant;
		}
		return args;
	}
}

