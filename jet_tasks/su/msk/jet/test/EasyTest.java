/**
 * Author: Alykov Gali
 * Date:   04.02.2012
 */
package su.msk.jet.test;
import su.msk.jet.exception.InFormatException;
import su.msk.jet.exception.OverflowIntException;
import su.msk.jet.logic.EasyLogic;

public class EasyTest
{

	public EasyTest() 
	{
		
	}

	public static void easyTest_1() throws InFormatException
	{
		System.out.print("* Тест простое задание #1." +
				   "\n  Программа, возвращающая максимальный элемент набора чисел.");
		int[] arr = new int[]{1,5,-1,54,25,-49};
		System.out.print("\n\tВходная последовательность: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println("\n\tМаксимальный элемент последовательности: " + EasyLogic.task_1(arr));
	}
	
	public static void easyTest_2() throws OverflowIntException, InFormatException
	{
		System.out.print("* Тест простое задание #2." +
				   "\n  Программа, возвращающая сумму набора чисел.");
		int[] arr = new int[]{1,5,-1,54,25,-49};
		System.out.print("\n\tВходная последовательность: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.println("\n\tМаксимальный элемент последовательности: " + EasyLogic.task_2(arr));
	}
	
	public static void easyTest_3()
	{
		System.out.print("* Тест простое задание #3." +
				   "\n  Программа, возвращающая список простых чисел меньше заданного.");
		int x = 135;
		System.out.print("\n\tПростые числа, которые меньше " + x + ": ");
		int[] arrOut = EasyLogic.task_3(x);
		for (int i = 0; i < arrOut.length; i++)
		{
			if (i % 15 == 0)
				System.out.print("\n\t");
			System.out.print(arrOut[i] + "\t");
		}
	}
	
	public static void easyTest_4() throws InFormatException
	{
		System.out.print("* Тест простое задание #4." +
				   "\n  Программа, возвращающая признак вхождения элемента в набор чисел," +
				   " т.е. определяет, входит ли заданное число в заданный набор.");
		int[] arr = new int[]{1,15,55,21,-2,17};
		System.out.print("\n\tВходная последовательность: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		int x = 15;
		if (EasyLogic.task_4(arr, x))
			System.out.println("\n\tx = " + x + " входит в набор чисел.");
		else
			System.out.println("\n\tx = " + x + " не входит в набор чисел.");
	}
	
	public static void easyTest_5() throws InFormatException
	{
		System.out.print("* Тест простое задание #5." +
				   "\n  Программа, выполняющая циклический сдвиг набора чисел.");
		
		System.out.println("\n\tВвeдите последовательно числа последовательности: ");
		int[] arr = new int[]{1,2,3,4,5,6,7,8,9,10};
		System.out.print("\tВходная последовательность: ");
		for (int i = 0; i < arr.length; i++)
			System.out.print(arr[i] + " ");
		System.out.print("\n\tПосле циклического сдвига влево: ");
		int[] out =  EasyLogic.task_5(arr);
		for (int i = 0; i < out.length; i++)
			System.out.print(out[i] + " ");
	}
	
	public static void main(String[] args) 
	{
		
	}

}
