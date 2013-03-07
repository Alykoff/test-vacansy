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
import java.nio.*;
import java.nio.channels.*;
import java.io.*;
import java.util.Date;

public class CopyFile {
  
  public static void main(String[] args) {
    String source = args[0];
    String target = args[1];
    copyFile(source, target + "1");
    copy(source, target);
  }
  public static void copy(String source, String target) {
    long date = new Date().getTime();
    try {
      // Getting file channels
      FileChannel in = new FileInputStream(source).getChannel();
      FileChannel out = new FileOutputStream(target).getChannel();
      try {
        in.transferTo (0, in.size(), out);
      } finally {
        out.close();
        in.close();
      }
    } catch(IOException e) {
      new RuntimeException(e);
    }
    long newDate = new Date().getTime();
    System.out.println((double)(newDate - date)/ 1000 + " sec");
  }

  public static void copyFile(String source, String destination) {
    long date = new Date().getTime();
    try {
      BufferedInputStream in = new BufferedInputStream(new FileInputStream(source));
      BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(destination));
      try {
        int len = 0, b = 0;
        while ((b = in.read()) != -1) {
          out.write(b);
          len += b;
        }
      } finally {
        in.close();
        out.close();
      }
    } catch (FileNotFoundException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    long newDate = new Date().getTime();
    System.out.println((double)(newDate - date)/ 1000 + " sec");
  }

}