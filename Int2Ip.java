/*
 * Напишите код, преобразующий 32-битное 
 * целочисленное представление ip-адреса в строковое:
 * Подробнее: http://company.yandex.ru/job/vacancies/dev_junior_java.xml
 *
 */
import java.lang.*;

public class Int2Ip {
	private final static int NUM_BIT_IN_BYTE = 8;
	private final static int NUM_BYTE_IN_INT = 4;
	private final static int BASE = 2;
	private final static char SEPARATOR = ':';
	
	public static void main(String[] args) {
		try {
			int value = Integer.parseInt(args[0]);
			System.out.println(getIp(value));
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public static String getIp(int ip) {
		StringBuilder acc = new StringBuilder();
		int maxByte = NUM_BYTE_IN_INT - 1;
		for (int i = maxByte; i >= 0; i--) {
			if (i != maxByte) {
				acc.append(SEPARATOR);
			}
			acc.append(getNthByte(ip, i));
		}
		return acc.toString();
	}
	private static int byteMask(int numByte) {
		int begin = (numByte * NUM_BIT_IN_BYTE) - 1;
		int end = begin + NUM_BIT_IN_BYTE;
		int result = 0;
		for (int i = begin; i <= end; i++) {
			result += Math.pow(BASE, i);
		}
		return result;
	}
	private static int getNthByte(int value, int numOfByte) {
		int filteredValue = value & byteMask(numOfByte);
		int traceSlip = NUM_BIT_IN_BYTE * numOfByte;  
		return filteredValue >> traceSlip;
	}
}