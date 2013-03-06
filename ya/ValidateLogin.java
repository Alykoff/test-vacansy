/*
 * В системе авторизации есть следующее ограничение на формат логина: «Логин должен начинаться с латинской буквы, может состоять из латинских букв, цифр, точки и минуса и должен заканчиваться латинской буквой или цифрой. 
 * Минимальная длина логина — 1 символ. Максимальная — 20 символов».
 * Напишите код, проверяющий, соответствует ли входная строка этому правилу:
 * Подробнее: http://company.yandex.ru/job/vacancies/dev_junior_java.xml
 *
 */

import java.util.regex.*;

public class ValidateLogin {
	private final static String LOGIN_PATTERN = 
		"(^[a-zA-Z][a-zA-Z0-9\\.-]{0,18}[a-zA-Z0-9]$)|(^[a-zA-Z]{1,20}$)";
	private final static Pattern pattern = 
		Pattern.compile(LOGIN_PATTERN);

	public static void main(String[] args) {
		try {
			boolean result = isValid(args[0]);
			System.out.println(String.valueOf(result));
		} catch (ArrayIndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}
	public static boolean isValid(String name) {
		return pattern.matcher(name).matches();
	}

}