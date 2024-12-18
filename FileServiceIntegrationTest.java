import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class FileServiceIntegrationTest {

    private final FileService fileService = new FileService();

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "file?name", "file*name", "file|name", "file<name>", "file>name", "file:name"})
    void fileExists_whenFileNameIsInvalid_thenThrowIllegalArgumentException(String invalidFileName) {
        System.out.println("Testing invalid file name: " + invalidFileName);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fileService.fileExists(invalidFileName);
        });
    }

    @Test
    void fileExists_whenFileExists_thenReturnTrue() {
        String existingFileName = "existingFile.txt";
        fileService.createFile(existingFileName);

        boolean result = fileService.fileExists(existingFileName);
        System.out.println("File exists: " + result);
        Assertions.assertTrue(result);

        new java.io.File(existingFileName).delete();
    }

    @Test
    void fileExists_whenFileDoesNotExist_thenReturnFalse() {
        String nonExistingFileName = "nonExistingFile.txt";

        boolean result = fileService.fileExists(nonExistingFileName);
        System.out.println("File does not exist: " + result);
        Assertions.assertFalse(result);
    }

    @Test
    void createFile_whenFileNameIsValid_thenFileIsCreated() {
        String validFileName = "newTestFile.txt";

        fileService.createFile(validFileName);
        boolean result = new java.io.File(validFileName).exists();
        System.out.println("File created: " + result);

        Assertions.assertTrue(result);

        new java.io.File(validFileName).delete();
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "file?name", "file*name", "file|name"})
    void createFile_whenFileNameIsInvalid_thenThrowIllegalArgumentException(String invalidFileName) {
        System.out.println("Testing invalid file name for creation: " + invalidFileName);
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            fileService.createFile(invalidFileName);
        });
    }
}
