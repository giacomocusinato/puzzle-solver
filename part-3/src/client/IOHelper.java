import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * IOHelper
 *
 * Expose methods for reading/writing Strings to file.
 *
 * Part of the PuzzleSolver project for Parallel and Concurrent Programming
 * teaching at Padova university (Università degli Studi di Padova).
 *
 * @author Giacomo Cusinato
 * @version 1.0
 */
public class IOHelper {
    private static Charset charset = StandardCharsets.UTF_8;

    /**
     * Performs a read in the file with the given path
     *
     * @param inputFile
     * @return
     */
    public static String readString(String inputFile) {
        Path inputPath = null;
        try {
            inputPath = Paths.get(inputFile);
        } catch (InvalidPathException ex) {
            System.out.println("Invalid input path. Details:");
            ex.printStackTrace();
            return "";
        }

        StringBuilder content = new StringBuilder();
        BufferedReader reader = null;
        try {
            reader = Files.newBufferedReader(inputPath, charset);
            String line = null;
            while ((line = reader.readLine()) != null) {
                content.append(line + System.lineSeparator());
            }
        } catch (IOException ex) {
            System.out.println("An IO error has occurred. Details: ");
            ex.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    System.out.println("An IO error has occurred. Details: ");
                    ex.printStackTrace();
                }
            }
        }

        return content.toString();
    }

    /**
     * Writes the given String in the file with the given path
     *
     * @param outputFile
     * @param content
     */
    public static void writeString(String outputFile, String content) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), "UTF-8"));
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            System.out.println("An IO error has occurred. Details:");
            ex.printStackTrace();
        }

    }
}
