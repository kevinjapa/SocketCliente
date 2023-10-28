import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;

public class SocketCliente {
    
    //String mensajeEnvio="";
    public SocketCliente(String mensajeEnvio){
        //this.mensajeEnvio=mensajeEnvio;
    }
    
    public static void main(String[] args ,String mensajeE) {
        String servidorIP = "172.16.209.213";
        int servidorPuerto = 8080;
        String mensajeEnvio=mensajeE;

        try {
            Socket clientSocket = new Socket(servidorIP, servidorPuerto);
            System.out.println("Conectado al servidor en la Direccion:" + servidorIP + " Puerto:" + servidorPuerto);

            // Aquí puedes manejar la comunicación con el servidor
            // Por ejemplo, puedes usar BufferedReader y PrintWriter para enviar y recibir datos.
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//Enviamos los datos
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);//recibimos los datos

            // Inicia un bucle para la comunicación continua
            while (true) {
                // Leer la entrada del usuario o realizar alguna lógica
                // Por ejemplo, puedes usar Scanner para leer la entrada del usuario
                Scanner scanner = new Scanner(System.in);
                System.out.print("Escribe un mensaje al servidor: ");
                String mensaje = mensajeEnvio;//scanner.nextLine();

                // Enviar el mensaje al servidor
                out.println(mensaje);

                // Recibir la respuesta del servidor
                String respuesta = in.readLine();
                System.out.println("Servidor dice: " + respuesta);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
