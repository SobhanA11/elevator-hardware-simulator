package com.syleera.sobhana11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Main {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 8080;

    public static void main(String[] args) {
        System.out.println("===============================");
        System.out.println("    ELEVATOR SYSTEM CLIENT");
        System.out.println("===============================");

        try (
                Socket socket = new Socket(HOST, PORT);

                BufferedReader input = new BufferedReader(
                        new InputStreamReader(socket.getInputStream())
                );

                PrintWriter output = new PrintWriter(
                        socket.getOutputStream(), true
                )
        ) {

            System.out.println("Connected to server!");
            System.out.println("Client IP: " + socket.getLocalAddress().getHostAddress());
            System.out.println("Client Port: " + socket.getLocalPort());

            System.out.println("\nType a message (exit to disconnect).\n");

            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("> ");

                String message = scanner.nextLine();

                // Send message to server
                output.println(message);

                // Receive server response
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
