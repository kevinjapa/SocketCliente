import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketCliente {
    
    public SocketCliente(){
        
    }
    
    public static void main(String[] args ,String mensajeE) {
        String servidorIP = "172.20.10.2";
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
    public static boolean conexion(String usuario){
        String servidorIP = "192.168.18.27";
        int servidorPuerto = 8080;
        try {
            Socket clientSocket = new Socket(servidorIP, servidorPuerto);
            System.out.println("Conectado al servidor en la Direccion:" + servidorIP + " Puerto:" + servidorPuerto);
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));//Enviamos los datos
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);//recibimos los datos
            out.println("Usuario "+usuario+" conectado");
            return true;
        } catch (IOException ex) {
            Logger.getLogger(SocketCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
            return false;
    }
    
    
    
    public static void prueba(String Mensaje){
        String servidorIP = "172.20.10.2";
        int servidorPuerto = 8080;
        String mensajeEnvio=Mensaje;

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
