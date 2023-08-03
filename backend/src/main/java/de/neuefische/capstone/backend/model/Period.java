package de.neuefische.capstone.backend.model;

public enum Period {
    MONTH,
    QUARTER,
    HALF_YEAR,
    YEAR;

    public int getNumMonths() {
        return switch (this) {
            case MONTH -> 1;
            case QUARTER -> 3;
            case HALF_YEAR -> 6;
            case YEAR -> 12;
            default -> throw new IllegalArgumentException("Unknown period: " + this);
        };
    }
}
