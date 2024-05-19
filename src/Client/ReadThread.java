package Client;

import DES_Implementimi.DES;

import java.io.*;
import java.net.*;

/*
 * Ky thread ka per detyre qe te lexoj sinjalin nga serveri dhe e
 * shfaq ate ne server
 * Ekzekutohet vazhdimisht deri sa klienti te dekonektohet nga serveri
 */

public class ReadThread extends Thread {
    private BufferedReader reader;
    private Socket socket;
    private ChatClient client;

    public ReadThread(Socket socket, ChatClient client) {
        this.socket = socket;
        this.client = client;

        try {
            InputStream input = socket.getInputStream();
            reader = new BufferedReader(new InputStreamReader(input));
        } catch (IOException ex) {
            System.out.println("Error getting input stream: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void run() {
        int i = 1;
        while (true) {
            try {

                String response = reader.readLine();
                if(i == 1){
                    System.out.println(response);
                    i++;
                } else {
                    String parts[] = response.split(":");
                    String mesazhi = (parts[1]);
                    String tekstiDekriptuar = DES.decryption(mesazhi, "FieK32*=1");
                    System.out.println("\n" + parts[0] + ":" + tekstiDekriptuar);
                }

            } catch (IOException ex) {
                System.out.println("Error ne lexim nga serveri: " + ex.getMessage());
                ex.printStackTrace();
                break;
            }
        }
    }
}