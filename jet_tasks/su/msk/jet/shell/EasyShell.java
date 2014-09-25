/**
 * Author: Alykov Gali
 * Date:   03.02.2012
 */
package su.msk.jet.shell;
import java.util.Scanner;
import su.msk.jet.exception.InFormatException;
import su.msk.jet.exception.OverflowIntException;
import su.msk.jet.logic.EasyLogic;

public class EasyShell
{

	public EasyShell() 
	{
		
	}
	
	public static void shellTask_1() throws InFormatException
	{
		String tmp;
		System.out.print("* Простое задание #1." +
				   "\n  Программа, возвращающая максимальный элемент набора чисел." +
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
		System.out.println("\n\tМаксимальный элемент последовательности: " + EasyLogic.task_1(arr));
	}
	
	public static void shellTask_2() throws InFormatException, OverflowIntException
	{
		String tmp;
		System.out.print("* Простое задание #2." +
				   "\n  Программа, возвращающая сумму набора чисел." +
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
		System.out.println("\n\tСумма набора чисел: " + EasyLogic.task_2(arr));
	}
	
	public static void shellTask_3() throws InFormatException
	{
		String tmp;
		System.out.print("* Простое задание #3." +
				   "\n  Программа, возвращающая список простых чисел меньше заданного." +
				   "\n  x - заданное число." +
				   "\n\tВведите число x: ");
		Scanner in = new Scanner(System.in);
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		if (!tmp.replaceAll("[\\D]", "").equals(tmp))
			throw new InFormatException();	
		int x = Integer.parseInt(tmp);
		System.out.print("\tПростые числа, которые меньше " + x + ": ");
		int[] arrOut = EasyLogic.task_3(x);
		for (int i = 0; i < arrOut.length; i++)
		{
			if (i % 15 == 0)
				System.out.print("\n\t");
			System.out.print(arrOut[i] + "\t");
		}
	}
	
	public static void shellTask_4() throws InFormatException
	{
		String tmp;
		System.out.print("* Простое задание #4." +
				   "\n  Программа, возвращающая признак вхождения элемента в набор чисел," +
				   " т.е. определяет, входит ли заданное число в заданный набор." +
				   "\n  n - количество чисел в наборе." +
				   "\n  x - заданное число." +
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

		System.out.print("\n\tВведите число x: ");
		tmp = in.next();
		if (tmp.equals("up"))
			return;
		if (!tmp.replaceAll("[\\D]", "").equals(tmp))
			throw new InFormatException();	
		int x = Integer.parseInt(tmp);
		
		if (EasyLogic.task_4(arr, x))
			System.out.println("\n\tx = " + x + " входит в набор чисел.");
		else
			System.out.println("\n\tx = " + x + " не входит в набор чисел.");
	}
	
	public static void shellTask_5() throws InFormatException
	{
		String tmp;
		System.out.print("* Простое задание #5." +
				   "\n  Программа, выполняющая циклический сдвиг набора чисел." +
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
		System.out.print("\n\tПосле циклического сдвига влево: ");
		int[] out =  EasyLogic.task_5(arr);
		for (int i = 0; i < out.length; i++)
			System.out.print(out[i] + " ");
	}
}
