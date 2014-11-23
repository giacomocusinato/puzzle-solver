import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.IOException;

/**
* Generator 
* 
* Manage the generation of the input file for the PuzzleResolver project.
* 
* Part of the PuzzleResolver project for Parallel and Concurrent Programming
* teaching at Padova university (Università degli Studi di Padova).
*
* @author Giacomo Cusinato
* @version 1.0
*/

public class Generator {
	private List<Block> blocks;
	private int columns = 0;
	private int rows = 0;
	private String sentence = "";

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
            blocksStr = block.toString() + "\n" + blocksStr;
        }
        return blocksStr;
    }

	public void randomizeBlocks() {
        Collections.shuffle(blocks);
	}

	public void outputFile(String filename) {
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