/**
 * Author: Alykov Gali
 * Date:   04.02.2012
 */
package su.msk.jet.test;
import java.util.ArrayList;
import su.msk.jet.exception.InFormatException;
import su.msk.jet.logic.MiddleLogic;

public class MiddleTest
{

	public MiddleTest()
	{
		
	}
	
	public static void middleTest_1() throws InFormatException
	{
		System.out.print("* Тест умеренно сложное задание #1." +
				   "\n  Программа, возвращающая 2-й по величине элемент набора чисел.");
		int[] arr = new int[]{1,5,-1,54,25,-49};
		System.out.print("\n\tВходная последовательность: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println("\n\t2-й по величине элемент набора чисел: " + MiddleLogic.task_1(arr));
	}
	
	public static void middleTest_2() throws InFormatException
	{
		System.out.print("* Тесет умеренно сложное задание #2." +
				   "\n  Программа, выполняющая разложение числа на набор простых множителей.");
		
		int x = 134;
		System.out.print("\n\tНабор простых множителей числа " + x + ": ");
		int[] arrOut = MiddleLogic.task_2(x);
		for (int i = 0; i < arrOut.length; i++)
		{
			if (i % 15 == 0)
				System.out.print("\n\t");
			System.out.print(arrOut[i] + "\t");
		}
	}
	
	public static void middleTest_3() throws InFormatException
	{
		System.out.print("* Тест умеренно сложное задание #3." +
				   "\n  Программа, выполняющая проверку строки на то, что она является полиндромом.\n");
		String tmp = "aaavvbvvaaa";		
		if (MiddleLogic.task_3(tmp))
			System.out.println("\tСтрока \'" + tmp + "\' полиндром.");
		else
			System.out.println("\tСтрока \'" + tmp + "\' не полиндром.");
		tmp = "14545777774";		
		if (MiddleLogic.task_3(tmp))
			System.out.println("\tСтрока \'" + tmp + "\' полиндром.");
		else
			System.out.println("\tСтрока \'" + tmp + "\' не полиндром.");
	}
	
	public static void middleTest_4()
	{
		System.out.print("* Тест умеренно сложное задание #4." +
				   "\n  Программа, возвращающая список разменов на 3,5 или 3,5,7 копеек." +
				   "\n  x - число копеек, которое нужно разменять.");
		int x = 144;
		System.out.println("\n\tВиды разменов " + x + " копеек: ");
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
	
	
	public static void main(String[] args)
	{
		

	}

}
