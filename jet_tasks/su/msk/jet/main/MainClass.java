/**
 * Author: Alykov Gali
 * Date:   04.02.2012
 */
package su.msk.jet.main;
import java.util.Scanner;
import su.msk.jet.exception.InFormatException;
import su.msk.jet.exception.OverflowIntException;
import su.msk.jet.shell.EasyShell;
import su.msk.jet.shell.HardShell;
import su.msk.jet.shell.MiddleShell;
import su.msk.jet.test.EasyTest;
import su.msk.jet.test.HardTest;
import su.msk.jet.test.MiddleTest;

public class MainClass 
{

	public MainClass() 
	{
	
	}
	
	public static void main(String[] args) 
	{
		String tmp;
		while (true)
		{
			System.out.print("\nГлавный раздел." +
					"\nСписок команд:" +
					"\n\t'up' - введите в любое время для выхода из подраздела. Для главного меню - выход из программы." +
					"\n\tЭто слово зарезервированно, пожалуста, не используйте его в заданиях как входной параметр;" +
					"\n\t'e1', 'e2', 'e3', 'e4', 'e5' - для запуска простых заданий 1, 2, 3, 4 или 5;" +
					"\n\t'm1', 'm2', 'm3', 'm4' - для запуска нормальных заданий 1, 2, 3, 4 или 5;" +
					"\n\t'h1', 'h2', 'h3', 'h4' - для запуска сложных заданий 1, 2, 3, 4 или 5;" +
					"\n\t'et1', 'et2', 'et3', 'et4', 'et5' - для запуска теста для простых заданий 1, 2, 3, 4 или 5;" +
					"\n\t'mt1', 'mt2', 'mt3', 'mt4' - для запуска теста для нормальных заданий 1, 2, 3, 4 или 5;" +
					"\n\t'ht1', 'ht2', 'ht3', 'ht4' - для запуска теста для сложных заданий 1, 2, 3, 4 или 5;\n>");
			Scanner in = new Scanner(System.in);
			tmp = in.next();
			if (tmp.equals("up"))
			{
				break;
			}
			else if (tmp.startsWith("e"))
			{
				boolean key = true;
				while (key)
				{
					try 
					{
						if (tmp.equals("e1"))
							EasyShell.shellTask_1();
						else if (tmp.equals("e2"))
							EasyShell.shellTask_2();
						else if (tmp.equals("e3"))
							EasyShell.shellTask_3();
						else if (tmp.equals("e4"))
							EasyShell.shellTask_4();
						else if (tmp.equals("e5"))
							EasyShell.shellTask_5();
						else if (tmp.equals("et1"))
							EasyTest.easyTest_1();
						else if (tmp.equals("et2"))
							EasyTest.easyTest_2();
						else if (tmp.equals("et3"))
							EasyTest.easyTest_3();
						else if (tmp.equals("et4"))
							EasyTest.easyTest_4();
						else if (tmp.equals("et5"))
							EasyTest.easyTest_5();
						key = false;
					}
					catch(InFormatException e)
					{
						key = true;
					}
					catch (OverflowIntException e) 
					{
						key = true;
					}
				}
			}
			else if (tmp.startsWith("m"))
			{
				boolean key = true;
				while (key)
				{
					try 
					{
						if (tmp.equals("m1"))
							MiddleShell.shellTask_1();
						else if (tmp.equals("m2"))
							MiddleShell.shellTask_2();
						else if (tmp.equals("m3"))
							MiddleShell.shellTask_3();
						else if (tmp.equals("m4"))
							MiddleShell.shellTask_4();
						else if (tmp.equals("mt1"))
							MiddleTest.middleTest_1();
						else if (tmp.equals("mt2"))
							MiddleTest.middleTest_2();
						else if (tmp.equals("mt3"))
							MiddleTest.middleTest_3();
						else if (tmp.equals("mt4"))
							MiddleTest.middleTest_4();
						key = false;
					}
					catch(InFormatException e)
					{
						key = true;
					}
				}
			}
			else if (tmp.startsWith("h"))
			{
				
				boolean key = true;
				while (key)
				{
					try 
					{
						if (tmp.equals("h1"))
							HardShell.shellTask_1();
						else if (tmp.equals("h2"))
							HardShell.shellTask_2();
						else if (tmp.equals("h3"))
							HardShell.shellTask_3();
						else if (tmp.equals("h4"))
							HardShell.shellTask_4();
						else if (tmp.equals("ht1"))
							HardTest.hardTest_1();
						else if (tmp.equals("ht2"))
							HardTest.hardTest_2();
						else if (tmp.equals("ht3"))
							HardTest.hardTest_3();
						else if (tmp.equals("ht4"))
							HardTest.hardTest_4();
						key = false;
					}
					catch(InFormatException e)
					{
						key = true;
					}
				}
			}
		}
	}

}
