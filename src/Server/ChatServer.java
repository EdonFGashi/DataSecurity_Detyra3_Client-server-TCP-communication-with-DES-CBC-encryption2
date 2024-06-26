package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class ChatServer {
    private int port;
    private Set<String> userNames = new HashSet<>();
    private Set<UserThread> userThreads = new HashSet<>();

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Syntax: java Server.ChatServer <port-number>");
            System.exit(0);
        }

        int port = Integer.parseInt(args[0]);

        ChatServer server = new ChatServer(port);
        server.execute();
    }

    public ChatServer(int port) {
        this.port = port;
    }

    public void execute() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {

            System.out.println("Serveri po pret sinjal ne portin " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Nje perdorues u kyq");

                UserThread newUser = new UserThread(socket, this);
                userThreads.add(newUser);
                newUser.start();

            }

        } catch (IOException ex) {
            System.out.println("Error ne server: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * Dergo mesazh nga njeri tek gjith te tjeret (broadcasting)
     */
    void broadcast(String message, UserThread excludeUser) {
        for (UserThread aUser : userThreads) {
            if (aUser != excludeUser) {
                aUser.sendMessage(message);
            }
        }
        System.out.println(message);
    }

    /**
     * E ruan emrin e perdoruesit te ri te kyqur
     */
    void addUserName(String userName) {
        userNames.add(userName);
    }

    /**
     * Kur nje klient largohet i fshihet emri dhe objekti i tij nga lista e usereve
     */
    void removeUser(String userName, UserThread aUser) {
        boolean removed = userNames.remove(userName);
        if (removed) {
            userThreads.remove(aUser);
            System.out.println("Perdoruesi " + userName + " u largua");
        }
    }

    Set<String> getUserNames() {
        return this.userNames;
    }

    /**
     * Kthen true nese ka perdorues tjere te lidhur
     */
    boolean hasUsers() {
        return !this.userNames.isEmpty();
    }

}
