package org.sopt.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import org.sopt.domain.Post;

public class FileIOUtil {
    private static final String FILEPATH = System.getProperty("user.dir") + "/src/main/java/org/sopt/assets/Post.txt";

    public void saveToFile(List<Post> posts) {
        File file = new File(FILEPATH);

        try {
            if (!file.exists()) {
                file.createNewFile();
            }

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                for (Post post : posts) {
                    String line = String.format("🆔 %d | 📌 제목: %s\n", post.getId(), post.getTitle());
                    writer.write(line);
                }
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
