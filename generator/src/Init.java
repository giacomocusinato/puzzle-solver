/**
* Init 
* 
* Initializer for the Generator class.
* 
* Part of the PuzzleResolver project for Parallel and Concurrent Programming
* teaching at Padova university (Università degli Studi di Padova).
*
* @author Giacomo Cusinato
* @version 1.0
* @see Generator
*/

public class Init {
	public static void main(String[] args) {
		// Parse the args list
		if (args.length < 3) {
            System.out.println("Need rows, columns and a sentence in the args list.");
            return;
        } 
        int columnsNumber = 0, rowsNumber = 0;
        try {
            rowsNumber = Integer.parseInt(args[0]);
            columnsNumber = Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Failed to parse a numeric argument.");
        }
        String puzzleSentence = args[2]; // Build the string from the remaining args
        for (int i=3; i < args.length; ++i) {
            puzzleSentence = puzzleSentence + " " + args[i];
        }
        // If the sentence's lenght is bigger than puzzle's dim, cut the string
        if (puzzleSentence.length() > rowsNumber*columnsNumber) {
            puzzleSentence = puzzleSentence.substring(0, rowsNumber*columnsNumber); 
        }

        Generator generator = new Generator(columnsNumber, rowsNumber, puzzleSentence);
        generator.generateBlocks();
        generator.outputFile(OUT_FILE);
	}
}