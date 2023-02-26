Сделано:
	сериализация объекта на подобии библиотеки Gson

Не сделано:
	десериализация из строки в объект 

Примечания:
	Работоспособность проверялась на обьектах с полями из примитивов; строк; других объектов; массивов по типу:Integer []/Integer[][];
		списков List<примитивы/кастомные объекты>; карт Map<объекты типа java.lang..., объекты типа java.lang.../кастомные объекты>.

При запуске тестов with Coverage - тесты крашатся и не проходят, для того чтобы не было ошибок - необходимо запускать не через профиль intellij idea, а через профиль JaCoCo:
https://www.jetbrains.com/help/idea/running-test-with-coverage.html#b20b5e96
Как по другому решить проблему не знаю. Пример аналогичной проблемы: https://youtrack.jetbrains.com/issue/IDEA-274803/Velocity-field-names-check-fails-with-new-coverage ,
https://youtrack.jetbrains.com/issue/IDEA-290288  

