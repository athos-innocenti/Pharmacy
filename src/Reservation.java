class Reservation {
    private final Client clientIdentifier;
    private final String desiredMedicineName;
    private final boolean isDesiredMedicineOriginal;

    Reservation(Client clientIdentifier, String desiredMedicineName, boolean isDesiredMedicineOriginal) {
        this.clientIdentifier = clientIdentifier;
        this.desiredMedicineName = desiredMedicineName;
        this.isDesiredMedicineOriginal = isDesiredMedicineOriginal;
    }

    Client getClientIdentifier() {
        return clientIdentifier;
    }

    String getDesiredMedicineName() {
        return desiredMedicineName;
    }

    boolean isDesiredMedicineOriginal() {
        return isDesiredMedicineOriginal;
    }
}
