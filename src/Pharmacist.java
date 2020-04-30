public class Pharmacist {
    private final String name;
    private final String surname;
    private final boolean isDirector;

    public Pharmacist(String name, String surname, boolean isDirector) {
        this.name = name;
        this.surname = surname;
        this.isDirector = isDirector;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public boolean isDirector() {
        return isDirector;
    }
}