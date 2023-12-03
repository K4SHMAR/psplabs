import java.io.*;
import java.net.Socket;
import java.util.Map;

public class ServerThread extends Thread {
    private final Socket clientSocket;
    private final Map<String, String> scheduleData;

    public ServerThread(Socket clientSocket, Map<String, String> scheduleData) {
        this.clientSocket = clientSocket;
        this.scheduleData = scheduleData;
    }

    @Override
    public void run() {
        try {
            ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
            ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

            while (true) {
                // Принимаем запрос от клиента
                String request = (String) in.readObject();
                String response = processRequest(request);

                // Отправляем ответ клиенту
                out.writeObject(response);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private String processRequest(String request) {
        // Обработка запроса и взаимодействие с данными о расписании
        // В данном примере просто возвращаем расписание по дню недели
        return scheduleData.getOrDefault(request, "Данные отсутствуют");
    }
}