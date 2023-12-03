import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Map;

public class Server {
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Сервер запущен на порту " + PORT);

            Map<String, String> scheduleData = new HashMap<>();
            // Инициализация данных о расписании
            scheduleData.put("понедельник", "Математика, Физика");
            scheduleData.put("вторник", "Информатика, Английский");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Новое подключение: " + clientSocket);

                ServerThread clientThread = new ServerThread(clientSocket, scheduleData);
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}