package com.weatherclient.utils;

/**
 * Created by Przemysław Książek
 on 2017-02-04.
 */

public final class EqualsUtils {
    private EqualsUtils() {
    }

    public static <T> boolean equals(T object1, T object2) {
        return object1 == null ? object2 == null : object1.equals(object2);
    }

    public static <E extends Enum<E>> boolean equals(E enum1, E enum2) {
        return enum1 == enum2;
    }

    public static boolean equals(boolean value1, boolean value2) {
        return value1 == value2;
    }

    public static boolean equals(byte value1, byte value2) {
        return value1 == value2;
    }

    public static boolean equals(short value1, short value2) {
        return value1 == value2;
    }

    public static boolean equals(int value1, int value2) {
        return value1 == value2;
    }

    public static boolean equals(long value1, long value2) {
        return value1 == value2;
    }

    public static boolean equals(float value1, float value2) {
        //noinspection FloatingPointEquality
        return value1 == value2;
    }

    public static boolean equals(double value1, double value2) {
        //noinspection FloatingPointEquality
        return value1 == value2;
    }

    public static boolean equals(char value1, char value2) {
        return value1 == value2;
    }
}