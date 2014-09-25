/**
 * Author: Alykov Gali
 * Date:   29.02.2012
 */
package su.msk.jet.logic;
import java.util.ArrayList;
import su.msk.jet.exception.InFormatException;

public class MiddleLogic {

	public MiddleLogic() 
	{
		
	}
	/**
	 * Программа, возвращающая 2-й по величине элемент набора чисел.
	 * @param args
	 * @return
	 * @throws InFormatException 
	 */
	public static int task_1(int... args) throws InFormatException
	{
		//Проверка входных параметров.
		if (args == null || args.length <= 1)
			throw new InFormatException();
		//Массив, который будет содержать входные данные
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < args.length; i++)
			list.add(Integer.valueOf(args[i]));
		//Переменная, которая хранит максимальное значение.
		int max = EasyLogic.task_1(list);
		//Проходимся по массиву и удаляем максимальное
		//число из этого массива.
		for (int k = 0; k < list.size(); k++)
		{
			if (max == list.get(k))
			{
				list.remove(k);
				break;
			}
		}
		//После удаления максимального числа,
		//ищем следующее максимальное число.
		return EasyLogic.task_1(list);
	}
	/**
	 * Программа, выполняющая разложение числа на набор простых множителей	
	 * @param arg
	 * @return
	 * @throws InFormatException 
	 */
	public static int[] task_2(int arg) throws InFormatException
	{
		//Проверка входных данных
		if (arg <= 1)
			throw new InFormatException();
		//Массив с простыми числами,
		//все из которых меньше входного числа.
		int[] simpleNum = EasyLogic.task_3(arg);
		//Массив, в который заносится результат программы.
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < simpleNum.length; i++)
		{
			while (arg % simpleNum[i] == 0)
			{
				arg = arg/simpleNum[i];
				list.add(new Integer(simpleNum[i]));
			}
		}
		//Массив выходных данных.
		int[] out = new int[list.size()];
		for (int j = 0; j < list.size(); j++)
			out[j] = list.get(j).intValue();
		if (out.length == 0)
			return new int[]{arg};//входное число является простым.
		else
			return out;
	}
	/**
	 * Программа, выполняющая проверку строки на то, что она является 
	 * палиндромом
	 * @param arg
	 * @return
	 * @throws InFormatException 
	 */
	public static boolean task_3(String arg) throws InFormatException
	{
		if (arg == null)
			throw new InFormatException();
		//Осуществляем реверс строки.
		String arg2 = new StringBuffer(arg).reverse().toString();
		//Сравниваем реверс и строку.
		if (arg.equals(arg2))
			return true;
		else
			return false;
	}
	/**
	 * Программа, возвращающая список разменов на 3,5 или 3,5,7 копеек
	 * @param sum
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static ArrayList<ArrayList<Integer>> task_4(int sum)
	{
		int[] coins = new int[] {3, 5, 7};
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(sum/3);
		list.add(sum/5);
		list.add(sum/7);
		ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
		boolean keyEmpty = true;
		for (int l : list)
		{
			if (l > 0)
			{
				keyEmpty = false;
				break;
			}
		}
		if (keyEmpty)
			return new ArrayList<ArrayList<Integer>>();
		
		for (int i = sum/3; i >= 0; i--)
		{
			list.set(0, i);
			for (int j = sum/5; j >= 0; j--)
			{
				list.set(1, j);
				for (int k = sum/7; k >= 0; k--)
				{
					list.set(2, k);
					int exchange = 0;
					for (int n = 0; n < coins.length; n++)
						exchange += coins[n] * list.get(n);
					if (exchange == sum)
						out.add((ArrayList<Integer>)list.clone());
				}
			}
		}
		return out;
	}
}
