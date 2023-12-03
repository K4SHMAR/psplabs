import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final String SERVER_IP = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);
            System.out.println("Подключено к серверу");

            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());

            Scanner scanner = new Scanner(System.in);

            while (true) {
                // Ввод запроса от пользователя
                System.out.print("Введите день недели (или 'exit' для выхода): ");
                String request = scanner.nextLine();

                if ("exit".equalsIgnoreCase(request)) {
                    break;
                }

                // Отправка запроса серверу
                out.writeObject(request);

                // Получение и вывод ответа от сервера
                String response = (String) in.readObject();
                System.out.println("Ответ сервера: " + response);
            }

            socket.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}