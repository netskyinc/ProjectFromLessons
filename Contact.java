/**
 * Наследование - это когда один класс - ЧАСТНЫЙ другого класса
 * <p>
 * Композиция - это когда объект одного класса является
 * полем другого класса
 */
public class Contact implements Comparable<Contact> {
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private BirthDate birthDate;

    // TODO: Добавить default и user contructors, setters, getters
    public Contact(String lastName, String firstName, String phoneNumber, BirthDate birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public Contact() {
        this.lastName = null;
        this.firstName = null;
        this.phoneNumber = null;
        this.birthDate = null;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public BirthDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(BirthDate birthDate) {
        this.birthDate = birthDate;
    }

    // TODO: Добавтить toString(), контакт должен выводиться в формате
    // lastName|firstName|phoneNumber|birthDate
    @Override
    public String toString() {
        return lastName + '|' + firstName + '|' + phoneNumber + '|' + birthDate;
    }

    // TODO: Добавить возможность сравнивать контакты
    /* Контакты сравниваются вначале по фамилии, если фамилии одинаковые,
    то по имени, если имена одинаковые, то по старшинству */
    @Override
    public int compareTo(Contact o) {
        int result = this.lastName.compareTo(o.lastName);
        if (result == 0) {
            result = this.firstName.compareTo(o.firstName);
            if (result == 0)
                result = this.birthDate.compareTo(o.birthDate); // Для этого в BirthDate нужен compareTo() вместе с implements Comparable<BirthDate>
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        Contact c = (Contact) obj;
        return this.lastName.equals(c.lastName) && this.firstName.equals(c.firstName) && this.phoneNumber.equals(c.phoneNumber) && this.birthDate.equals(c.birthDate);
    }
}

