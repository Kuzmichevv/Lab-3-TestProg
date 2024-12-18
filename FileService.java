import java.io.File;
import java.io.IOException;

public class FileService {

    public boolean fileExists(String fileName) {
        if (fileName == null || fileName.trim().isEmpty() || fileName.matches(".*[<>:\"/\\\\|?*].*")) {
            throw new IllegalArgumentException("Invalid file name");
        }
        return new File(fileName).exists();
    }

    public void createFile(String fileName) {
        if (fileName == null || fileName.trim().isEmpty() || fileName.matches(".*[<>:\"/\\\\|?*].*")) {
            throw new IllegalArgumentException("Invalid file name");
        }
        try {
            File file = new File(fileName);
            if (!file.createNewFile()) {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create file", e);
        }
    }
}
