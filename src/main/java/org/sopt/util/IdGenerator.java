package org.sopt.util;

public class IdGenerator {
    private static int id = 1;

    public static int autoIncrement() {
        return id++;
    }
}
