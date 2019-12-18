import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Books {

    public static List<String> getBooks(Path path) {
        try {
            return Files.readAllLines(path, StandardCharsets.UTF_8);

        } catch(IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }
}
