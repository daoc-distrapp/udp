
package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 *
 * @author dordonez@ute.edu.ec
 */
public class Client {

    public static void main(String[] args) throws Exception {
        System.out.println("UDP CLIENTE");
                
        //Crea el socket cliente
        DatagramSocket socket = new DatagramSocket();
        //Obtiene la IP del servidor
        InetAddress direccion = InetAddress.getByName(Server.DIRECCION); 
        //Mensajes a enviar
        String[] mensajes = {"HOLA", "Primer mensaje", "Segundo mensaje", "CHAO", Server.END_OF_MSGS};

        for(String msj : mensajes) {
            //Convierte el valor a byte[]
            byte[] buffer = msj.getBytes();
            //Crea el datagrama a enviar con el valor, y la dirección y puerto del servidor
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, direccion, Server.PUERTO);
            //envía el datagrama
            socket.send(packet);
            //presenta la información enviada
            System.out.println(
                String.format("Enviado: %s; A: %s", msj, packet.getSocketAddress()));
            //Pausa el trabajo del hilo
            Thread.sleep(3000);
        }
        
        socket.close();
    }
    
}
