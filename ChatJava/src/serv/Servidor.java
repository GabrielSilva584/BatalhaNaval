package serv;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Servidor {
    
    public static void main(String[] args) throws IOException {
      //Crio um objeto  chamado servidor a partir da classe
      //ServerSocket e atribuo ao meu servidor a porta 12345
      ServerSocket servidor = new ServerSocket(12345); 
      
      //Crio um objeto chamado cliente a partir da classe
      //Socket que receberá o metodo accept do servidor
      Socket cliente  = servidor.accept();
      
        System.out.println("Nova conexão com o Cliente: "+
                cliente.getInetAddress().getHostAddress());
        
        //Crio um objeto chamado scan , do tipo Scanner que é 
        //capaz de verficar e ler as informaçoes enviadas
        //pelo cliente
        Scanner scan = new Scanner(cliente.getInputStream());
        
        //Estrutura de repetição que exobora na tela uma linha
        //quando tivermos uma proxima linha
        while (scan.hasNextLine()) {
            System.out.println(scan.nextLine());
          
        }

        scan.close();
        cliente.close();
        servidor.close();
    }
    
}
