import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class StudentScoreProcessorTest {

    @TempDir
    Path tempDir;

    @Test
    void testProcessStudentScores() throws IOException {
        // Create a test CSV file
        File testFile = tempDir.resolve("test_scores.csv").toFile();
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("student_id,name,subject,score\n");
            writer.write("1,John Doe,Math,85.5\n");
            writer.write("1,John Doe,Science,90.0\n");
            writer.write("2,Jane Smith,Math,95.0\n");
            writer.write("2,Jane Smith,Science,88.5\n");
        }

        // Create processor and process the file
        StudentScoreProcessor processor = new StudentScoreProcessor();
        processor.processStudentScores(testFile.getAbsolutePath());
    }

    @Test
    void testEmptyFile() throws IOException {
        File emptyFile = tempDir.resolve("empty.csv").toFile();
        emptyFile.createNewFile();

        StudentScoreProcessor processor = new StudentScoreProcessor();
        processor.processStudentScores(emptyFile.getAbsolutePath());
    }

    @Test
    void testInvalidScore() throws IOException {
        File invalidFile = tempDir.resolve("invalid_scores.csv").toFile();
        try (FileWriter writer = new FileWriter(invalidFile)) {
            writer.write("student_id,name,subject,score\n");
            writer.write("1,John Doe,Math,invalid\n");
            writer.write("2,Jane Smith,Math,95.0\n");
        }

        StudentScoreProcessor processor = new StudentScoreProcessor();
        processor.processStudentScores(invalidFile.getAbsolutePath());
    }

    @Test
    void testInvalidRowFormat() throws IOException {
        File invalidFile = tempDir.resolve("invalid_format.csv").toFile();
        try (FileWriter writer = new FileWriter(invalidFile)) {
            writer.write("student_id,name,subject,score\n");
            writer.write("1,John Doe,Math\n"); // Missing score
            writer.write("2,Jane Smith,Math,95.0\n");
        }

        StudentScoreProcessor processor = new StudentScoreProcessor();
        processor.processStudentScores(invalidFile.getAbsolutePath());
    }

    @Test
    void testNonExistentFile() {
        StudentScoreProcessor processor = new StudentScoreProcessor();
        processor.processStudentScores("nonexistent.csv");
    }
} 