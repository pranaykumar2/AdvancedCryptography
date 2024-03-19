import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

class P2P {
    private static final int PORT = 8083;
    private static List<PrintWriter> writers = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);

            Thread acceptThread = new Thread(() -> {
                try {
                    while (true) {
                        Socket socket = serverSocket.accept();
                        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
                        writers.add(writer);

                        Thread messageThread = new Thread(() -> handleMessages(socket));
                        messageThread.start();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            acceptThread.start();

            Thread inputThread = new Thread(() -> {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                    while (true) {
                        String input = br.readLine();
                        if (input != null) {
                            broadcast("[Terminal " + (writers.size() + 1) + "]: " + input);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            inputThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleMessages(Socket socket) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String message;
            while ((message = reader.readLine()) != null) {
                System.out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcast(String message) {
        for (PrintWriter writer : writers) {
            writer.println(message);
        }
    }
}
