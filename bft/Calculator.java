/*********************************************************
 * @author: Alykoff Gali
 * @date: 07.03.2013
 * =======================================================
 * Assgment number: 
 *    3
 * Assignment:  
 *    "Реализация простейшего калькулятора. 
 *    Доступны четыре арифметических действия: 
 *      сложение (+), 
 *      умножение (*), 
 *      деление (/) и 
 *      вычитание (-). 
 *    На вход программе подается строка вида 
 *    [число][ариф. знак][число], на выходе результат."
 * Compile:
 *    javac Calculator.java
 * Start: 
 *    java Calculator 4+12    //16.0
 *    java Calculator -4+-12  //-16.0
 *
 *********************************************************/
import java.lang.ArithmeticException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Calculator {
  private final static Operator[] OPERATORS = 
    new Operator[] {
      new Plus(), 
      new Minus(), 
      new Multi(), 
      new Div()};
  private final static String EXP_NUM =
    "(-?0|-?0\\.\\d+|-?[1-9]\\d*\\.\\d+|-?[1-9]\\d*)";
  private final static String EXP_OPERATOR = 
    "([-\\*\\+\\/])";
  private final static String EXP_VALID_EXPRESSION =
    "^" + EXP_NUM + EXP_OPERATOR + EXP_NUM + "?";
  private final static Pattern PATTERN_EXPRESSION =
    Pattern.compile(EXP_VALID_EXPRESSION);

  private Calculator() {}

  public static void main(String[] args) {
    String expression;
    try {
      expression = args[0];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new RuntimeException(e);
    }
    double result = calc(expression);
    System.out.println(result);
  }
  /**
   * Simple calculator.
   * @param expression follows the contract:
   *        [NUM][+,-,*,/][NUM].
   * @return calculated result.
   */
  public static double calc(String expression) {
    Matcher m = PATTERN_EXPRESSION.matcher(expression);
    String rawFirstNum = null;
    String rawSecondNum = null;
    String operation = null;
    if (m.find()) {
      rawFirstNum = m.group(1);
      operation = m.group(2);
      rawSecondNum = m.group(3);
    }
    if (rawFirstNum == null || 
        operation == null || 
        rawSecondNum == null) {
      throw new RuntimeException("Invalid data.");
    }

    double firstNum = Double.parseDouble(rawFirstNum);
    double secondNum = Double.parseDouble(rawSecondNum);

    for (Operator operator : OPERATORS) {
      if (operator.SIMBOL.equals(operation)) {
        return operator.apply(firstNum, secondNum);
      }
    }
    throw new RuntimeException("Invalid data.");
  }
}

abstract class Operator {
  final String SIMBOL;
  Operator(String simbol) {
    this.SIMBOL = SIMBOL;
  }
  abstract double apply(double firstNum, double secondNum);
}

class Plus extends Operator {
  Plus() {
    super("+");
  }
  @Override
  double apply(double firstNum, double secondNum) {
    return firstNum + secondNum;
  }
}

class Minus extends Operator {
  Minus() {
    super("-");
  }
  @Override
  double apply(double firstNum, double secondNum) {
    return firstNum - secondNum;
  }
}

class Div extends Operator {
  Div() {
    super("/");
  }
  @Override
  double apply(double firstNum, double secondNum) {
    if (secondNum == 0D) {
      throw new ArithmeticException("Div on zero.");
    }
    return firstNum / secondNum;
  }
}

class Multi extends Operator {
  Multi() {
    super("*");
  }
  @Override
  double apply(double firstNum, double secondNum) {
    return firstNum * secondNum;
  }
}