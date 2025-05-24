import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Seja muito bem vindo à máquina calculadora de IMC. " +
                "Todos os registros solicitados nesse projeto serão inseridos num banco de dados, com finalidade acadêmica. " +
                "Ao prosseguir, você está de acordo com as normas vigentes na LGPD.\n");

        System.out.println("Informe seu nome: \n");
        String nome = new Scanner(System.in).nextLine();

        System.out.println("Informe seu peso(kg): \n");
        double peso = new Scanner(System.in).nextDouble();

        System.out.println("Informe sua altura(m) - COM PONTO E NÃO VÍRGULA: \n");
        double altura = new Scanner(System.in).nextDouble();

        // imc
        double imc = peso / (altura * altura);
        System.out.printf("IMC: %.2f \n", imc);

        // metodo pra salvar no mysql worbench
        salvarNoBanco(nome, peso, altura, imc);
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