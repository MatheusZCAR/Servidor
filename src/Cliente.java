import java.io.*;
import java.net.*;

public class Cliente {
    public static void main(String[] args) {
        String host = "localhost"; // Endereço do servidor
        int porta = 3000; // Porta do servidor

        try (Socket socket = new Socket(host, porta)) {
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);

            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // Simular envio de dados para o servidor
            try (BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in))) {
                System.out.println("Conectado ao servidor. Envie uma operação no formato: <numero1> <operacao> <numero2>");
                
                String linha;
                while ((linha = teclado.readLine()) != null) {
                    writer.println(linha); // Enviar para o servidor
                    String resposta = reader.readLine(); // Ler resposta do servidor
                    System.out.println("Servidor: " + resposta);
                }
            }
        } catch (UnknownHostException e) {
            System.out.println("Servidor desconhecido: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e.getMessage());
        }
    }
}
