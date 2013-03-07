/*********************************************************
 * @author: Alykoff Gali
 * @date: 07.03.2013
 * =======================================================
 * Assgment number: 
 *    2
 * Assignment:  
 *    "Поиск количества вхождений слова в тексте. 
 *     На вход программе подается имя файла с текстом и 
 *     слово, на выходе число вхождений данного слова
 *     в тексте."
 * Compile:
 *    javac Entry.java
 * Start: 
 *    java ^
 *       -DconsoleEncoding=[YOUR_CONSOLE_ENCODING] ^
 *       Entry [PATH_TO_FILE] ^
 *       [WORD]
 * OR
 *    java \
 *       -DconsoleEncoding=[YOUR_CONSOLE_ENCODING] \
 *       Entry [PATH_TO_FILE] \
 *       [WORD]
 *
 *********************************************************/

import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.regex.Pattern;

public class Entry {
  private final static String EXP_NOT_WORD = "[^\\p{L}]";
  private final static String SUPPORT_ENCODING = "UTF-8";
  private final static Pattern PATTERN = 
    Pattern.compile(EXP_NOT_WORD);

  private Entry() {}

  public static void main(String[] args) {
    String pathToFile, standartWord;
    try {
      pathToFile = args[0];
      standartWord = args[1];
    } catch (ArrayIndexOutOfBoundsException e) {
      throw new RuntimeException(e);
    } 
    long numWord = wordCount(pathToFile, standartWord);
    System.out.println(numWord);
  }

  /**
   * Calculate counts the number of
   * occurences of word in file.
   * For languages other than English 
   * correct work only `Entry.SUPPORT_ENCODING` 
   * encoding files.
   * @param pathToFile 
   * @param standartWord 
   * @return number of `standartWord` in file 
   *         setting down in `pathToFile`.
   */
  public static long wordCount(String pathToFile, String standartWord) {
    if (PATTERN.matcher(standartWord).find()) {
      throw new RuntimeException(
        "Illigal word. Word must contains" + 
        " only letters.");
    }
    long count = 0;
    try {
      BufferedReader reader = 
        new BufferedReader(new FileReader(pathToFile));
      try {                                         
        String line;
        while ((line = reader.readLine()) != null) {
          // for support `SUPPORT_ENCODING`.
          line = new String(line.getBytes(), SUPPORT_ENCODING); 
          String[] wordsInLine = PATTERN.split(line, 0);
          for (String word : wordsInLine) {
            if (word.equals(standartWord)) {
              count++;              
            }
          }
        }
      } finally {
        reader.close();
      }
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    return count;
  }
}