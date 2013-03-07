/*********************************************************
 * @author: Alykoff Gali
 * @date: 07.03.2013
 * =======================================================
 * Assgment number: 
 *    1
 * Assignment:  
 *    "Расчет факториала. На вход программе подается число, 
 *     на выходе факториал этого числа."
 * Compile:
 *    javac Factorial.java
 * Start: 
 *    java Factorial [NUMBER]
 *
 *********************************************************/

public class Factorial {

  private Factorial() {}

  public static void main(String[] args) {
    long num;
    try {
      num = Long.parseLong(args[0]);
    } catch (NumberFormatException e) {
      throw new RuntimeException(e);
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new RuntimeException(e);
    }
    long factorial = calc(num);
    System.out.println(factorial);
  }
  /**
   * Calculate factorian number `n`.
   * @param n number.
   * @return factorial of `n`.
   */
  public static long calc(long num) {
    if (num < 0) {
     throw new RuntimeException("Invalid data.");
    }
    long result = 1;
    for (int i = 1; i <= num; i++) {
      result *= i;
      if (result <= 0) {
        throw new RuntimeException("Overflow, data is lost.");   
      }
    }
    return result;
  }
}