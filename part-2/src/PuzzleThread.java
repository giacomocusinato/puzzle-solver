/**
 * PuzzleThread
 *
 * Represent a Thread object for apply parallel operation in the PuzzleSolver.  
 *
 * Part of the PuzzleResolver project for Parallel and Concurrent Programming
 * teaching at Padova university (Università degli Studi di Padova).
 *
 * @author Giacomo Cusinato
 * @version 1.0
 */

public class PuzzleThread implements Runnable {
	private int firstRow; // First row to order
	private int columSpan; // Number of row to reorder starting from firstRow 
	private PuzzleSolver solver;

	public PuzzleThread(int row, int span, PuzzleSolver pSolver) {
		firstRow = row;
		columSpan = span;
		solver = pSolver;
	}

	public void run() {
		solver.reoderRows(firstRow, columSpan);
	}
}