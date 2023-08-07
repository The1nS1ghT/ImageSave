# Тестовое задание по REST API (микросервис хранения изображений)
***

## База данных
База данных представляет собой две таблицы, соединённые связью один ко многим.

![image](https://github.com/The1nS1ghT/ImageSave/assets/71176595/1b0c97f5-9e08-464e-8d60-a5a60581aeaa)




## Пример использования
***
1. Запросы которые не защищены Bearer JWT - авторизацией
##### Главная страница

`http://localhost:8080`

##### Запрос для регистрации нового пользователя

`http://localhost:8080/register?name=Maxim&email=makss.ps@gmail.com&password=123123`

Все значения в формате String

`````
``````
>Код: 200 OK 
>
| Поле              | Тип данных    |
|-------------------|---------------|
| id_user           | Long          |
| name              | String        |
| email             | String        |
| password          | String        |
| created_at        | LocalDateTime |
| role              | String        |

***
##### Запрос для авторизации пользователя

`http://localhost:8080/login?email=makss.passt@gmail.com&password=123`

Все значения в формате String

>Код: 200 OK
>


Успешный запрос возвращает JWT токен, в котором зашит email пользователя

2. Следующие запросы защищены Bearer JWT авторизацией.
##### Для их выполнения необходимо внести токен следующим образом:
   ![image](https://github.com/The1nS1ghT/ImageSave/assets/71176595/bf46b254-a7f5-4abe-a21e-3add72b4e9fe)
***
##### Запрос для получения информации о зарегистрированных пользователях

`http://localhost:8080/users`

При успешной авторизации
![image](https://github.com/The1nS1ghT/ImageSave/assets/71176595/f689fca4-7297-47a6-8991-c38bdb3a8477)
>Код: 200 OK

При попытке несанкционированного доступа к маршруту произойдёт ошибка с кодом 403
![image](https://github.com/The1nS1ghT/ImageSave/assets/71176595/4a99e10f-8bda-4e73-a993-d0a61c39ab82)
>Код: 403 Forbidden
***

##### Запрос для получения детальной информации о пользователе

`http://localhost:8080/user/{id}`

![image](https://github.com/The1nS1ghT/ImageSave/assets/71176595/46d9bc21-603b-4947-9650-68692f4441a8)
>Код: 200 OK
***

##### Запрос для сохранения изображения

`http://localhost:8080/user/{id}/new`

![image](https://github.com/The1nS1ghT/ImageSave/assets/71176595/4c64c58c-7b0e-4662-94aa-2726a6d633cf)
>Код: 200 OK
***

##### Запрос для поиска изображений по имени

`http://localhost:8080/user/{id}/search/name`

![image](https://github.com/The1nS1ghT/ImageSave/assets/71176595/75782791-497b-40f2-b898-beba6f1924ae)
>Код: 200 OK
***

##### Запрос для поиска изображений где размер файла больше, чем указал пользователь и загруженные на определённую дату

`http://localhost:8080/user/{id}/search/size`

![image](https://github.com/The1nS1ghT/ImageSave/assets/71176595/294d2b6e-da70-4c21-a976-c1ff02a9cb54)
>Код: 200 OK

##### Не хранить изображения более недели

Чтобы реализовать данную функцию необходимо создать класс с аннотацией @EnableScheduling и метод с @Scheduled.
Далее указываем переодичность вызова метода.

SQL - запрос для выполнения функции:

>UPDATE "Image" set status = 'DELETED' WHERE now()::date - date_save::date > 7

Данный запрос не удаляет данные, а всего лишь меняет статус файла с `ACTIVE` на `DELETED`.
