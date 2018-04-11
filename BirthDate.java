import java.util.Calendar;

public class BirthDate implements Comparable<BirthDate> {
    private int day;
    private int month;
    private int year;

    // Конструктор для создания пустого объекта класса BirthDate
    public BirthDate() {
        this.day = 0;
        this.month = 0;
        this.year = 0;
    }

    // Конструктор для создания нового объекта класса BirthDate
    // с заданными днём, месяцем и годом, переданными в качестве аргументов
    public BirthDate(int day, int month, int year) {
        if (!isValidDate(day, month, year))
            throw new IllegalArgumentException("Несуществующая дата!");
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /* Конструктор для создания нового объекта класса BirthDate
     * с заданой заранее датой, переданной в качестве аргумента в виде String, а НЕ в виде
     * 3-ёх целых чисел, как это было в предыдущем конструкторе.
     * Т.е. этот конструктор в гипотетическом main() будет использоваться следующим образом:
     * BirthDate bd = new BirthDate("20.09.2017");
     * То есть этот конструктор должен принять новую дату в виде String в формате
     * "dd.mm.yyyy", затем этот конструктор должен самостоятельно вычленить из этого String-а
     * день, месяц и год для того, чтобы инициализовать соответствующие поля у нового объекта класса
     * BirthDate */
    public BirthDate(String birthDateAsString) {
        String[] args = birthDateAsString.split("\\.");

        // TODO: Проверка формата ввода
        if (args.length != 3) {
            throw new IllegalArgumentException("Неверный формат! Нужно: dd.mm.yyyy");
        }

        // TODO: Сделать стринговские строки интежеровскими
        int newDay = Integer.parseInt(args[0]);
        int newMonth = Integer.parseInt(args[1]);
        int newYear = Integer.parseInt(args[2]);

        // TODO: Проверить правильность данной даты
        if (!isValidDate(newDay, newMonth, newYear))
            throw new IllegalArgumentException("Несуществующая дата!");

        // TODO: Инициализировать
        // ВОТ В ЧЁМ СУТЬ КОНСТРУКТОРА
        this.day = newDay;
        this.month = newMonth;
        this.year = newYear;
    }

    // При выводе объекта класса BirthDate на консоль, он должен
    // выводиться в формате dd.mm.yyyy
    @Override
    public String toString() {
        return this.day + "." + this.month + "." + this.year;
    }

    // Добавить getter-ы для всех полей

    // Добавить setter-ы для всех полей с проверкой правильности даты

    // Пример setter-а для дня привожу ниже:
    public void setDay(int day) {
        if (!isValidDate(day, this.month, this.year))
            throw new IllegalArgumentException("Несуществующий день!");
        this.day = day;
    }

    public int getDay() {
        return this.day;
    }

    public int getMonth() {
        return this.month;
    }

    public void setMonth(int month) {
        if (!isValidDate(this.day, month, this.year))
            throw new IllegalArgumentException("Несуществующий месяц!");
        this.month = month;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        if (!isValidDate(this.day, this.month, year))
            throw new IllegalArgumentException("Несуществующий год!");
        this.year = year;
    }

    /* Метод для проверки правильности даты, переданной в качестве аргумента в виде дня, месяца и года.
     * Метод должен вернуть true, если переданная дата верная и false, если нет.
     * Критерии верной даты:
     * y 1900 - 2100
     * m 1 -12
     * d 1 - 31 (1, 3, 5, 7, 8, 10, 12)
     * d 1 - 30 (4, 6, 9, 11)
     * d 1 - 28 (2, НЕвис. год)
     * d 1 - 29 (2, вис. год)
     * год является високосным в двух случаях:
     * либо он кратен 4, но при этом не кратен 100,
     * либо кратен 400.*/
    private boolean isValidDate(int d, int m, int y) {
        boolean result = true;

        if (y < 1900 || y > 2100)
            result = false;
        else if (m == 1 || m == 3 || m == 5 || m == 7 || m == 8 || m == 10 || m == 12) {
            if (d < 1 || d > 31)
                result = false;
        } else if (m == 4 || m == 6 || m == 9 || m == 11) {
            if (d < 1 || d > 30)
                result = false;
        } else if (m == 2) {
            if (y % 4 == 0 && y % 100 != 0 || y % 400 == 0) {
                if (d < 1 || d > 29)
                    result = false;
            } else {
                if (d < 1 || d > 28)
                    result = false;
            }
        } else
            result = false;

        return result;
    }

    // Метод для сравнения 2-ух дат между собой.
    @Override
    public int compareTo(BirthDate birthDate) {
        int result = Integer.compare(this.year, birthDate.year) * -1;
        if (result == 0) {
            result = Integer.compare(this.month, birthDate.month) * -1;
            if (result == 0)
                result = Integer.compare(this.day, birthDate.day) * -1;
        }
        return result;
    }

    // Метод, который возвращает возраст на основании даты рождения
    public int getAge() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR) - this.year;
    }

    @Override
    public boolean equals(Object obj) {
        BirthDate bd = (BirthDate) obj;
        return this.day == bd.day && this.month == bd.month && this.year == bd.year;
    }
}