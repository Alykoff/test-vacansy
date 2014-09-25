/**
 * Author: Alykov Gali
 * Date:   04.02.2012
 */
package su.msk.jet.shell;
import java.util.Scanner;
import su.msk.jet.exception.InFormatException;
import su.msk.jet.logic.HardLogic;

public class HardShell 
{

	public HardShell() 
	{
		
	}
	
	public static void shellTask_1() throws InFormatException
	{
		String tmp;
		System.out.print("* Сложное задание #1." +
				   "\n  Программа, возвращающая n-й по величине элемент набора чисел." +
				   "\n  m - количество чисел в наборе." +
				   "\n  n - n-ый по величине элемент." +
				   "\n\tВведите число m: ");
		Scanner in = new Scanner(System.in);
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		if (!tmp.replaceAll("[\\D]", "").equals(tmp))
			throw new InFormatException();	
		int m = Integer.parseInt(tmp);
		System.out.println("\tВвeдите последовательно числа последовательности: ");
		int[] arr = new int[m];
		for (int i = 0; i < m; i++)
		{
			System.out.print("\t\t#" + (i + 1) + ": ");
			tmp = in.next();
			if (tmp.equals("up"))
				return;
			if (!tmp.replaceAll("[\\D]", "").equals(tmp))
				throw new InFormatException();				
			arr[i] = Integer.parseInt(tmp);
		}
		System.out.print("\tВведите число n: ");
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		if (!tmp.replaceAll("[\\D]", "").equals(tmp))
			throw new InFormatException();	
		int n = Integer.parseInt(tmp);
		System.out.print("\tВходная последовательность: ");
		for (int i = 0; i < m; i++)
			System.out.print(arr[i] + " ");
		System.out.println("\n\tn-ый максимальный элемент последовательности: " + HardLogic.task_1(arr, n));
	}
	
	public static void shellTask_2() throws InFormatException
	{
		String tmp;
		System.out.print("* Сложное задание #2." +
				   "\n  Программа выполняющая Run-length encoding кодирование строки." +
				   "\n  x - входная строка: ");
		Scanner in = new Scanner(System.in);
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		System.out.print("\tRun-length encoding кодирование строки: " + HardLogic.task_2(tmp));
	}
	
	public static void shellTask_3() throws InFormatException
	{
		String tmp;
		System.out.print("* Сложное задание #3." +
				   "\n  Программа выполняющая Run-length encoding декодирование строки." +
				   "\n  x - входная строка: ");
		Scanner in = new Scanner(System.in);
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		System.out.print("\tRun-length encoding декодирование строки: " + HardLogic.task_3(tmp));
	}
	
	public static void shellTask_4() throws InFormatException
	{
		String tmp;
		System.out.print("* Сложное задание #4." +
				   "\n  Количество разменов на n1..nk копеек." +
				   "\n  x - число копеек, которое нужно разменять." +
				   "\n  n - количество различных видов разменных копеек." +
				   "\n\tВведите число n: ");
		Scanner in = new Scanner(System.in);
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		if (!tmp.replaceAll("[\\D]", "").equals(tmp))
			throw new InFormatException();	
		int n = Integer.parseInt(tmp);
		System.out.println("\tВвeдите последовательно номиналы разменных копеек: ");
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
		System.out.print("\tНоминалы разменных копеек: ");
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
		System.out.print("\n\tВвeдите x: ");
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		if (!tmp.replaceAll("[\\D]", "").equals(tmp))
			throw new InFormatException();	
		int x = Integer.parseInt(tmp);
		System.out.println("\tКоличество возможных разменов: " + HardLogic.task_4(x, arr));
	}	
}
