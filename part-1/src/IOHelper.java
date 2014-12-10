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
 * Expose methods to reading/writing Strings to file.
 *
 * Part of the PuzzleResolver project for Parallel and Concurrent Programming
 * teaching at Padova university (Università degli Studi di Padova).
 *
 * @author Giacomo Cusinato
 * @version 1.0
 */
public class IOHelper {
    private static Charset charset = StandardCharsets.UTF_8;

    /**
     * Performs a read in the file with the given path
     * @param inputFile
     * @return
     */
    public static String readString(String inputFile) {
        Path inputPath = null;
        try {
            inputPath = Paths.get(inputFile);
        } catch (InvalidPathException ex) {
            exceptionHelper("Invalid input path", ex.getMessage());
            return  "";
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
            exceptionHelper("An IO error has occurred.", ex.getMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ex) {
                    exceptionHelper("An IO error has occurred.", ex.getMessage());
                }
            }
        }

        return content.toString();
    }

    /**
     * Writes the given String in the file with the given path
     * @param outputFile
     * @param content
     */
    public  static void writeString(String outputFile, String content) {
        BufferedWriter writer = null;
        try {
/*
            File yourFile = new File(outputFile);
            if(!yourFile.exists()) {
                yourFile.createNewFile();
            }
*/
            writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(outputFile), "UTF-8"));
            writer.write(content);
            writer.close();
        } catch (IOException ex) {
            //exceptionHelper("An IO error has occurred.", ex.printStackTrace());
            ex.printStackTrace();
        }

    }

    private static void exceptionHelper(String message, String errorMessage) {
        System.out.println(message);
        if (errorMessage != null)
            System.out.println(String.format("Error message: %s", errorMessage));
    }}
