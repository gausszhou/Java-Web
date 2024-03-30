package com.example.web.lesson01;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * http://localhost:8080
 */
public class MyServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("[debug] server is running");
        while (true) {
            Socket sock = serverSocket.accept();
            System.out.println("[debug] connected from" + sock.getRemoteSocketAddress());
            Thread t = new Thread(new MyServerHandler(sock));
            t.start();
        }
    }
}
