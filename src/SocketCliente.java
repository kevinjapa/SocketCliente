import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JTextArea;

public class SocketCliente extends javax.swing.JInternalFrame {
    //public Mensajes m;
    public ArrayList l=new ArrayList();
    private String userName;
    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;
    String Usuario;
    public JTextArea t;

    public SocketCliente(Mensajes m) {
        //this.m = m;
    }

    public SocketCliente() {
    }

    public boolean connectToServer() {
        try {
            socket = new Socket("localhost", 8080);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void sendUserNameToServer(String Usuario) {
        writer.println(userName);
    }
    public void startReceivingMessages(JTextArea t) {
        this.t=t;
        String capturedOutput;
        Thread receiveThread = new Thread(new Runnable() {
            @Override
            public void run() {
                String serverResponse;
                try {
                    while ((serverResponse = reader.readLine()) != null) {
                        // En lugar de imprimir en la consola, enviar el mensaje a la interfaz gráfica
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    PrintStream printStream = new PrintStream(outputStream);
                    PrintStream originalOut = System.out;
                    // Establece la nueva salida estándar
                    System.setOut(printStream);
                    // Realiza la operación que genera un mensaje en System.out.println
                    System.out.println(serverResponse);
                    // Restablece la salida estándar original
                    System.setOut(originalOut);
                    // Convierte el contenido del búfer en una cadena (String)
                    String capturedOutput = outputStream.toString();
                    l.add(capturedOutput);
                    String formattedList = String.join(" ", l);
                    t.setText("");
                    t.setText(formattedList);                        
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        receiveThread.start();
    }

    public void handleUserInput(String mensaje, boolean es) {
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        //System.out.println("Conectado al servidor. Escribe 'exit' para salir.");
        String userInputLine;
        while (es==true) {
            userInputLine = mensaje;
            if (userInputLine.equalsIgnoreCase("exit")) {
                break;
            }
            writer.println(userInputLine);
            es=false;
        }
    }

    private void closeConnection() {
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
    
}
