import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TestUtil {
    public static void initFileWithContext(String file, List<String> contexts) throws IOException {
        Files.write(Paths.get(file), contexts);
    }

    public static void deleteFile(String path) throws IOException {
        if (Files.exists(Paths.get(path))) {
            Files.delete(Paths.get(path));
        }
    }

    public static String getResourcePath(String fileName) {
        return TestUtil.class
                .getResource(fileName)
                .getPath();
    }
}
