package com.nexus;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class App {
    public static void main(String[] args) throws IOException {
        int port = 8080;
        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        server.createContext("/", new HttpHandler() {
            @Override
            public void handle(HttpExchange exchange) throws IOException {
                String html = "<html><body style='background:#f2f4f4; text-align:center; padding-top:90px; font-family:Arial;'>" +
                              "<h1 style='color:#117a65;'>🌟 Project AWS-Nexus is Live!</h1>" +
                              "<p style='color:#2e4053; font-size:20px;'>Built via <b>Maven & SonarQube</b>, provisioned via <b>Terraform</b>, and deployed via <b>Ansible</b>.</p>" +
                              "</body></html>";
                exchange.sendResponseHeaders(200, html.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(html.getBytes());
                os.close();
            }
        });
        server.setExecutor(null);
        server.start();
        System.out.println("AWS-Nexus server running on port " + port);
    }
}
