
package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * https://github.com/dordonez-ute-apdist/udp
 * @author dordonez@ute.edu.ec
 */
public class Server {
    public static final String DIRECCION  = "localhost";
    public static final int PUERTO = 9595;
    public static final int TALLA_BUFFER = 256;
    public static final String MSJ_FIN  = "EXIT";

    public static void main(String[] args) throws Exception {
        System.out.println("UDP SERVER");
        
        //Crea el socket servidor
        DatagramSocket socket = new DatagramSocket(Server.PUERTO);
        System.out.println("El servidor se reserva el puerto: " + socket.getLocalPort());
        
        while(true) {
            //Crea el buffer para guardar los datos que se reciban
        	byte[] buffer = new byte[Server.TALLA_BUFFER];
        	
            //Crea un datagrama con el buffer vacío para recibir los datos
            DatagramPacket packet = new DatagramPacket(buffer, Server.TALLA_BUFFER);
            
            //Espera que llegue un datagrama y lo copia en el datagrama creado
            socket.receive(packet);
            
            //presenta la infomación recibida
            String carga = new String(packet.getData()).trim();
            System.out.println(
                String.format("Recibido: %s; desde: %s", carga, packet.getSocketAddress()));

            //si recibe el mensaje "EXIT" finaliza
            if(carga.equals(Server.MSJ_FIN)) break;
        }
        
        //cierra el socket
        socket.close();
    }
    
}
