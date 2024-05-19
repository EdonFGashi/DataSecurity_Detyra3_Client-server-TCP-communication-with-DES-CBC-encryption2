package Client;

import DES_Implementimi.DES;

import java.io.Console;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/*
 * Ky thread eshte i pergjegjshem qe te lexoj mesazhin qe jep klienti
 * dhe e dergon ate ne server
 * Ekzekutohet ne nje infinit loop deri sa klienti e shkruan 'dil'
 * */

public class WriteThread extends Thread{
    private PrintWriter writer;
    private Socket socket;
    private ChatClient client;

    public WriteThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);
        } catch (IOException ex) {
            System.out.println("Error getting output stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {

        Console console = System.console();

        String userName = console.readLine("\nShkruani emrin tuaj: ");
        client.setUserName(userName);
        writer.println(userName);

        String text;

        do {
            text = console.readLine();

            String tekstiEnktipruar = DES.encryption(text, "FieK32*=1");
            writer.println(tekstiEnktipruar);

        } while (!text.equals("dil"));

        try {
            socket.close();
        } catch (IOException ex) {

            System.out.println("Error gjat shkrimit ne server: " + ex.getMessage());
        }
    }
}
