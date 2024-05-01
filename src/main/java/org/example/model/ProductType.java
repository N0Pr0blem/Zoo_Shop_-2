package org.example.model;

public enum ProductType {
    CAT, DOG, RODENT, PARROT;
    public static ProductType fromString(String roomTypeString) {
        for (ProductType type : ProductType.values()) {
            if (type.name().equalsIgnoreCase(roomTypeString)) {
                return type;
            }
        }
        throw new IllegalArgumentException("No enum constant " + ProductType.class.getName() + "." + roomTypeString);
    }
}
