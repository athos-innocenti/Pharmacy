class FullWarehouseException extends Exception {
    FullWarehouseException() {
        super("Il magazzino è pieno!");
    }
}