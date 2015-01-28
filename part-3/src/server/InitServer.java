import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class InitServer {
    private static final String REMOTE_OBJ = "PuzzleSolver";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Argument list is empty. Need server name.");
            return;
        }

        String uri = String.format("rmi://%s/%s", args[0], REMOTE_OBJ);
        try {
            PuzzleSolverServer s = new PuzzleSolverServer();
            Naming.rebind(uri, s);
        } catch(RemoteException e) {
            System.out.println(e.getMessage());
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
