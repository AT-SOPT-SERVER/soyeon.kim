package org.sopt.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.function.Function;

public class FileIOUtil {
    private static final String FILEPATH = System.getProperty("user.dir") + "/src/main/java/org/sopt/assets/Post.txt";

    public static <T> void saveToFile(List<T> items, Function<T, String> formatter) {
        File file = new File(FILEPATH);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (T item : items) {
                writer.write(formatter.apply(item));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
