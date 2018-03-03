### Об этом проекте:

Это тестовое задание для компании [Exante](https://exante.eu).

Подробнее текст задания можно найти по ссылке [link](https://bitbucket.org/snippets/exante/7j8nn), или же в файле 
`test_assignment.md`.

### Как запустить

Самый простой способ для запуска `сервера` -- это использование `gradlew` файла и команды:
```
# может понадобиться выполнить так же команду chmod
# chmod +x gradlew
./gradlew :run
```

Для запуска `тестового клиента` нужно воспользоваться командой:
```
./gradlew :run -Dprop.type=client
```

Логирование можно настроить в файле `src/main/resources/logback.xml`

### Использованные технологии:
 
 * Language: Scala 2.12.0
 * Framework: Akka
 * Build Tools: Gradle 
 
 
### Автор
 * Гали Алыков