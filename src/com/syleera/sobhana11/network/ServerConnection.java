package com.syleera.sobhana11.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ServerConnection {
    private final String host;
    private final int port;

    public ServerConnection(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void connect() {
        try (
                Socket socket = new Socket(host, port);

                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                PrintWriter output = new PrintWriter(
                        socket.getOutputStream(), true
                );

                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("===============================");
            System.out.println("    ELEVATOR SYSTEM CLIENT");
            System.out.println("===============================");

            System.out.println("Connected to server!");

            System.out.println(
                    "Client IP: " +
                    socket.getLocalAddress().getHostAddress()
            );

            System.out.println(
                    "Client Port: " + socket.getLocalPort()
            );

            System.out.println("\nType a message (exit to disconnect).\n");

            while (true) {
                System.out.print("> ");

                String message = scanner.nextLine();

                output.println(message);

                String response = input.readLine();

                if (response == null) {
                    System.out.println("Server disconnected!");
                    break;
                }

                System.out.println(response);

                if (message.equalsIgnoreCase("exit")) {
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("Could not connect to server!");
            System.err.println(e.getMessage());
        }

        System.out.println("Client stopped.");
    }
}
