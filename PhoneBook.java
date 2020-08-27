import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * S.R.P. - Single Responsibility Principle
 * Принцип Единой Ответственности (Обязанности)
 */
public class PhoneBook {
    // поля (характеристики) телефонной книги
    private List<Contact> contactList;
    private BufferedReader reader;

    // Единственный конструктор
    // КОНСТРУКТОР СОЗДАЁТ НОВЫЕ ОБЪЕКТЫ
    // Инициализирует новую Телефонную книгу
    public PhoneBook() {
        // в новой ТК инициализировалось хранилище для контактов
        contactList = new ArrayList<>();
        // в новой ТК инициализировался считыватель
        reader = new BufferedReader(new InputStreamReader(System.in));
    }

    /* run() запускает Телефонную книгу, выводит главную менюшку и ждёт выбор пользователя из главного меню
    Сам run() при приёме пункта меню от пользователя ничего не делает, а лишь отправляет выбранный пункт методу processBasicMenuInput()
    За то, чтобы обработать выбор пользователя отвечает processBasicMenuInput()
    Он запускает нужный метод в зависимости от того, какой пункт был выбран */

    public void run() {
        /* Сделать так, чтобы программа циклилась до тех пор, пока пользователь не введёт что-либо от 0 до 10
            Окружить следующий блок кода try/catch Для обработки следующих исключений:
            1) IOException (Возникает при reader.readLine() - оставляем стандартную обработку)
            2) NumberFormatException (Возникает, если пользователь ввёл не цифру, а слово, в обработке
            нужно сказать, что вводить можно только числа
            3) Нужно кинуть своё собственное IllegalArgumentException, если пользователь ввёдет
            что-то кроме 0 - 10 с соответствующим сообщением,
            в обработке его словить и вывести это сообщение

            Если пользователь введёт 0, то можно будет закончить программу 3-мя разными способами:
            1) break();
            2) return;
            3) System.exit(0);*/
        // Основная последовательность действий:
        // TODO: Вывести меню
        // TODO: Принять выбор пользователя
        // TODO: Зациклить первыё 2 TODO, пока пользователь не введёт что-либо от 0 до 10
        // TODO: Отправить ПРАВИЛЬНЫЙ ввод в processBasicMenuInput()
        // insert your code here
        int userChoice = 0;
        printBasicMenu();
        while (true) {
            try {
                System.out.print("\nВыберите один из пунктов меню (0 - 10): ");
                userChoice = Integer.parseInt(reader.readLine());
                if (userChoice == 0) {
                    System.out.println("\nПрограмма завершенна!");
                    System.exit(0);
                }
                if (userChoice < 0 || userChoice > 10)
                    throw new IllegalArgumentException("Возможен только выбор пунктов от 0 до 10.");

                processBasicMenuInput(userChoice);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода. Введите ЧИСЛО!");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void processBasicMenuInput(int userChoise) throws IOException {
        switch (userChoise) {
            case 1:
                printAllContacts(contactList);
                break;
            case 2:
                addNewContact(promptNewContactInfo());
                break;
            case 3:
                updateContactInfo();
                break;
            case 4:
                deleteContact();
                break;
            case 5:
                findContact();
                break;
            case 6:
                printContactUsingAgeFilter();
                break;
            case 7:
                exportTo();
                break;
            case 8:
                exportOneContact();
                break;
            case 9:
                importFrom();
                break;
            case 10:
                importOneContact();
                break;
        }
    }

    private void printAllContacts(List<Contact> list) {
        // TODO: Вывести все контакты в пронумерованном виде
        /*
        1) *первый контакт*
        2) *второй контакт*

        Должна быть проверка, что если телефонная книга
        пуста, то нужно вывести соответствующее сообщение*/
        if (list.isEmpty()) {
            System.out.println("\nТелефонная книга пуста.");
        } else {
            System.out.println();
            for (Contact contact : list) {
                System.out.printf("%d) %s\n", list.indexOf(contact) + 1, contact);
            }
        }
    }

    // Метод, который добавит в хранилище новый контакт, который
    // передаётся этому методу в качестве аргумента
    private void addNewContact(Contact newContact) {
        // TODO: Добавить новый контакт в хранилище
        // TODO: Проверьте, если такой контакт там уже есть, то сообщить об этом и не добавлять его
        // TODO: Если такого контакта там нет, то молча добавить
        // TODO: Oтсортировать хранилище и сообщить об успешном добавлении
        contactList.add(newContact);
        Collections.sort(contactList);
        System.out.println("\nКонтакт " + newContact.getLastName() + " " + newContact.getFirstName() + " успешно добавлен!");
    }

    // Именно этот метод будет отвечать за то, чтобы принять у пользователя
    // данные о новом контакте (его фамилию, имя и т.д.)
    // В конце этот метод должен вернуть новенький созданный контакт
    // Именно этот метод даст ново-созданный контакт методу addNewContact()
    // А тот уже, в свою очередь, добавит этот контакт в хранилище
    private Contact promptNewContactInfo() throws IOException {
        System.out.println("\n<----- Добавление нового контакт ----->");
        // TODO: Попросить и считать все основные данные нового контакта
        // TODO: Создать новый объект класса контакт и вернуть его
        System.out.print("Введите фамилию: ");
        String lastName = reader.readLine();
        System.out.print("Введите имя: ");
        String firstName = reader.readLine();
        System.out.print("Введите номер: ");
        String phoneNumber = reader.readLine();

        Contact contact = null;
        while (true) {
            try {
                System.out.print("Введите дату рождения в формате dd.mm.yyyy: ");
                String birthDate = reader.readLine();
                contact = new Contact(lastName, firstName, phoneNumber, new BirthDate(birthDate));
                break;
            } catch (IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }
        return contact;
    }

    private void updateContactInfo() throws IOException {
        /* Этот метод работает точно также как и метод run()
        Если метод run() печатал пользователю основное меню через printBasicMenu(),
        брал у пользователя выбор пункта из этого меню
        и отправлял этот пункт в processBasicMenuInput(),
        то этот метод делает то же самое, но уже для change menu и отправит
        выбор пользователя пункта этого меню в метод processChangeMenuInput()

        То есть аналогия такая
        run() -> updateContactInfo()
        processBasicMenuInput() -> processChangeMenuInput()

        Но в этом методе нужно ещё, чтобы пользователь вбивал 2 вещи
        1) Индекс того контакта, который он хочет поменять.
        2) Пункт из change menu (то есть, что он хочет поменять)

        Потом пункт меню и индекс контакта отправляются в метод processChangeMenuInput()

        Индекс контакт просится с помощью метода promptIndex()

        Не забудьте также в начале проверить, что если Телефонная книга пуста,
        то ничего выполнять не нужно, ведь там некого обновлять.*/
        if (contactList.isEmpty()) {
            System.out.println("\nТелефонная книга пуста.");
        } else {
            int userChoice = 0;
            printChangeMenu();
            while (true) {
                try {
                    System.out.print("\nВыберите один из пунктов меню (0 - 4): ");
                    userChoice = Integer.parseInt(reader.readLine());
                    if (userChoice == 0) {
                        System.out.println("\nВозврат в основное меню...");
                        return;
                    }
                    if (userChoice < 0 || userChoice > 4)
                        throw new IllegalArgumentException("Возможен только выбор пунктов от 0 до 4.");

                    processChangeMenuInput(promptIndex(contactList), userChoice);
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат ввода. Введите ЧИСЛО!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private int promptIndex(List<Contact> list) throws IOException {
        /* В этом методе нужно вывести все контакты пользователю, чтобы он
        просмотрел их и смог выбрать того, которого хочет поменять.
        Не забудьте, что для вывода всех контактов есть специальный метод,
        который это делает.
        После того, как пользователю вывелись все контакты он выбирает среди них
        И вот вот этот индекс, который он выберет этот метод и должен вернуть.

        Но помните про следующие особенности:
        1) Пользователь может ввести не индекс, а какое-то левое слово
        2) Пользователь может ввести индекс не существующего контакта
        3) Для пользователя список из 3-ёх контактов выглядит пронумерованным как
        1 - 2 - 3, но в хранилище то они хранятся как 0 - 1 - 2, соответственно
        если пользователь захочет поменять самый первый контакт он выберет 1,
        но метод должен вернуть 0, потому что самый первый контакт в ArrayList-е
        хранится именно на 0-ом индексе, а не на первом. То есть метод должен вернуть
        выбранный пользователем индекс контакта на 1 меньше.

        Не забудьте всё это зациклить, чтобы пользователь выбирал индекст контакта, который
        он хочет поменять до тех пор, пока не выберет правильно.*/
        printAllContacts(list);
        int indexToChange = 0;
        while (true) {
            try {
                System.out.print("\nВыберите контакт: ");
                indexToChange = Integer.parseInt(reader.readLine());
                if (indexToChange < 1 || indexToChange > list.size())
                    throw new IndexOutOfBoundsException("Нет контакта под таким номером!");
                else
                    return indexToChange - 1;
            } catch (IndexOutOfBoundsException ioob) {
                System.out.println(ioob.getMessage());
            } catch (NumberFormatException nfe) {
                System.out.println("Неверный формат ввода. Введите ЧИСЛО!");
            }
        }
    }

    private void processChangeMenuInput(int indexToChange, int userChoise) throws IOException {
        /* Этот метод принимает 2 аргумента, 1-ый - кого поменять, 2-ой - что поменять
        Надо так же как и в processBasicMenuInput() запустить switch, который
        в зависимости от выбранного userChoice будет менять соответствующее поле у контакта
        (например, если userChoise = 1, то значит надо менять фамилию, если 2, то имя и т.д.)

        Вот, как будет выглядеть примерно очередной case в этом switch для замены фамилии:
        1) Попросить у пользователя ввести новую фамилию
        2) Считать новую фамилию
        3) Заменить у выбранного контакта фамилию на новую
        4) Сообщить об успешном изменении

        С остальными кейсами аналогично */
        switch (userChoise) {
            case 1:
                System.out.print("Введите новую фамилию: ");
                String newLastName = reader.readLine();
                contactList.get(indexToChange).setLastName(newLastName);
                System.out.println("Фамилия была успешно изменена!");
                break;
            case 2:
                System.out.print("Введите новое имя: ");
                String newFirstName = reader.readLine();
                contactList.get(indexToChange).setFirstName(newFirstName);
                System.out.println("Имя было успешно изменено!");
                break;
            case 3:
                System.out.print("Введите новый номер: ");
                String newPhoneNumber = reader.readLine();
                contactList.get(indexToChange).setPhoneNumber(newPhoneNumber);
                System.out.println("Номер был успешно изменен!");
                break;
            case 4:
                while (true) {
                    try {
                        System.out.print("Введите новую дату рождения в формате дд.мм.гггг: ");
                        String newBirthDate = reader.readLine();
                        contactList.get(indexToChange).setBirthDate(new BirthDate(newBirthDate));
                        System.out.println("Дата рождения была успешно изменена!");
                        break;
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                    }
                }
                break;
        }
        System.out.println("Измененный контакт: " + contactList.get(indexToChange));
    }

    private void deleteContact() throws IOException {
        // TODO: Проверить пустоту ТК
        // TODO: Показать все контакты
        // TODO: ПОльзователь выбирает, кого удалить
        // TODO: Вы пересправшиваете
        // TODO: Если подтверждает - удалите, если нет - не удадяйте
        // TODO: Сообщите об успешном удалении либо об успешной отмене удаления

        if (contactList.isEmpty()) {
            System.out.println("Телефонная книга пуста!");
        } else {
            int indexToDelete = promptIndex(contactList);
            while (true) {
                try {
                    System.out.println("\nПодтвердить удаление?");
                    System.out.println("1 - YES\n2 - NO");
                    System.out.print("Ваш выбор: ");
                    int choice = Integer.parseInt(reader.readLine());
                    if (choice == 1) {
                        contactList.remove(indexToDelete);
                        System.out.println("Контакт успешно удалён!");
                        printAllContacts(contactList);
                        break;
                    } else if (choice == 2) {
                        System.out.println("Удаление отменено!");
                        break;
                    } else {
                        System.out.println("Выберите 1 или 2!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Вы должны ввести число (1 или 2)!");
                }
            }
        }
    }

    private void findContact() throws IOException {
        // TODO: Попросить и считать у пользователя данные для поиска (в виде стринга)
        /* Данными для поиска могут быть как буква, например "А", там и несколько букв, например "Алекс"
         * (это в случае, если польщователь хочет найти контакт (или контакты) по части фамилии или имени), а также цифры,
         * например "701" (это в случае, если пользователь хочет найти контакт (или контакты) по части номера) */
        // TODO: Вывести все контакты пронумерованными, у которых либо имя, либо фамилия, либо номер содержат введённые пользователем данные
        // TODO: Сделать так, чтобы поиск шёл в независимости от регистра
        /* То есть можно ввести префикс как "АлЕкС" или "алекс" или "АЛЕКС", но все равно найдутся все контакты,
         * имена или фамилии которых содержат такую последовательность символов.
         * Очень полезным будет метод "toLowerCase()", который есть у каждого стринга. */

        System.out.print("Введите что-либо для поиска: ");
        String strSeq = reader.readLine().toLowerCase();

        int counter = 0;
        for (Contact contact : contactList)
            if (contact.getLastName().toLowerCase().contains(strSeq) ||
                    contact.getFirstName().toLowerCase().contains(strSeq) ||
                    contact.getPhoneNumber().toLowerCase().contains(strSeq))
                System.out.printf("%d) %s\n", ++counter, contact);

        if (counter == 0)
            System.out.println("Не было найдено контактов, соответсвующих критерию поиска!");
    }

    private void printContactUsingAgeFilter() throws IOException {
        // TODO: Такой же метод, как и run() и udpateContactInfo(), но только для printAgeMenu()
        if (contactList.isEmpty()) {
            System.out.println("\nТелефонная книга пуста.");
        } else {
            int userChoice = 0;
            printAgeMenu();
            while (true) {
                try {
                    System.out.print("\nВыберите один из пунктов меню (0 - 3): ");
                    userChoice = Integer.parseInt(reader.readLine());
                    if (userChoice == 0) {
                        System.out.println("\nВозврат в основное меню...");
                        return;
                    }
                    if (userChoice < 0 || userChoice > 3)
                        throw new IllegalArgumentException("Возможен только выбор пунктов от 0 до 3.");

                    processAgeMenuInput(userChoice);
                    return;
                } catch (NumberFormatException e) {
                    System.out.println("Неверный формат ввода. Введите ЧИСЛО!");
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    private void processAgeMenuInput(int userChoise) throws IOException {
        // TODO: Попросить пользователя ввести возраст (зациклить, чтобы вводился возраст, пока не введётся число)
        // TODO: В зависимости от выбранного пункта 3-его меню
        // TODO: Вывести все контакты, соответствующе поиску, в пронумерованном виде
        // TODO: Если таких контактов нет, сообщить об этом
        // TODO: Попросить пользователя ввести возраст (зациклить, чтобы вводился возраст, пока не введётся число)
        // TODO: В зависимости от выбранного пункта 3-его меню
        // TODO: Вывести все контакты, соответствующе поиску, в пронумерованном виде
        // TODO: Если таких контактов нет, сообщить об этом
        int age = 0;
        while (true) {
            try {
                System.out.print("Введите возраст для поиска: ");
                age = Integer.parseInt(reader.readLine());
                if (age > 0 && age < 120)
                    break;
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат ввода. Введите ЧИСЛО!");
            }
        }

        int counter = 0;

        switch (userChoise) {
            case 1:
                for (Contact contact : contactList) {
                    if (contact.getBirthDate().getAge() > age)
                        System.out.printf("%d) %s\n", ++counter, contact);
                }
                if (counter == 0)
                    System.out.println("Нет контактов старше " + age);
                break;
            case 2:
                for (Contact contact : contactList) {
                    if (contact.getBirthDate().getAge() < age)
                        System.out.printf("%d) %s\n", ++counter, contact);
                }
                if (counter == 0)
                    System.out.println("Нет контактов младше " + age);
                break;
            case 3:
                for (Contact contact : contactList) {
                    if (contact.getBirthDate().getAge() == age)
                        System.out.printf("%d) %s\n", ++counter, contact);
                }
                if (counter == 0)
                    System.out.println("Нет контактов, которым " + age);
                break;
        }
    }

    private String getFileName() throws IOException {
        // TODO: Попросить у пользователя ввести имя файла
        System.out.print("Введите название файла: ");
        // TODO: Унифицировать регистр
        String fileName = reader.readLine().toLowerCase();
        // TODO: Если пользователь введёт имя файла БЕЗ расширения .txt
        // TODO: Очень полезен будет метод endsWith()
        if (!fileName.endsWith(".txt")) {
            // TODO: Добавить в конце расширение .txt
            fileName = fileName + ".txt";
        }
        return fileName;
    }

    private void exportTo() throws IOException {
        // TODO: Проверка пустоты
        if (contactList.isEmpty()) {
            System.out.println("Телефонная книга пуста!");
        }
        // TODO: с помощью метода getFileName() получить имя файла от пользователя
        String fileName = getFileName();

        try (FileOutputStream fos = new FileOutputStream("/Documents/Dev/Java/ProjectFromLessons/" + fileName);
             PrintWriter writer = new PrintWriter(fos)) {
            // TODO: занести все контакты в файл
            for (Contact contact : contactList) {
                writer.println(contact);
            }
            // TODO: сообщить об успешном экспорте
            System.out.println("Экспорт успешно завершён!");

        } catch (FileNotFoundException e) {
            System.out.printf("%s файл не был найден", fileName);
        }
    }

    private void exportOneContact() throws IOException {
        // TODO: Проверка пустоты
        if (contactList.isEmpty()) {
            System.out.println("Телефонная книга пуста!");
        }
        // TODO: с помощью метода getFileName() получить имя файла от пользователя
        String fileName = getFileName();

        try (FileOutputStream fos = new FileOutputStream("Files/" + fileName, true);
             PrintWriter writer = new PrintWriter(fos)) {
            // TODO: попросить у пользователя индекс того контакта, который он хочет хочет эспортировать
            // TODO: занести этот контакт в файл
            writer.println(contactList.get(promptIndex(contactList)));
            // TODO: сообщить об успешном экспорте
            System.out.println("Контакт был успешно экспортирован!");

        } catch (FileNotFoundException e) {
            System.out.printf("%s файл не был найден", fileName);
        }
    }

    private void importFrom() throws IOException {
        // TODO: с помощью метода getFileName() получить имя файла от пользователя
        String fileName = getFileName();

        try (FileInputStream fis = new FileInputStream("Files/" + fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

            // TODO: Считать все линии с файла
            // TODO: Что сделать с каждой линией
            // TODO: Разбить линию
            // TODO: Создать из кусочков новый контакт
            // TODO: Сообщить о результате импорта, сколько контактов было импортировано
            // TODO: Если файл пустой, то сказать, что ничего импортировано не было
            // TODO: Проверять формат ввода
            // TODO: Добавить проверку, если такой контакт есть, то пропустить

            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|");
                if (args.length != 4) {
                    System.out.println("Неправильный формат контакта: " + line);
                    continue;
                }

                Contact tempContact = new Contact(args[0], args[1], args[2], new BirthDate(args[3]));

                if (!contactList.contains(tempContact)) {
                    // TODO: После импорта контакты должны быть отсортированы в вашей ТК
                    addNewContact(tempContact);
                    counter++;
                }
            }

            if (counter != 0)
                System.out.println("\nИмпорт " + counter + " контакта(ов) был завершён успешно!");
            else
                System.out.println("Не было ничего импортировано!");

        } catch (FileNotFoundException e) {
            System.out.printf("%s файл не был найден", fileName);
        }
    }

    private void importOneContact() throws IOException {
        String fileName = getFileName();

        try (FileInputStream fis = new FileInputStream("Files/" + fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(fis))) {

            List<Contact> tempList = new ArrayList<>();

            String line;
            int counter = 0;
            while ((line = reader.readLine()) != null) {
                String[] args = line.split("\\|");
                if (args.length != 4) {
                    System.out.println("Неправльный формат контакта: " + line);
                    continue;
                }

                Contact tempContact = new Contact(args[0], args[1], args[2], new BirthDate(args[3]));

                // TODO: Добавить проверку, если такой контакт есть, то пропустить
                if (!tempList.contains(tempContact)) {
                    // TODO: После импорта контакты должны быть отсортированы в вашей ТК
                    tempList.add(tempContact);
                    counter++;
                }
            }

            if (counter == 0) {
                System.out.println("Не было ничего импортировано!");
                return;
            }

            System.out.println("\nВ указанном файле обнаружилось " + counter + " контакт(а/ов):");
            Contact tempContact = tempList.get(promptIndex(tempList));
            if (!contactList.contains(tempContact))
                addNewContact(tempContact);
            else
                System.out.println("Такой контакт уже есть в Телефонной книге!");
        } catch (FileNotFoundException e) {
            System.out.printf("%s файл не был найден", fileName);
        }
    }

    private void printBasicMenu() {
        System.out.println("\n<----------------- Меню действий ----------------->");
        System.out.println("|   <1> Просмотреть список контактов              |");
        System.out.println("|   <2> Добавить новый контакт                    |");
        System.out.println("|   <3> Обновить существующий контакт             |");
        System.out.println("|   <4> Удалить контакт                           |");
        System.out.println("|   <5> Поиск контактов                           |");
        System.out.println("|   <6> Просмотреть контакты старше/младше ... лет|");
        System.out.println("|   <7> Экспортировать контакты в файл            |");
        System.out.println("|   <8> Экспортировать 1 контакт в файл           |");
        System.out.println("|   <9> Импортировать контакты из файла           |");
        System.out.println("|   <10> Импортировать 1 контакт из файла         |");
        System.out.println("|   <0> Выйти                                     |");
        System.out.println("<------------------------------------------------->");
    }

    private void printChangeMenu() {
        System.out.println("\n<----------------- Меню действий ----------------->");
        System.out.println("|    <1> Изменить фамилию контакта                |");
        System.out.println("|    <2> Изменить имя контакта                    |");
        System.out.println("|    <3> Изменить номер контакта                  |");
        System.out.println("|    <4> Изменить дату рождения контакта          |");
        System.out.println("|    <0> Вернуться в основное меню                |");
        System.out.println("<--------------------------------------------------->");
    }

    private void printAgeMenu() {
        System.out.println("\n<----------------- Меню действий ----------------->");
        System.out.println("|    <1> Старше определённого возраста            |");
        System.out.println("|    <2> Младше определённого возраста            |");
        System.out.println("|    <3> Ровесники                                |");
        System.out.println("|    <0> Вернуться в основное меню                |");
        System.out.println("<--------------------------------------------------->");
    }
}
