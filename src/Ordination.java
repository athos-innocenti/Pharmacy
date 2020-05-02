public class Ordination {
    private final String name;
    private final boolean isOrginal;
    private final int clientId;

    public Ordination(String name, boolean isOrginal, int clientId) {
        this.name = name;
        this.isOrginal = isOrginal;
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public boolean isOrginal() {
        return isOrginal;
    }

    public int getClientId() {
        return clientId;
    }
}
