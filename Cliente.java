import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Cliente {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        int opcion = 0;
        try {
            System.out.print("Escribe tu nombre: ");
            String nombre = teclado.nextLine();

            Socket cliente = new Socket("localhost", 8000);

            DataOutputStream mensaje = new DataOutputStream(cliente.getOutputStream());
            mensaje.writeUTF(nombre);
            // Aqui el cliente lee el menu de operaciones
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            String mensaje2 = entrada.readUTF();
            System.out.println(mensaje2);
            // seleccionamos la operacion deseada y decimos al servidor que opcion queremos
            opcion = teclado.nextInt();
            mensaje.writeInt(opcion);
            hazOp(opcion, cliente, mensaje);

        } catch (IOException ex) {
            teclado.close();
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        teclado.close();
    }

    public static void hazOp(int opcion, Socket cliente, DataOutputStream mensaje) {
        Scanner teclado = new Scanner(System.in);
        int val1 = 0;
        int val2 = 0;
        try {
            System.out.println("La opcion actual es:" + opcion);
            if (opcion == -1) {
                cliente.close();
                System.out.println("Conexion cerrada");
            } else {

                // Aqui el cliente lee en su terminal que se le solicita primer digito
                DataInputStream entradaPDS = new DataInputStream(cliente.getInputStream());
                String mensajeDeSolicitarPDS = (String) entradaPDS.readUTF();
                System.out.println(mensajeDeSolicitarPDS);

                /**** ESCRIBIMOS ****/
                // Escribimos el primer valor de la operacion
                val1 = teclado.nextInt();
                mensaje.writeInt(val1);

                /**** LEEMOS ***/
                // Aqui el cliente lee en su terminal que se le solicita segundo digito
                DataInputStream entradaSDS = new DataInputStream(cliente.getInputStream());
                String mensajeDeSolicitarSDS = entradaSDS.readUTF();
                System.out.println(mensajeDeSolicitarSDS);

                /**** ESCRIBIMOS ****/
                // Escribimos el segundo valor de la operacion
                val2 = teclado.nextInt();
                mensaje.writeInt(val2);

                /**** LEEMOS ***/
                // El cliente lee el resulta de su operacion
                DataInputStream entrada4 = new DataInputStream(cliente.getInputStream());
                String respSuma = (String) entrada4.readUTF();
                System.out.println(respSuma);

                // VOLVEMOS A CARGAR DATOS
                /***** ESCRIBIMOS ****/
                // Mandamos saludo al servidor
                DataOutputStream mensajeNS = new DataOutputStream(cliente.getOutputStream());
                mensajeNS.writeUTF("Hola servidor");

                /*** LEEMOS ***/
                // Aqui el cliente lee el menu de operaciones
                DataInputStream entradaNS = new DataInputStream(cliente.getInputStream());
                String mensaje2NS = (String) entradaNS.readUTF();
                System.out.println(mensaje2NS);

                /**** ESCRIBIMOS ****/
                // seleccionamos la operacion deseada y decimos al servidor que opcion queremos
                int op = teclado.nextInt();
                mensaje.writeInt(op);

                hazOp(op, cliente, mensajeNS);
            }
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}