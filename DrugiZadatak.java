package test2;
import java.util.Scanner;

public class DrugiZadatak {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Unesite koordinate centra: ");
        double cx = sc.nextDouble();
        double cy = sc.nextDouble();

        System.out.print("Unesite poluprecnik r1: ");
        int r1 = sc.nextInt();

        System.out.print("Unesite poluprecnik r2: ");
        int r2 = sc.nextInt();

        System.out.print("Unesite broj trkaca: ");
        int N = sc.nextInt();  

        int naStazi = 0;

        for (int i = 0; i < N; i++) {
            System.out.print("Unesite x trkaca: ");
            double x = sc.nextDouble();
            System.out.print("Unesite y trkaca: ");
            double y = sc.nextDouble();

            
            double d = Math.sqrt(Math.pow(x - cx, 2) + Math.pow(y - cy, 2));

            if (r2 > d && r1 < d && y > cy) {
                naStazi++;
            }
        }

        
        System.out.printf("Broj trkaca na stazi je %d\n", naStazi);

        sc.close();
    }
}
