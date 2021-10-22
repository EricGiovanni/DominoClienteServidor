import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(8000);
            Socket cliente = servidor.accept();

            System.out.println("Se ha establecido la conexión");

            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            String mensaje = (String) entrada.readUTF();
            Domino d = new Domino("Servidor", mensaje);

            DataOutputStream respuesta = new DataOutputStream(cliente.getOutputStream());
            String menucito = mostrarMenu(d);
            respuesta.writeUTF(menucito);

            int operacionDeseada = entrada.readInt();
            System.out.println("Esto es lo que quiere el cliente: " + operacionDeseada);

            solicitaOperacion(operacionDeseada, cliente, servidor, entrada, d);
        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static String mostrarMenu(Domino d) {
        String s = "Servidor: \n";
        s += "Si desea salir presione -1\n Si desea comer presione -2\n";
        s += d.getJugador2().toString();
        return s;
    }

    public static void solicitaOperacion(int op, Socket cn, ServerSocket serv, DataInputStream entrada, Domino d) {
        try {
            int suma = 0;
            int resta = 0;
            int mult = 1;
            if (op == -1) {
                serv.close();
            } else if (op == -2) {

            } else {
                // Le decimos al cliente que meta el primer valor de la op
                DataOutputStream pdSuma = new DataOutputStream(cn.getOutputStream());
                pdSuma.writeUTF("Dame el primer digito");

                // Leemos el primer digito de una suma
                int digito1 = entrada.readInt();
                suma = suma + digito1;

                // Le decimos al cliente que meta el segundo valor de la operacion
                DataOutputStream sdSuma = new DataOutputStream(cn.getOutputStream());
                sdSuma.writeUTF("Dame el segundo digito");

                // Leemos el segundo digito de la suma
                int digito2 = (int) entrada.readInt();
                suma = suma + digito2;

                // Le decimos al cliente la respuesta
                DataOutputStream respuestaSuma = new DataOutputStream(cn.getOutputStream());
                respuestaSuma.writeUTF("La suma es:" + suma);

                // Esto lo lee el servidor, en esta caso se lee el saludo
                DataInputStream entradaNS = new DataInputStream(cn.getInputStream());
                String mensajeNS = entradaNS.readUTF();
                System.out.println("Este es el mensaje del cliente: " + mensajeNS);

                // Le mostramos al cliente las operaciones que puede hacer
                DataOutputStream respuestaNS = new DataOutputStream(cn.getOutputStream());
                String menucitoNS = mostrarMenu(d);
                respuestaNS.writeUTF(menucitoNS);

                // Leemos la opcion de la operacion que quiere hacer el usuario
                int operacionDeseadaNS = entrada.readInt();
                System.out.println("Esto es lo que quiere el cliente: " + operacionDeseadaNS);

                solicitaOperacion(operacionDeseadaNS, cn, serv, entrada, d);
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
