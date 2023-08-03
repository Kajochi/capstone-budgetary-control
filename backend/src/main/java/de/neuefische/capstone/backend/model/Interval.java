package de.neuefische.capstone.backend.model;

public enum Interval {

    MONTHLY,
    QUARTERLY,
    HALF_YEARLY,
    YEARLY,
    ONCE;


    public static int getMultiplier(Interval interval) {
        return switch (interval) {
            case MONTHLY -> 1;
            case QUARTERLY -> 3;
            case HALF_YEARLY -> 6;
            case YEARLY -> 12;
            case ONCE -> 1;
            default -> throw new IllegalArgumentException("Unknown interval: " + interval);
        };

    }
}

