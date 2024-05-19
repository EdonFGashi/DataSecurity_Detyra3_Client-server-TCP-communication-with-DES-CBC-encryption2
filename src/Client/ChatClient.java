package Client;

import java.net.*;
import java.io.*;

/*
 * Programi i bisedes se klientit
 * Shkruani 'dil' per te dale nga biseda
 */

public class ChatClient {
    private String hostname;
    private int port;
    private String userName;

    public ChatClient(String hostname, int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public void execute() {
        try {
            Socket socket = new Socket(this.hostname, this.port);
            System.out.println("Connected to the chat server");
            (new ReadThread(socket, this)).start();
            (new WriteThread(socket, this)).start();
        } catch (UnknownHostException var2) {
            UnknownHostException ex = var2;
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException var3) {
            IOException ex = var3;
            System.out.println("I/O Error: " + ex.getMessage());
        }

    }

    void setUserName(String userName) {
        this.userName = userName;
    }

    String getUserName() {
        return this.userName;
    }

    public static void main(String[] args) {
        if (args.length >= 2) {
            String hostname = args[0];
            int port = Integer.parseInt(args[1]);
            ChatClient client = new ChatClient(hostname, port);
            client.execute();
        }
    }
}