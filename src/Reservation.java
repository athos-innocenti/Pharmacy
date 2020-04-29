public class Reservation {
    private final Client clientIdentifier;
    private final String desiredMedicineName;
    private final boolean isDesiredMedicineOriginal;

    public Reservation(Client clientIdentifier, String desiredMedicineName, boolean isDesiredMedicineOriginal) {
        this.clientIdentifier = clientIdentifier;
        this.desiredMedicineName = desiredMedicineName;
        this.isDesiredMedicineOriginal = isDesiredMedicineOriginal;
    }

    public Client getClientIdentifier() {
        return clientIdentifier;
    }

    public String getDesiredMedicineName() {
        return desiredMedicineName;
    }

    public boolean isDesiredMedicineOriginal() {
        return isDesiredMedicineOriginal;
    }
}
