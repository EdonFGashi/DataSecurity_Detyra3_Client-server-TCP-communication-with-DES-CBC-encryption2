package Server;


import java.io.*;
import java.net.Socket;

///**
// * Ky thread trajton lidhjen per secilin perdorues te lidhur
// * mund te mbaje te lidhur disa perdorues ne te njejten kohe
// */
public class UserThread extends Thread{
    private Socket socket;
    private ChatServer server;
    private PrintWriter writer;

    public UserThread(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    public void run() {
        try {
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            OutputStream output = socket.getOutputStream();
            writer = new PrintWriter(output, true);

            String userName = reader.readLine();
            server.addUserName(userName);

            String serverMessage = "Nje perdorues i ri u kyq:" + userName;
            server.broadcast(serverMessage, this);

            String clientMessage;

            do {
                clientMessage = reader.readLine();
                serverMessage = "[" + userName + "]:" + clientMessage;
//                String parts[] = serverMessage.split(":");
//                String mesazhi = (parts[1]);
                server.broadcast(serverMessage, this);

            } while (!clientMessage.equals("dil"));

            server.removeUser(userName, this);
            socket.close();

            serverMessage = userName + " u largua.";
            server.broadcast(serverMessage, this);

        } catch (IOException ex) {
            System.out.println("Error ne Server.UserThread: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    /**
     * Dergon mesazh tek klientiy
     */
    void sendMessage(String message) {
        writer.println(message);
    }
}
