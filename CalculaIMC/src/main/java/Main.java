import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.json.JSONObject;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/imc", new ImcHandler());
        server.setExecutor(null);
        System.out.println("Servidor HTTP iniciado na porta 8080");
        server.start();
    }

    static class ImcHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // 1) Sempre adicionar os cabeçalhos CORS
        exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
        exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
        exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type");

        String method = exchange.getRequestMethod();
        if ("OPTIONS".equalsIgnoreCase(method)) {
            // 2) Responder ao preflight sem corpo
            exchange.sendResponseHeaders(204, -1);
            return;
        }

        if ("POST".equalsIgnoreCase(method)) {
            // Seu código atual de leitura do JSON e cálculo de IMC…
            BufferedReader reader = new BufferedReader(new InputStreamReader(exchange.getRequestBody()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            JSONObject json = new JSONObject(sb.toString());
            String nome = json.has("nome") ? json.getString("nome") : "";
            double peso = json.getDouble("peso");
            double altura = json.getDouble("altura");
            double imc = peso / (altura * altura);
            String classificacao = classificaIMC(imc);
            salvarNoBanco(nome, peso, altura, imc);

            JSONObject resposta = new JSONObject();
            resposta.put("imc", imc);
            resposta.put("classificacao", classificacao);
            byte[] respBytes = resposta.toString().getBytes();

            exchange.getResponseHeaders().set("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, respBytes.length);
            OutputStream os = exchange.getResponseBody();
            os.write(respBytes);
            os.close();
        } else {
            exchange.sendResponseHeaders(405, -1); // Method Not Allowed
        }
    }
}

    private static String classificaIMC(double imc) {
        if (imc < 18.5) return "Abaixo do peso";
        if (imc < 25) return "Peso normal";
        if (imc < 30) return "Sobrepeso";
        if (imc < 35) return "Obesidade grau I";
        if (imc < 40) return "Obesidade grau II";
        return "Obesidade grau III";
    }

    public static void salvarNoBanco(String nome, double peso, double altura, double imc) {
        String url = "jdbc:mysql://localhost:3306/CalculaIMC";
        String usuario = "root";
        String senha = "123456";
        String sql = "INSERT INTO pessoa (nome, peso, altura, imc) VALUES (?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, usuario, senha);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, nome);
            stmt.setDouble(2, peso);
            stmt.setDouble(3, altura);
            stmt.setDouble(4, imc);

            stmt.executeUpdate();
            System.out.println("Dados salvos com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}