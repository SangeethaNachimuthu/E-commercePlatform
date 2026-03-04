package se.lexicon.ecommerceplatform.entity;

public enum OrderStatus {

    CREATED,
    PAID,
    SHIPPED,
    CANCELLED;

    public static OrderStatus fromString(String status) {
        if (status == null || status.isEmpty()) {
            return CREATED;
        }
        else {
            throw new IllegalArgumentException("Invalid status: " + ". Valid status are CREATED, PAID, SHIPPED, CANCELLED");
        }
    }
}
