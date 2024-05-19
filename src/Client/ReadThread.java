package Client;

import DES_Implementimi.DES;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class ReadThread {
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
