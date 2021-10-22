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
            System.out.println("Este es el mensaje del cliente: " + mensaje);

            /**** ESCRIBIMOS ****/
            // Le mostramos al cliente las operaciones que puede hacer
            DataOutputStream respuesta = new DataOutputStream(cliente.getOutputStream());
            String menucito = mostrarMenu();
            respuesta.writeUTF(menucito);

            /**** LEEEMOS *****/
            // Leemos la opcion de la operacion que quiere hacer el usuario
            int operacionDeseada = entrada.readInt();
            System.out.println("Esto es lo que quiere el cliente: " + operacionDeseada);

            solicitaOperacion(operacionDeseada, cliente, servidor, entrada);
        } catch (IOException e) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static String mostrarMenu() {
        return "Servidor: \nTeclea una opcion:\n1. Suma\n2. Resta\n3. Multiplicacion\n4. Salir";
    }

    public static void solicitaOperacion(int op, Socket cn, ServerSocket serv, DataInputStream entrada) {
        try {
            int suma = 0;
            int resta = 0;
            int mult = 1;
            if (op == 4) {
                // cn.close();
                serv.close();
            } else {
                // Hacemos una suma
                /**** ESCRIBIMOS *****/
                // Le decimos al cliente que meta el primer valor de la op
                DataOutputStream pdSuma = new DataOutputStream(cn.getOutputStream());
                pdSuma.writeUTF("Dame el primer digito");

                /**** LEEMOS ****/
                // Leemos el primer digito de una suma
                int digito1 = entrada.readInt();
                suma = suma + digito1;

                /**** ESCRIBIMOS *****/
                // Le decimos al cliente que meta el segundo valor de la operacion
                DataOutputStream sdSuma = new DataOutputStream(cn.getOutputStream());
                sdSuma.writeUTF("Dame el segundo digito");

                /**** LEEMOS ****/
                // Leemos el segundo digito de la suma
                int digito2 = (int) entrada.readInt();
                suma = suma + digito2;

                /**** ESCRIBIMOS *****/
                // Le decimos al cliente la respuesta
                DataOutputStream respuestaSuma = new DataOutputStream(cn.getOutputStream());
                respuestaSuma.writeUTF("La suma es:" + suma);

                // VOLVEMOS A CARGAR DATOS
                /***** LEEMOS ***/
                // Esto lo lee el servidor, en esta caso se lee el saludo
                DataInputStream entradaNS = new DataInputStream(cn.getInputStream());
                String mensajeNS = entradaNS.readUTF();
                System.out.println("Este es el mensaje del cliente: " + mensajeNS);

                /**** ESCRIBIMOS ****/
                // Le mostramos al cliente las operaciones que puede hacer
                DataOutputStream respuestaNS = new DataOutputStream(cn.getOutputStream());
                String menucitoNS = mostrarMenu();
                respuestaNS.writeUTF(menucitoNS);

                /**** LEEEMOS *****/
                // Leemos la opcion de la operacion que quiere hacer el usuario
                int operacionDeseadaNS = entrada.readInt();
                System.out.println("Esto es lo que quiere el cliente: " + operacionDeseadaNS);

                solicitaOperacion(operacionDeseadaNS, cn, serv, entrada);
                if (op == 1) {
                    /**** ESCRIBIMOS *****/
                    // Le decimos al cliente que meta el primer valor de la op
                    DataOutputStream pdSuma = new DataOutputStream(cn.getOutputStream());
                    pdSuma.writeUTF("Dame el primer digito");

                    /**** LEEMOS ****/
                    // Leemos el primer digito de una suma
                    int digito1 = entrada.readInt();
                    suma = suma + digito1;

                    /**** ESCRIBIMOS *****/
                    // Le decimos al cliente que meta el segundo valor de la operacion
                    DataOutputStream sdSuma = new DataOutputStream(cn.getOutputStream());
                    sdSuma.writeUTF("Dame el segundo digito");

                    /**** LEEMOS ****/
                    // Leemos el segundo digito de la suma
                    int digito2 = (int) entrada.readInt();
                    suma = suma + digito2;

                    /**** ESCRIBIMOS *****/
                    // Le decimos al cliente la respuesta
                    DataOutputStream respuestaSuma = new DataOutputStream(cn.getOutputStream());
                    respuestaSuma.writeUTF("La suma es:" + suma);

                    // VOLVEMOS A CARGAR DATOS
                    /***** LEEMOS ***/
                    // Esto lo lee el servidor, en esta caso se lee el saludo
                    DataInputStream entradaNS = new DataInputStream(cn.getInputStream());
                    String mensajeNS = entradaNS.readUTF();
                    System.out.println("Este es el mensaje del cliente: " + mensajeNS);

                    /**** ESCRIBIMOS ****/
                    // Le mostramos al cliente las operaciones que puede hacer
                    DataOutputStream respuestaNS = new DataOutputStream(cn.getOutputStream());
                    String menucitoNS = mostrarMenu();
                    respuestaNS.writeUTF(menucitoNS);

                    /**** LEEEMOS *****/
                    // Leemos la opcion de la operacion que quiere hacer el usuario
                    int operacionDeseadaNS = entrada.readInt();
                    System.out.println("Esto es lo que quiere el cliente: " + operacionDeseadaNS);

                    solicitaOperacion(operacionDeseadaNS, cn, serv, entrada);
                } // termina op==1

                // Es una resta
                if (op == 2) {
                    /**** ESCRIBIMOS *****/
                    // Le decimos al cliente que meta el primer valor de la op
                    DataOutputStream pdR = new DataOutputStream(cn.getOutputStream());
                    pdR.writeUTF("Dame el primer digito");

                    /**** LEEMOS ****/
                    // Leemos el primer digito de una suma
                    int digito1R = entrada.readInt();
                    resta = resta + digito1R;

                    /**** ESCRIBIMOS *****/
                    // Le decimos al cliente que meta el segundo valor de la operacion
                    DataOutputStream sdR = new DataOutputStream(cn.getOutputStream());
                    sdR.writeUTF("Dame el segundo digito");

                    /**** LEEMOS ****/
                    // Leemos el segundo digito de la resta
                    int digito2R = entrada.readInt();
                    resta = resta - digito2R;

                    /**** ESCRIBIMOS *****/
                    // Le decimos al cliente la respuesta
                    DataOutputStream respuestaResta = new DataOutputStream(cn.getOutputStream());
                    respuestaResta.writeUTF("La resta es:" + resta);

                    // VOLVEMOS A CARGAR DATOS
                    /***** LEEMOS ***/
                    // Esto lo lee el servidor, en esta caso se lee el saludo
                    DataInputStream entradaNR = new DataInputStream(cn.getInputStream());
                    String mensajeNR = entradaNR.readUTF();
                    System.out.println("Este es el mensaje del cliente: " + mensajeNR);

                    /**** ESCRIBIMOS ****/
                    // Le mostramos al cliente las operaciones que puede hacer
                    DataOutputStream respuestaNR = new DataOutputStream(cn.getOutputStream());
                    String menucitoNR = mostrarMenu();
                    respuestaNR.writeUTF(menucitoNR);

                    /**** LEEEMOS *****/
                    // Leemos la opcion de la operacion que quiere hacer el usuario
                    int operacionDeseadaNR = entrada.readInt();
                    System.out.println("Esto es lo que quiere el cliente: " + operacionDeseadaNR);

                    solicitaOperacion(operacionDeseadaNR, cn, serv, entrada);
                }
                // es una multiplicaci√≥n
                if (op == 3) {
                    /**** ESCRIBIMOS *****/
                    // Le decimos al cliente que meta el primer valor de la op
                    DataOutputStream pdM = new DataOutputStream(cn.getOutputStream());
                    pdM.writeUTF("Dame el primer digito");

                    /**** LEEMOS ****/
                    // Leemos el primer digito de una suma
                    int digito1M = entrada.readInt();
                    mult = mult * digito1M;

                    /**** ESCRIBIMOS *****/
                    // Le decimos al cliente que meta el segundo valor de la operacion
                    DataOutputStream sdM = new DataOutputStream(cn.getOutputStream());
                    sdM.writeUTF("Dame el segundo digito");

                    /**** LEEMOS ****/
                    // Leemos el segundo digito de la suma
                    int digito2M = entrada.readInt();
                    mult = mult * digito2M;

                    /**** ESCRIBIMOS *****/
                    // Le decimos al cliente la respuesta
                    DataOutputStream respuestaMultiplicacion = new DataOutputStream(cn.getOutputStream());
                    respuestaMultiplicacion.writeUTF("La multiplicacion es:" + mult);

                    // VOLVEMOS A CARGAR DATOS
                    /***** LEEMOS ***/
                    // Esto lo lee el servidor, en esta caso se lee el saludo
                    DataInputStream entradaNM = new DataInputStream(cn.getInputStream());
                    String mensajeNM = (String) entradaNM.readUTF();
                    System.out.println("Este es el mensaje del cliente: " + mensajeNM);

                    /**** ESCRIBIMOS ****/
                    // Le mostramos al cliente las operaciones que puede hacer
                    DataOutputStream respuestaNM = new DataOutputStream(cn.getOutputStream());
                    String menucitoNM = mostrarMenu();
                    respuestaNM.writeUTF(menucitoNM);

                    /**** LEEEMOS *****/
                    // Leemos la opcion de la operacion que quiere hacer el usuario
                    int operacionDeseadaNM = entrada.readInt();
                    System.out.println("Esto es lo que quiere el cliente: " + operacionDeseadaNM);

                    solicitaOperacion(operacionDeseadaNM, cn, serv, entrada);
                }
            }
            /*
             * } catch(ClassNotFoundException ex) {
             * Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
             */
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }// termina solicitaOp
}
