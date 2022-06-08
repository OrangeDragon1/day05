package vttp2022.day05.server;

import java.io.Console;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) throws IOException {

        // Crate a server socket and listen to a port (Port must be free)
        ServerSocket server = new ServerSocket(3000);

        System.out.println("Waiting for connection...");
        // Wait for incoming client connection
        Socket sock = server.accept();
        System.out.println("Connection accepted.");
        String input;
        Boolean stop = false;
        String response;

        // Get the input and output stream - bytes
        // Get the input stream
        InputStream is = sock.getInputStream();
        DataInputStream dis = new DataInputStream(is);

        // Get the output stream
        OutputStream os = sock.getOutputStream();
        DataOutputStream dos = new DataOutputStream(os);

        
        response = dis.readUTF();
        response = response.toUpperCase();
        System.out.printf("Request from client: %s\n", response);

        while (!stop) {
        // Get input from server
        Console cons = System.console();
        
        if (!response.equalsIgnoreCase("Connection ending.")) {

            input = cons.readLine("Say something to the client:");
            // Write back the data to the client
            dos.writeUTF(input);
            dos.flush();

            response = dis.readUTF();
            response = response.toUpperCase();
            System.out.printf("Request from client: %s\n", response);
            } else {
                stop = true;
                //response = dis.readUTF();
                //response = response.toUpperCase();
                //System.out.printf(">> %s.\n", response);
            }
        }
        
        // close the streams and socket
        is.close();
        os.close();
        sock.close();
    }
    
    
}
