
package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * https://github.com/dordonez-ute-apdist/udp
 * @author dordonez@ute.edu.ec
 */
public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("UDP CLIENTE");
                
        //Crea el socket cliente
        DatagramSocket socket = new DatagramSocket();
        System.out.println("El cliente se reserva el puerto: " + socket.getLocalPort());
        
        //Define la dirección(IP o nombre) del servidor
        InetAddress direccion = InetAddress.getByName(Server.DIRECCION);
        
        //Mensajes a enviar
        String[] mensajes = {"HOLA", "Primer mensaje", "Segundo mensaje", "CHAO", Server.MSJ_FIN};

        for(String msj : mensajes) {
            //Convierte el valor a byte[]
            byte[] buffer = msj.getBytes();
            
            //Crea el datagrama a enviar con el valor, y la dirección y puerto del servidor
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, Server.PUERTO);
            
            //envía el datagrama
            socket.send(packet);
            
            //presenta la información enviada
            System.out.println(
                String.format("Enviado: %s; hacia: %s", new String(packet.getData()), packet.getSocketAddress()));
            
            //Pausa el trabajo del hilo
            Thread.sleep(3000);
        }
        
        socket.close();
    }
    
}
