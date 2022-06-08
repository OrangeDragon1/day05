package vttp2022.day05.client;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientMain {

    public static void main(String[] args) throws IOException {
        // Connect to the server
        // e.g. 127.0.0.1 - localhost

        System.out.println("Connecting to localhost at port 3000...");
        Socket sock = new Socket("127.0.0.1", 3000);
        System.out.println("Connected.");
        Boolean stop = false;
        String input;
        String response;
        // Get the input and output stream - bytes
        // Get the input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        // Get the output stream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);
        
        while (!stop) {
        // Get input from user
        Console cons = System.console(); 
        input = cons.readLine("Say something to the server: ");
        if (!input.equalsIgnoreCase("end")) {
        
            // Write to server
            dos.writeUTF(input);
            dos.flush();

            // Wait for response from server
            response = dis.readUTF();
            response = response.toUpperCase();
            System.out.printf("Response from server: %s\n", response);
            } else {
                stop = true;
                dos.writeUTF("Connection ending.");
                dos.flush();
            }
        
        }

        // close the streams and socket
        is.close();
        os.close();
        sock.close();

    }
    
}
