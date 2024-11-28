import java.io.*;
import java.net.*;

public class Servidor {
    public static void main(String[] args) {
        int porta = 3000; // Porta onde o servidor ficará escutando

        try (ServerSocket serverSocket = new ServerSocket(porta)) {
            System.out.println("Servidor iniciado. Aguardando conexões...");
            
            while (true) {
                // Aceitar conexões de clientes
                Socket socket = serverSocket.accept();
                System.out.println("Cliente conectado!");

                // Criar uma nova thread para lidar com o cliente
                new Thread(new ManipuladorDeCliente(socket)).start();
            }
        } catch (IOException e) {
            System.out.println("Erro no servidor: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

class ManipuladorDeCliente implements Runnable {
    private Socket socket;

    public ManipuladorDeCliente(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (
            InputStream input = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true)
        ) {
            // Ler os dados do cliente
            String linha;
            while ((linha = reader.readLine()) != null) {
                System.out.println("Mensagem recebida: " + linha);

                // Dividir a mensagem para obter os números e a operação
                String[] partes = linha.split(" ");
                if (partes.length != 3) {
                    writer.println("Erro: formato esperado é '<numero1> <operacao> <numero2>'");
                    continue;
                }

                try {
                    double numero1 = Double.parseDouble(partes[0]);
                    String operacao = partes[1];
                    double numero2 = Double.parseDouble(partes[2]);

                    // Calcular o resultado
                    double resultado = calcular(numero1, operacao, numero2);
                    writer.println("Resultado: " + resultado);
                } catch (NumberFormatException e) {
                    writer.println("Erro: os números devem ser válidos.");
                } catch (IllegalArgumentException e) {
                    writer.println("Erro: operação inválida. Use +, -, *, ou /.");
                }
            }
        } catch (IOException e) {
            System.out.println("Erro na comunicação com o cliente: " + e.getMessage());
        }
    }

    private double calcular(double numero1, String operacao, double numero2) {
        switch (operacao) {
            case "+":
                return numero1 + numero2;
            case "-":
                return numero1 - numero2;
            case "*":
                return numero1 * numero2;
            case "/":
                if (numero2 == 0) {
                    throw new IllegalArgumentException("Divisão por zero não permitida.");
                }
                return numero1 / numero2;
            default:
                throw new IllegalArgumentException("Operação inválida.");
        }
    }
}