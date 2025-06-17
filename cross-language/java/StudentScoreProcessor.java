import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

/**
 * Processes student scores from a CSV file and generates summary statistics.
 * The CSV file should have the format: student_id,name,subject,score
 */
public class StudentScoreProcessor {
    
    /**
     * Processes student scores from a CSV file and prints summary statistics.
     *
     * @param filePath Path to the CSV file containing student scores
     */
    public void processStudentScores(String filePath) {
        Map<String, StudentData> studentScores = new HashMap<>();
        Map<String, List<Double>> subjectScores = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Skip header line
            String header = reader.readLine();
            if (header == null) {
                System.out.println("Error: Empty file");
                return;
            }

            String line;
            while ((line = reader.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length != 4) {
                    System.out.println("Invalid row format. Skipping row: " + line);
                    continue;
                }

                String studentId = values[0].trim();
                String name = values[1].trim();
                String subject = values[2].trim();
                double score;

                try {
                    score = Double.parseDouble(values[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid score for student " + name + ". Skipping row.");
                    continue;
                }

                // Aggregate per student
                studentScores.computeIfAbsent(studentId, k -> new StudentData(name, new ArrayList<>()))
                           .scores.add(score);

                // Aggregate per subject
                subjectScores.computeIfAbsent(subject, k -> new ArrayList<>())
                           .add(score);
            }

            // Print student averages
            System.out.println("\nStudent Averages:");
            studentScores.forEach((sid, data) -> {
                double avg = data.scores.stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0);
                System.out.printf("%s (ID: %s) - Average Score: %.2f%n", 
                    data.name, sid, avg);
            });

            // Print top scorer(s)
            double topAvg = studentScores.values().stream()
                    .mapToDouble(data -> data.scores.stream()
                            .mapToDouble(Double::doubleValue)
                            .average()
                            .orElse(0.0))
                    .max()
                    .orElse(0.0);

            System.out.println("\nTop Scorer(s):");
            studentScores.forEach((sid, data) -> {
                double avg = data.scores.stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0);
                if (Math.abs(avg - topAvg) < 1e-6) {
                    System.out.printf("%s (ID: %s) with %.2f%n", 
                        data.name, sid, avg);
                }
            });

            // Print subject-wise averages
            System.out.println("\nSubject Averages:");
            subjectScores.forEach((subject, scores) -> {
                double avg = scores.stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0);
                System.out.printf("%s: %.2f%n", subject, avg);
            });

        } catch (IOException e) {
            System.out.println("Error: File '" + filePath + "' not found or cannot be read.");
        } catch (Exception e) {
            System.out.println("Unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * Helper class to store student data
     */
    private static class StudentData {
        final String name;
        final List<Double> scores;

        StudentData(String name, List<Double> scores) {
            this.name = name;
            this.scores = scores;
        }
    }
} 