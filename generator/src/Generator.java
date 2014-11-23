/**
 * Class: Generator 
 *	
 * Author: Giacomo Cusinato
 *
 * Creation date: 22/11/201
 *
 * Part of the PuzzleResolver project for Parallel and Concurrent Programming
 * teaching at Padova university (Università degli Studi di Padova).
 */

//package generator;

//import generator.Block;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class Generator {
	private List<Block> blocks;
	private int columns = 0;
	private int rows = 0;
	private String sentence = "";

    private static String OUT_FILE = "random_puzzle.txt";

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

	public Generator(int columnsNumber, int rowsNumber, String puzzleSentence) {
		this.columns = columnsNumber;
		this.rows = rowsNumber;	
		this.sentence = puzzleSentence;
	 	this.blocks = new ArrayList<Block>();
	}

	public void generateBlocks() {
		for (int i = 0; i < rows * columns; ++i) {
            String blockValue = " ";
            if (i < sentence.length()) {
            	// Get the character for the next block object
                blockValue = sentence.substring(i, i+1);
            } 
            Block block = new Block(
                    Integer.toString(i),
                    blockValue,
                    Integer.toString(i-5),
                    Integer.toString(i+5),
                    Integer.toString(i+1),
                    Integer.toString(i-1)
            );
            // If the block is in the first row, the topId is empty
            if (i < columns) { 
                block.setTopId(Block.EMPTY_BLOCK);
            } 
            // if the block is in the first column, leftId is empty
            if ((i % columns) == 0) { 
                block.setLeftId(Block.EMPTY_BLOCK);
            } 
            // if the block is in the last column, rightId is empty
            if ((i + 1) % columns == 0) { 
                block.setRightId(Block.EMPTY_BLOCK);
            }
            // if the block is in the last row, bottomId is empty
            if (i >= (rows * columns) - columns) {
                block.setBottomId(Block.EMPTY_BLOCK);
            }
            blocks.add(block);
        }

	}

    public String blocksToString () {
        String blocksStr = "";
        for (Block block : blocks) {
            blocksStr = blocksStr + "\n" + block.toString();
        }
        return blocksStr;
    }

	public void randomizeBlocks() {
        Collections.shuffle(blocks);
	}

	public void outputFile(Strng filename) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filename), "UTF-8"));
            writer.write(this.blocksToString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}