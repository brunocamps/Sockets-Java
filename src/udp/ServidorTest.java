package udp;

public class ServidorTest {

    public static void main(String[] args) {

      FormServidor srv1 = new FormServidor("5000","5001");
      srv1.setVisible(true);
      
      FormServidor srv2 = new FormServidor("5001","5000");
      srv2.setVisible(true);
        
    }
    
}
