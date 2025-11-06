
import java.util.Scanner;

public class Zadatak3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Unesite rečenicu: ");
        String original = scanner.nextLine();

        String[] rijeci = original.split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = rijeci.length - 1; i >= 0; i--) {
            sb.append(rijeci[i]);
            if (i != 0) {
                sb.append(" ");
            }
        }

        String obrnutaRecenica = sb.toString();

        System.out.println("Originalna rečenica: " + original);
        System.out.println("Obrnuta rečenica: " + obrnutaRecenica);

        scanner.close();
    }
}
