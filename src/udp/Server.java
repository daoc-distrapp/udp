
package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class Server {
    public static final String DIRECCION  = "localhost";
    public static final int PUERTO = 9595;
    public static final int TALLA_BUFFER = 256;
    public static final String END_OF_MSGS  = "EXIT";

    public static void main(String[] args) throws Exception {
        //Crea el socket servidor
        DatagramSocket socket = new DatagramSocket(Server.PUERTO);
        byte[] buffer;
        System.out.println("UDP SERVER");
        while(true) {
            //Crea el buffer para guardar los datos que se reciban
            buffer = new byte[Server.TALLA_BUFFER];
            //Crea un datagrama con el buffer vacío para recibir los datos
            DatagramPacket packet = new DatagramPacket(buffer, Server.TALLA_BUFFER);
            //Espera que llegue un datagrama y lo copia en el datagrama creado
            socket.receive(packet);
            System.out.println("XYZ");
            //presenta la infomación recibida
            String carga = new String(packet.getData()).trim();
            System.out.println(
                String.format("Recibido: %s; Desde: %s", carga, packet.getSocketAddress()));

            if(carga.equals(Server.END_OF_MSGS)) break;
        }
        
        socket.close();
    }
    
}
