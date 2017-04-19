package Services;

import Model.Door;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcy
 */
public class DoorService implements Runnable {

    public DoorService() {
    }

    public static String unlockTheDoor() {
        boolean DoorIsOpen = true;
        Door door = new Door(DoorIsOpen);

        Gson gson = new Gson();
        String json = gson.toJson(door);
        return json;
    }

    @Override
    public void run() {

        try {

            ServerSocket listener = new ServerSocket(7070);
            try {

                // This line of code shows how to create a JmDNS instance
                JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

                // Here is the code for the registration of the service
                ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "Door Service", 7070, "can't be empty?");
                jmdns.registerService(serviceInfo);
                System.out.println("Door Service is registered");
                

                // Here we have the code for printing the message
                while (true) {
                    Socket socket = listener.accept();
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("Doors are open!!!");
                    } finally {
                        socket.close();
                    }
                }
            } finally {
                listener.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(DoorService.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
