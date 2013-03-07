/*********************************************************
 * @author: Alykoff Gali
 * @date: 07.03.2013
 * =======================================================
 * Assgment number: 
 *    4 
 * Assignment:  
 *    "Утилита для копирования файлов. 
 *    На вход программе подается имя копируемого
 *    файла и имя для конечного файла. 
 *    Дополнительные условия:
 *      -- на консоль должно выводиться время, 
 *         потраченное на копирование файла;
 *      -- алгоритм копирования должен 
 *         быть наиболее оптимальным."
 * Compile:
 *    javac Calculator.java
 * Start: 
 *    java Calculator 4+12
 *
 *********************************************************/
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

public class CopyFile {
  private CopyFile() {}

  public static void main(String[] args) {
    String source, target;
    try {
      source = args[0];
      target = args[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new RuntimeException(e);
    }
    Timer timer = new Timer();
    copy(source, target);
    System.out.println(timer.getReadableInterval());
  }

  public static void copy(String source, String target) {
    try {
      FileInputStream fileInput = new FileInputStream(source);
      FileOutputStream fileOutput = new FileOutputStream(target);
      FileChannel in = fileInput.getChannel();
      FileChannel out = fileOutput.getChannel();
      try {
        in.transferTo (0L, in.size(), out);
      } finally {
        out.close();
        in.close();
        fileOutput.close();
        fileInput.close();
      }
    } catch(IOException e) {
      new RuntimeException(e);
    }
  }
}


class Timer {
  private long date;
  private final static double MILLSEC_IN_SEC = 1000D;
  Timer() {
    this.date = new Date().getTime();
  }
  
  long getInterval() {
    long currentDate = new Date().getTime();
    return currentDate - this.date;
  }
  
  String getReadableInterval() {
    return (this.getInterval() / MILLSEC_IN_SEC) +
           " sec.";
  }
  
}