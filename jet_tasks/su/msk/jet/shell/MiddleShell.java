/**
 * Author: Alykov Gali
 * Date:   04.02.2012
 */
package su.msk.jet.shell;
import java.util.ArrayList;
import java.util.Scanner;
import su.msk.jet.exception.InFormatException;
import su.msk.jet.logic.MiddleLogic;

public class MiddleShell 
{

	public MiddleShell() 
	{
		
	}
	
	public static void shellTask_1() throws InFormatException
	{
		String tmp;
		System.out.print("* Умеренно сложное задание #1." +
				   "\n  Программа, возвращающая 2-й по величине элемент набора чисел." +
				   "\n  n - количество чисел в наборе." +
				   "\n\tВведите число n: ");
		Scanner in = new Scanner(System.in);
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		if (!tmp.replaceAll("[\\D]", "").equals(tmp))
			throw new InFormatException();	
		int n = Integer.parseInt(tmp);
		
		System.out.println("\tВвeдите последовательно числа последовательности: ");
		int[] arr = new int[n];
		for (int i = 0; i < n; i++)
		{
			System.out.print("\t\t#" + (i + 1) + ": ");
			tmp = in.next();
			if (tmp.equals("up"))
				return;
			if (!tmp.replaceAll("[\\D]", "").equals(tmp))
				throw new InFormatException();				
			arr[i] = Integer.parseInt(tmp);
		}
		System.out.print("\tВходная последовательность: ");
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
		System.out.println("\n\t2-й по величине элемент набора чисел: " + MiddleLogic.task_1(arr));
	}
	
	public static void shellTask_2() throws InFormatException
	{
		String tmp;
		System.out.print("* Умеренно сложное задание #2." +
				   "\n  Программа, выполняющая разложение числа на набор простых множителей." +
				   "\n  x - заданное число." +
				   "\n\tВведите число x: ");
		Scanner in = new Scanner(System.in);
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		if (!tmp.replaceAll("[\\D]", "").equals(tmp))
			throw new InFormatException();	
		int x = Integer.parseInt(tmp);
		System.out.print("\tНабор простых множителей числа " + x + ": ");
		int[] arrOut = MiddleLogic.task_2(x);
		for (int i = 0; i < arrOut.length; i++)
		{
			if (i % 15 == 0)
				System.out.print("\n\t");
			System.out.print(arrOut[i] + "\t");
		}
	}
	public static void shellTask_3() throws InFormatException
	{
		String tmp;
		System.out.print("* Умеренно сложное задание #3." +
				   "\n  Программа, выполняющая проверку строки на то, что она является полиндромом." +
				   "\n  x - заданная строка.");
		Scanner in = new Scanner(System.in);
		System.out.print("\n\tВведите строку x: ");
		tmp = in.next();
		if (tmp.equals("up"))
			return;		
		if (MiddleLogic.task_3(tmp))
			System.out.println("\tСтрока \'" + tmp + "\' полиндром.");
		else
			System.out.println("\tСтрока \'" + tmp + "\' не полиндром.");
	}
	
	public static void shellTask_4() throws InFormatException
	{
		String tmp;
		System.out.print("* Умеренно сложное задание #4." +
				   "\n  Программа, возвращающая список разменов на 3,5 или 3,5,7 копеек." +
				   "\n  x - число копеек, которое нужно разменять." +
				   "\n\tВведите число x: ");
		Scanner in = new Scanner(System.in);
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		if (!tmp.replaceAll("[\\D]", "").equals(tmp))
			throw new InFormatException();	
		int x = Integer.parseInt(tmp);
		System.out.println("\tВиды разменов " + x + " копеек: ");
		ArrayList<ArrayList<Integer>> arrOut = MiddleLogic.task_4(x);
		System.out.print("\t2 коп.; ");
		System.out.print("\t3 коп.; ");
		System.out.print("\t7 коп.; \n");
		for (ArrayList<Integer> arr : arrOut)
		{
			System.out.print("\t");
			System.out.print(arr.get(0));
			System.out.print("\t\t");
			System.out.print(arr.get(1));
			System.out.print("\t\t");
			System.out.print(arr.get(2));
			System.out.println();
		}
	}

}
