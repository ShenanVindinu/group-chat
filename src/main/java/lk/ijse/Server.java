package lk.ijse;

import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.ArrayList;

public class Server {

    public static void main(String[] args) {
        ArrayList<ServerThread> threadList = new ArrayList<>();
        ServerSocket serversocket;
        try {
            serversocket = new ServerSocket(3991);
            System.out.println("Server started");
            while(!serversocket.isClosed()) {
                Socket socket = serversocket.accept();
                ServerThread serverThread = new ServerThread(socket, threadList);
                threadList.add(serverThread);
                serverThread.start();

            }
        } catch (Exception e) {
            System.out.println("Error occured in main: " + e.getStackTrace());
        } finally {
            System.out.println("main");
        }
    }


}