import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

public class TestUtil {
    public static void initFileWithContext(String file, String context) throws IOException {
        Files.write(Paths.get(file), Collections.singleton(context));
    }

    public static void deleteFile(String path) throws IOException {
        if (Files.exists(Paths.get(path))) {
            Files.delete(Paths.get(path));
        }
    }
}
