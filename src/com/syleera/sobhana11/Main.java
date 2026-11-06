package com.syleera.sobhana11;

import com.syleera.sobhana11.network.ServerConnection;

public class Main {

    public static void main(String[] args) {
        ServerConnection connection =
                new ServerConnection("127.0.0.1", 8080);

        connection.connect();
    }
}
