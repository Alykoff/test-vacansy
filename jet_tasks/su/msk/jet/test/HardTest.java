/**
 * Author: Alykov Gali
 * Date:   04.02.2012
 */
package su.msk.jet.test;
import su.msk.jet.exception.InFormatException;
import su.msk.jet.logic.HardLogic;

public class HardTest 
{

	public HardTest()
	{
		
	}

	
	public static void hardTest_1() throws InFormatException
	{
		System.out.print("* Тест сложное задание #1." +
				   "\n  Программа, возвращающая n-й по величине элемент набора чисел.");
		int[] arr = new int[]{1,5,-1,54,25,-49};
		int n = 4;
		System.out.print("\n\tЧисло n: " + n);
		System.out.print("\n\tВходная последовательность: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println("\n\tn-ый максимальный элемент последовательности: " + HardLogic.task_1(arr, n));
	}
	
	public static void hardTest_2() throws InFormatException
	{
		String tmp = "aaasssefsssaefetgbcc";
		System.out.print("* Тест сложное задание #2." +
				   "\n  Программа выполняющая Run-length encoding кодирование строки." +
				   "\n  x - входная строка: " + tmp);
		System.out.print("\n\tRun-length encoding кодирование строки: " + HardLogic.task_2(tmp));
	}
	
	public static void hardTest_3() throws InFormatException
	{
		String tmp = "1e4d3a8s";
		System.out.print("* Сложное задание #3." +
				   "\n  Программа выполняющая Run-length encoding декодирование строки." +
				   "\n  x - входная строка: " + tmp);
		System.out.print("\n\tRun-length encoding декодирование строки: " + HardLogic.task_3(tmp));
	}
	
	public static void hardTest_4() throws InFormatException
	{
		System.out.print("* Сложное задание #4." +
				   "\n  Количество разменов на n1..nk копеек." +
				   "\n  x - число копеек, которое нужно разменять.");
		int[] arr = new int[]{2,7,11};
		System.out.print("\n\tНоминалы разменных копеек: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		int x = 755;
		System.out.print("\n\tx: " + x);
		System.out.println("\n\tКоличество возможных разменов: " + HardLogic.task_4(x, arr));
	}	
	public static void main(String[] args) 
	{
		
		
	}
}