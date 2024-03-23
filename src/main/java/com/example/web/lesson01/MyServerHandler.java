package com.example.web.lesson01;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class MyServerHandler implements Runnable {
    Socket sock;

    public MyServerHandler(Socket sock) {
        this.sock = sock;
    }

    @Override
    public void run() {
        try (InputStream input = this.sock.getInputStream()) {
            try (OutputStream output = this.sock.getOutputStream()) {
                handle(input, output);
            }
        } catch (Exception e) {
        } finally {
            try {
                this.sock.close();
            } catch (IOException ioe) {
            }
            System.out.println("[debug] client disconnected.");
        }
    }

    private void handle(InputStream input, OutputStream output) throws IOException {
        System.out.println("[debug] Process new http request...");
        BufferedReader reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
        boolean requestOk = false;
        String first = reader.readLine();
        System.out.println("```http");
        System.out.println(first);
        if (first.startsWith("GET / HTTP/1.") || first.startsWith("GET /favicon.ico HTTP/1.")) {
            requestOk = true;
        }
        for (; ; ) {
            String header = reader.readLine();
            if (header.isEmpty()) { // 读取到空行时, HTTP Header读取完毕
                break;
            }
            System.out.println(header);
        }
        System.out.println("```");
        System.out.println(requestOk ? "[debug] Response OK" : "[debug] Response Error");
        String data;// 空行标识Header和Body的分隔
        int length;
        if (!requestOk) {
            // 发送错误响应:
            data = "<html><body><h1>404 Not Found</h1></body></html>";
            writer.write("HTTP/1.1 404 Not Found\r\n");
        } else {
            // 发送成功响应:
            data = "<html><body><h1>Hello, world!</h1></body></html>";
            writer.write("HTTP/1.1 200 OK\r\n");
        }
        length = data.getBytes(StandardCharsets.UTF_8).length;
        writer.write("Connection: close\r\n");
        writer.write("Content-Type: text/html\r\n");
        writer.write("Content-Length: " + length + "\r\n");
        writer.write("\r\n");
        writer.write(data);
        writer.flush();
    }
}
