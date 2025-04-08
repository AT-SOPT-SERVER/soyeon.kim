package org.sopt.util;

public class IdGenerator {
    private int id = 1;

    public int autoIncrement() {
        return id++;
    }
}
