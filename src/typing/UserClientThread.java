/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package typing;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *
 * @author SAURABHHH
 */
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;

public class UserClientThread extends Thread {
    private Socket socket = null;
    private User client = null;
    private DataInputStream streamIn = null;

    public UserClientThread(User _client, Socket _socket) {
        client = _client;
        socket = _socket;
        open();
        start();
    }

  

    public void open() {
        try {
            streamIn = new DataInputStream(socket.getInputStream());
        } catch (IOException ioe) {
            System.out.println("Error getting input stream: " + ioe);
            client.stop();
        }
    }

    public void close() {
        try {
            if (streamIn != null) {
                streamIn.close();
            }
        } catch (IOException ioe) {
            System.out.println("Error closing input stream: " + ioe);
        }
    }

    @Override
    public void run() {
        
        
            try {
                client.handle(streamIn.readUTF());
            } catch (IOException ioe) {
                System.out.println("Listening error: " + ioe.getMessage());
                client.stop();
            }
           
        
    }
   
        
                //convert string message into integer 
    
}
