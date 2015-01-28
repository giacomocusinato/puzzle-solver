import java.rmi.*;
import java.rmi.server.*;
import java.util.*;
import java.util.concurrent.*;

interface ISolver extends Remote {
    public String reorder(String input) throws RemoteException;
}


public class PuzzleSolverServer extends UnicastRemoteObject implements ISolver {
    private HashMap<String, Block> puzzleMap;
    private String[][] orderedKeys;
    private int cols = 0; // Size of a column (number of rows)
    private int rows = 0; // Size of a row (number of columns)

    public PuzzleSolverServer() throws RemoteException { }

    public String reorder(String inputContent) throws RemoteException {
        parseContent(inputContent);
        reorderPuzzle();

        String result = getPuzzleResult();

        reset();

        return result;
    }


    /**
    * Parse the input file in a HashMap type field.
    * Gets the rows and the columns number of the puzzle.
    * @param inputContent
    */
    private void parseContent(String inputContent) {
        if (puzzleMap == null) {
            puzzleMap = new HashMap<String, Block>();
        }
        String[] lines = inputContent.split(System.lineSeparator());
        for (int i = 0; i < lines.length; ++i) {
            String[] val = lines[i].split("\t");

            Block block = new Block(
            val[0].trim(),
            val[1],
            val[2].trim(),
            val[3].trim(),
            val[4].trim(),
            val[5].trim()
            );
            puzzleMap.put(block.getId(), block);

            if (block.getLeftId().equals(Block.EMPTY_BLOCK)) cols++;
            if (block.getTopId().equals(Block.EMPTY_BLOCK)) rows++;
        }
    }

    /**
    * Orders the puzzle.
    */
    private void reorderPuzzle() {
        orderedKeys = new String[rows][cols];
        // Start by filling the array's first columns with the puzzle's
        // first column (leftID = EMPTY_BLOCK) in random a order
        int i = 0;
        for (Map.Entry<String, Block> entry : puzzleMap.entrySet()) {
            if (entry.getValue().getLeftId().equals(Block.EMPTY_BLOCK)) {
                orderedKeys[0][i++] = entry.getKey();
            }
        }


        ExecutorService executor = Executors.newFixedThreadPool(5);
        for (int r = 0; r < cols; ++r) {
            Runnable thread = new PuzzleThread(r, this);
            executor.execute(thread);
        }
        executor.shutdown();
        while (!executor.isTerminated()) { } // Wait here until the executor finish his work


        // Reorder the rows

        // Find the first row (topID = EMPTY_BLOCK)
        for (int c = 0; c < cols; ++c) {
            String topId = puzzleMap.get(orderedKeys[0][c]).getTopId();
            if (topId.equals(Block.EMPTY_BLOCK)) {
                rowSwap(c, 0);
            }
        }

        // Link the remaing rows
        for (int c = 0; c < cols; ++c) {
            String firstBottomId = puzzleMap.get(orderedKeys[0][c]).getBottomId();
            for (int k = c+1; k < cols; ++k) {
                String firstId = puzzleMap.get(orderedKeys[0][k]).getId();
                if (firstBottomId.equals(firstId)) {
                    rowSwap(c + 1, k);
                }
            }
        }
    }


    public void reorderRow(int row) {
        for (int r = 1; r < rows; ++r) {
            orderedKeys[r][row] = puzzleMap.get(orderedKeys[r-1][row]).getRightId();
        }
    }

    private void rowSwap(int firstColumn, int secondColumn) {
        if (firstColumn == secondColumn){
            return;
        }
        for (int i = 0; i < rows; ++i) {
            String temp = orderedKeys[i][firstColumn];
            orderedKeys[i][firstColumn] = orderedKeys[i][secondColumn];
            orderedKeys[i][secondColumn] = temp;
        }
    }

    /**
    * Gets the puzzle's result in a single line
    * @return
    */
    public String getInlineResult() {
        StringBuilder builder = new StringBuilder();
        for (int c = 0; c < cols; ++c) {
            for (int r = 0; r < rows; ++r) {
                builder.append(puzzleMap.get(orderedKeys[r][c]).getValue());
            }
        }
        return builder.toString().trim();
    }

    /**
    * Gets the puzzle's result divided by rows and columns
    * @return
    */
    public String getTabularResult() {
        StringBuilder builder = new StringBuilder();
        for (int c = 0; c < cols; ++c) {
            for (int r = 0; r < rows; ++r) {
                builder.append(puzzleMap.get(orderedKeys[r][c]).getValue());
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString().trim();
    }

    /**
    * Gets the puzzle's rows number
    * @return
    */
    public int getRowNumber() {
        return cols;
    }

    /**
    * Get's the puzzle's columns number
    * @return
    */
    public int getColumnNumber() {
        return rows;
    }

    /**
    * Get's the puzzle result both linear and
    * tabular version, plus the number of row and
    * columns
    * @return
    */
    public String getPuzzleResult() {
        return getInlineResult() +
        System.lineSeparator() +
        System.lineSeparator() +
        getTabularResult() +
        System.lineSeparator() +
        System.lineSeparator() +
        + getRowNumber() + " " +
        getColumnNumber();
    }


    /**
    * Reset object's fileds on order to solve other's puzzle
    */
    private void reset() {
        puzzleMap = null;
        orderedKeys = null;
        cols = 0;
        rows = 0;
    }

}
