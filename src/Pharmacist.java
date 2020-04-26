public class Pharmacist {
    private String name;
    private String surname;
    private boolean isDirector;

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
}
