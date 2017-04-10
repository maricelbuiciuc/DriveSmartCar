package Services;

import Model.Lights;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Marcy
 */
public class LightsService implements Runnable {

    public LightsService() {
    }

    public static String turnOnLights() {
        boolean LightsAreON = true;
        Lights engine = new Lights(LightsAreON);

        Gson gson = new Gson();
        String json = gson.toJson(engine);
        return json;
    }

    @Override
    public void run() {

        try {

            ServerSocket listener = new ServerSocket(9090);
            try {

                // Create a JmDNS instance
                JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

                // Register a service
                ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "Audio Service", 9090, "can't be empty?");
                jmdns.registerService(serviceInfo);
                System.out.println("Lights Service is registered");

                //print message
                while (true) {
                    Socket socket = listener.accept();
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("Lights are on!!!");
                    } finally {
                        socket.close();
                    }
                }
            } finally {
                listener.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(EngineService.class.getName()).log(Level.SEVERE, null, ex);
        }

        throw new UnsupportedOperationException("Not supported yet.");
    }
}
