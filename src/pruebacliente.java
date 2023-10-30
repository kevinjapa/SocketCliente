import java.io.*;
import java.net.*;
import java.util.Scanner;

public class pruebacliente {
    private String userName;
    
    public pruebacliente(String userName) {
        this.userName = userName;
    }
    
    public void start() {
        Socket socket = null;
        final BufferedReader reader;
        final PrintWriter writer;

        try {
            socket = new Socket("localhost", 8080);

            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Conectado al servidor. Escribe 'exit' para salir.");
            
            writer.println(userName); // Enviar el nombre de usuario al servidor

            Thread receiveThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String serverResponse;
                        while ((serverResponse = reader.readLine()) != null) {
                            System.out.println(serverResponse);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiveThread.start();

            String userInputLine;
            while (true) {
                userInputLine = userInput.readLine();
                if (userInputLine.equalsIgnoreCase("exit")) {
                    break;
                }
                writer.println(userInputLine);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public static void main(String[] args) {
        System.out.print("Ingresa tu nombre de usuario: ");
        Scanner scanner = new Scanner(System.in);
        String userName = scanner.nextLine();
        pruebacliente client = new pruebacliente(userName);
        client.start();
    }
}
