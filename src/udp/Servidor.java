package udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import javax.swing.JTextArea;

public class Servidor extends Thread {
    
    //GIT note
    private final JTextArea txt;
    private final String URL;
    private final int PORTA_ENVIAR;
    private final int PORTA_RECEBER;
    
    public Servidor(JTextArea txt, String URL, String PORTA_ENVIAR, String PORTA_RECEBER){
        this.txt = txt;
        this.URL = URL;
        this.PORTA_ENVIAR = Integer.parseInt(PORTA_ENVIAR);
        this.PORTA_RECEBER = Integer.parseInt(PORTA_RECEBER);
        exibir("Servidor executando na porta " + PORTA_RECEBER);
    }

    private void exibir(String msg){
        txt.append(msg);
    }
    
    public void enviar(String msg){
        try {
            // Transformar a mensagem em bytes
            byte[] buffer = msg.getBytes();
            
            // Empacotar a mensagem
            DatagramPacket pct = new DatagramPacket(
                buffer, buffer.length,
                InetAddress.getByName(URL), PORTA_ENVIAR
            );
            
            //enviar a mensagem
            new DatagramSocket().send(pct);
            
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }    
    
    
    @Override
    public void run(){
        try {
            // Criação do socket assíncrono (UDP)
            DatagramSocket srv = new DatagramSocket(PORTA_RECEBER);
            
            while(true){
                // Criar um buffer para receber as mensagens
                byte[] buffer = new byte[256];
                
                // Objeto para desempacotar a mensagem
                DatagramPacket pct = new DatagramPacket(buffer, buffer.length);
                
                //Receber a mensagem
                srv.receive(pct);
                
                String msg = new String(pct.getData()).trim();
                exibir("\nDE.: " + pct.getAddress().getHostAddress());
                exibir("\nMSG: " + msg + "\n");
            }
            
        } catch (Exception e) {
            System.out.println("ERRO: " + e.getMessage());
        }
    }
    
}
