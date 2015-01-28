/**
 * PuzzleThread
 *
 * Represent a Thread object for apply parallel operation in the PuzzleSolver.
 *
 * Part of the PuzzleSolver project for Parallel and Concurrent Programming
 * teaching at Padova university (Università degli Studi di Padova).
 *
 * @author Giacomo Cusinato
 * @version 1.0
 */

public class PuzzleThread implements Runnable {
	private int row; // Row to order
	private PuzzleSolverServer solver; // PuzzleSolver instance

	public PuzzleThread(int r, PuzzleSolverServer pSolver) {
		row = r;
		solver = pSolver;
	}

	public void run() {
		solver.reorderRow(row);
	}
}
