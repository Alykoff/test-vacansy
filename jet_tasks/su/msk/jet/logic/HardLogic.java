/**
 * Author: Alykov Gali
 * Date:   29.02.2012
 */
package su.msk.jet.logic;
import java.util.ArrayList;
import su.msk.jet.exception.InFormatException;

public class HardLogic
{
	/**
	 * переменная использующаяся как вспомогательная в 
	 * четвертом задании.
	 */
	private static ArrayList<ArrayList<Integer>> arr_out_task_4;
	public HardLogic() 
	{
		
	}
	static
	{
		arr_out_task_4 = new ArrayList<ArrayList<Integer>>();
	}
	/**
	 * Программа, возвращающая n-й по величине элемент набора чисел.
	 * @param args
	 * @param n
	 * @return
	 * @throws InFormatException
	 */
	public static int task_1(int[] args, int n) throws InFormatException
	{
		//входные проверки
		if (args == null || n <= 0 || args.length < n || args.length == 0)
			throw new InFormatException();
		//массив с динамическим размером, в котором содержатся входной массив
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < args.length; i++)
			list.add(Integer.valueOf(args[i]));
		//максимальное число из последовательности
		int max = 0;
		//цикс по последовательности
		for (int j = 0; j < n; j++)
		{
			max = EasyLogic.task_1(list);
			//если не последняя иттерация, то удаляем
			//элемент из динамического массива
			if (j != n - 1)
			{
				for (int k = 0; k < list.size(); k++)
				{
					if (max == list.get(k))
					{
						list.remove(k);
						break;
					}
				}
			}
		}
		return max;
	}
	/**
	 * Программа выполняющая Run-length encoding кодирование строки 
	 * (aaabccdd -> 3a1b2c2d)
	 * @param arg
	 * @return
	 * @throws InFormatException
	 */
	public static String task_2(String arg) throws InFormatException
	{
		//входные проверки на валидность данных
		if (arg == null || arg.length() == 0)
			throw new InFormatException();
		//переменная, в которую заносится очередная строка
		//подлежащая кодированию.
		String value = "";
		//массив-последовательность символов, которые нужно кодировать.
		char[] mChar = arg.toCharArray();
		//выходная строка
		StringBuffer out = new StringBuffer("");
		//счетчик количества вхождения символа.
		int counter = 0;
		//проходимся по всей строке осуществляя кодирование,
		//и занося результаты в строку out.
		for (int i = 0; i < mChar.length; i++)
		{
			if (i == 0)
				value = String.valueOf(mChar[i]);
			if (!value.equals(String.valueOf(mChar[i])))
			{
				out.append(String.valueOf(counter) + value);
				counter = 1;
				value = String.valueOf(mChar[i]);
			}
			else
				counter++;
			if (i == (mChar.length - 1))
				out.append(String.valueOf(counter) + value);
		}
		return out.toString();
	}
	/**
	 * Программа выполняющая Run-length encoding декодирование строки 
	 * (3a1b2c2d -> aaabccdd)
	 * @param arg
	 * @return
	 * @throws InFormatException 
	 */
	public static String task_3(String arg) throws InFormatException
	{
		StringBuffer out = new StringBuffer("");
		//массив счетчика символов
		ArrayList<String> counters = new ArrayList<String>();
		for (String str : arg.split("[\\D]{1,14}"))
			if (!str.equals(""))
				counters.add(str);
		//массив значений
		ArrayList<String> values = new ArrayList<String>();
		for (String str : arg.split("[\\d]{1}"))
			if (!str.equals(""))
				values.add(str);
		//проверка на валидность массивов
		if (counters.size() != values.size())
			throw new InFormatException();
		//перебор для каждого символа, который
		//добавляется в выходную строку нужное количество раз.
		for (int i = 0; i < counters.size(); i++)
		{
			if (counters.get(i).length() > 14 || values.get(i).length() != 1)
				throw new InFormatException();
			long count = Long.parseLong(counters.get(i));
			for (long j = 0L; j < count; j++)
				out.append(values.get(i));
		}
		return out.toString();
	}
	/**
	 * Количество разменов на n1..nk копеек
	 * @param sum - сумма, которая подлежит размену.
	 * @param coins - достоинства разменных монет.
	 * @return количество разменов
	 * @throws InFormatException
	 */
	public static int task_4(int sum, int[] coins) throws InFormatException
	{
		//входные проверки.
		if (coins == null || coins.length == 0 || sum <= 0)
			throw new InFormatException();
		/*
		 * составление массива, который соответствует по размеру входному массиву coins,
		 * и на i месте массива list стоит максимальное возможное количество монет (достоинством coins[i]),
		 * которое может участвовать в размене.
		 */
		ArrayList<Integer> list = new ArrayList<Integer>();
		boolean key = true;//ключ на не положительность массива list
		for (int i = 0; i < coins.length; i++)
		{
			if (coins[i] <= 0)
				throw new InFormatException();
			int temp = sum/coins[i];
			list.add(temp);
			if (temp != 0)
				key = false;
		}
		if (key)
			throw new InFormatException();
		//очищение массива результатов.
		HardLogic.arr_out_task_4.clear();
		//ищем все варианты
		HardLogic.task_4_secondary(HardLogic.arr_out_task_4, list, coins, sum, 0);
		return HardLogic.arr_out_task_4.size();
	}
	
	/**
	 * 
	 * @param out массив, который будет содержать выходные значения.
	 * @param args входной массив, который содержит вектор-состояние (каждая координата
	 * такого вектора - это количество монет данного достоинства, которое участвовует в размене).
	 * @param coins - достоинства разменных монет.
	 * @param sum - сумма, которая подлежит размену.
	 * @param count - указатель на координату вектора-состояния, которая будет изменяться.
	 * @throws InFormatException неверные входные данные.
	 */
	@SuppressWarnings("unchecked")
	private static void task_4_secondary(ArrayList<ArrayList<Integer>> out, ArrayList<Integer> args, int[] coins, int sum,  int count) throws InFormatException
	{
		/*
		 * проверка на выход указателя координат вектора
		 * состояния за границы массива.
		 */
		if (count < 0 || count > (args.size() - 1))
			return;
		/*
		 * перебор всех состояний для одной из координат
		 * вектора состояний.
		 */
		int maxValue = args.get(count);
		for (int i = maxValue; i >= 0; i--)
		{
			//изменение вектора состояний
			args.set(count, i);
			/*
			 * проверка на удовлетворение условиям задачи
			 * вектора состояния, если он удовлетворяет условиям - 
			 * добавление в массив результатов.
			 */
			if (coins.length != args.size())
				throw new InFormatException();
			int exchange = 0;
			for (int j = 0; j < coins.length; j++)
				exchange += coins[j] * args.get(j);
			if (exchange == sum)
			{	
				boolean keyReplay = true;//ключ проверка на вхождение в массив результатов.
				for (ArrayList<Integer> temp : HardLogic.arr_out_task_4)
					if (temp.equals(args))
						keyReplay = false;
				if (keyReplay)
					HardLogic.arr_out_task_4.add((ArrayList<Integer>)args.clone());
			}
			//рекурсивное построение, варьируем следующую координату вектора состояний.
			HardLogic.task_4_secondary(HardLogic.arr_out_task_4, (ArrayList<Integer>)args.clone(), coins, sum, count + 1);
		}
	}
}
