import java.rmi.*;
import java.net.*;

/**
 * PuzzleSolverClient
 *
 * Main class of the Puzzle Solver . Performs input of files containing
 * a well typed puzzle, send data to the server and output the solved solution.
 *
 * Part of the PuzzleSolver project for Parallel and Concurrent Programming
 * teaching at Padova university (Università degli Studi di Padova).
 *
 * @author Giacomo Cusinato
 * @version 2.0
 */

interface ISolver extends Remote {
    public String reorder(String input) throws RemoteException;
}


public class PuzzleSolverClient {
    private static final String REMOTE_OBJ = "PuzzleSolver";

    public PuzzleSolverClient(String inputPath, String outputPath, String serverName) {

        // Get the input file content
        String inputContent = IOHelper.readString(inputPath);

        // Get the reference to the remote object
        String uri = String.format("rmi://%s/%s", serverName, REMOTE_OBJ);
        ISolver solver = null;
        try {
            solver = (ISolver) Naming.lookup(uri);
        } catch (Exception e) {
            System.out.println("An error has occurred in getting the server object.");
        }

        // Call the remote solver method
        String solvedPuzzle = "";
        try {
            solvedPuzzle = solver.reorder(inputContent);
        } catch (RemoteException e) {
            System.out.println(e.getMessage());
            return;
        }

        // Output the retrieved string
        IOHelper.writeString(outputPath, solvedPuzzle);
    }

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Argument list is invalid." +
                "Need input file, output file and server name.");
            return;
        }
        String inputPath = args[0];
        String outputPath = args[1];
        String serverName = args[2];

        PuzzleSolverClient solver =
        new PuzzleSolverClient(inputPath, outputPath, serverName);
    }
}
