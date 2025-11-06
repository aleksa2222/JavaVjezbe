import java.util.Scanner;

public class Zadatak1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        System.out.println("Unesi rečenicu:");
        String recenica = scanner.nextLine();

        
        String[] rijeci = recenica.trim().split("\\s+");

       
        int brojRijeci = recenica.trim().isEmpty() ? 0 : rijeci.length;

        System.out.println("Broj riječi u rečenici je: " + brojRijeci);

        scanner.close();
    }
}
