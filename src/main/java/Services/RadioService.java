package Services;

import Model.Radio;
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
public class RadioService implements Runnable {

    public RadioService() {
    }

    public static String turnRadioOn() {
        boolean RadioIsON = true;
        Radio radio = new Radio(RadioIsON);

        Gson gson = new Gson();
        String json = gson.toJson(radio);
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
                ServiceInfo serviceInfo = ServiceInfo.create("_http._tcp.local.", "Radio Service", 7070, "can't be empty?");
                jmdns.registerService(serviceInfo);
                System.out.println("Radio Service is registered");

                //Here we have the code for printing the message
                while (true) {
                    Socket socket = listener.accept();
                    try {
                        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
                        out.println("Radio is on!!!");
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
