public class Ordination {
    private final String orderedMedicineName;
    private final boolean isOrginal;
    private final int clientNum;

    Ordination(String orderedMedicineName, boolean isOrginal, int clientNum) {
        this.orderedMedicineName = orderedMedicineName;
        this.isOrginal = isOrginal;
        this.clientNum = clientNum;
    }

    public String getOrderedMedicineName() {
        return orderedMedicineName;
    }

    boolean isOrginal() {
        return isOrginal;
    }

    int getClientNum() {
        return clientNum;
    }
}
