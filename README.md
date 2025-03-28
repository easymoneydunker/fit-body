# Fit Body

Это приложение предназначено для отслеживания питания пользователей, их приемов пищи и контроля за потреблением калорий. Оно помогает людям следить за своим рационом, планировать питание в зависимости от целей (похудение, поддержание веса, набор массы) и вести здоровый образ жизни.

## Основные функции приложения:

1. **Регистрация пользователей**  
   Позволяет создать профили, где хранятся данные о возрасте, весе, росте, целях и суточной норме калорий.

2. **Запись приемов пищи**  
   Пользователи могут добавлять информацию о том, что они ели, и хранить историю своих приемов пищи.

3. **Учет калорий**  
   Приложение помогает отслеживать, сколько калорий было потреблено за день, и сравнивать это с установленной нормой.

4. **Поддержка целей**  
   Позволяет пользователю установить цель (например, похудеть или набрать вес), и приложение будет помогать контролировать соблюдение суточной нормы калорий в зависимости от этой цели.

5. **Отчеты о питании**  
   Пользователи могут просматривать отчеты о своем питании за день, неделю или месяц, чтобы лучше понимать, какие блюда или привычки влияют на их питание.

## Целевая аудитория

Это приложение будет полезно для людей, которые хотят контролировать свою диету, следить за своим здоровьем или достигать определенных фитнес-целей, таких как снижение веса, поддержание здоровья или набор массы.


# Запуск приложения Fit Body
### Для запуска необходимо запустить docker-compose.yaml
**По умолчанию приложение запускается на порте 8080**

# Документация API

## Контроллер приёмов пищи (Eating)

### Создать приём пищи
- **Метод:** `POST`
- **Путь:** `/eatings?userId={userId}`
- **Тело запроса:**
  ```json
  {
    "date": "2023-10-10" // Обязательное поле (дата в формате YYYY-MM-DD)
  }

- **Параметры запроса:**
  - `userId` (обязательный, положительное число)
- **Ответ:** Созданный объект `Eating` с ID, датой и userId.

### Получить приём пищи по ID
- **Метод:** `GET`
- **Путь:** `/eatings/{id}`
- **Параметры пути:**
  - `id` (обязательный, положительное число)
- **Ответ:** Объект `Eating` со списком блюд.

### Добавить блюдо в приём пищи
- **Метод:** `POST`
- **Путь:** `/eatings/{eatingId}/add-meal?name={mealName}`
- **Параметры пути:**
  - `eatingId` (обязательный, положительное число)
- **Параметры запроса:**
  - `name` (обязательный, непустая строка)
- **Ответ:** Обновлённый объект `Eating`.

### Удалить приём пищи
- **Метод:** `DELETE`
- **Путь:** `/eatings/{id}`
- **Параметры пути:**
  - `id` (обязательный, положительное число)

---

## Контроллер блюд (Meal)

### Создать новое блюдо
- **Метод:** `POST`
- **Путь:** `/meals`
- **Тело запроса:**
  ```json
  {
    "name": "Chicken Breast", // Обязательное поле
    "calories": 165,         // Положительное число
    "proteins": 31,         // Положительное число
    "fats": 3,              // Положительное число
    "carbohydrates": 0      // Положительное число
  }
  ```
- **Проверки:**
  - `name`: Обязательное, непустое поле.
  - `calories`, `proteins`, `fats`, `carbohydrates`: Положительные числа.

### Получить блюдо по названию
- **Метод:** `GET`
- **Путь:** `/meals/{name}`
- **Параметры пути:**
  - `name` (обязательный, непустая строка)
- **Ответ:** Объект `Meal`.

### Удалить блюдо
- **Метод:** `DELETE`
- **Путь:** `/meals/{id}`
- **Параметры пути:**
  - `id` (обязательный, положительное число)

---

## Контроллер отчётов (Report)

### Получить дневной отчёт
- **Метод:** `GET`
- **Путь:** `/reports/{userId}?date={YYYY-MM-DD}`
- **Параметры пути:**
  - `userId` (обязательный, число)
- **Параметры запроса:**
  - `date` (обязательный, дата в формате ISO)
- **Ответ:** Объект `DailyReport` с суммарными калориями и БЖУ.

### Получить журнал отчётов пользователя
- **Метод:** `GET`
- **Путь:** `/reports/journal/{userId}`
- **Параметры пути:**
  - `userId` (обязательный, число)
- **Ответ:** Список объектов `DailyReport`.

---

## Контроллер пользователей (User)

### Создать пользователя
- **Метод:** `POST`
- **Путь:** `/users`
- **Тело запроса:**
  ```json
  {
    "name": "Иван Иванов",  // Обязательное поле
    "email": "ivan@example.com", // Обязательное поле
    "age": 30,             // Диапазон: 1-120
    "weight": 75,          // Диапазон: 30-200 кг
    "height": 180,         // Диапазон: 100-250 см
    "goal": "LOSE"         // Варианты: LOSE, MAINTAIN, GAIN
  }
  ```
- **Проверки:**
  - `name`, `email`: Обязательные, непустые поля.
  - `age`: 1-120 лет.
  - `weight`: 30-200 кг.
  - `height`: 100-250 см.
  - `goal`: Одно из значений: `LOSE` (похудение), `MAINTAIN` (поддержание), `GAIN` (набор массы).

### Получить пользователя по ID
- **Метод:** `GET`
- **Путь:** `/users/{id}`
- **Параметры пути:**
  - `id` (обязательный, положительное число)

### Получить пользователя по имени
- **Метод:** `GET`
- **Путь:** `/users/name/{name}`
- **Параметры пути:**
  - `name` (обязательный, непустая строка)

### Удалить пользователя
- **Метод:** `DELETE`
- **Путь:** `/users/{id}`
- **Параметры пути:**
  - `id` (обязательный, положительное число)
