package test2;

import java.util.Scanner;

public class PrviZadatak {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Unesite broj neprijatelja (N): ");
        int N = sc.nextInt();

        System.out.print("Unesite domet radara (D): ");
        long D = sc.nextLong();

        System.out.print("Unesite početnu odbranu (HD): ");
        long HD = sc.nextLong();

        System.out.print("Unesite koeficijent napada (KA): ");
        long KA = sc.nextLong();

        int brojPrijetnji = 0;

        System.out.println("\nUnesite koordinate neprijatelja (x i y za svakog):");
        for (int i = 0; i < N; i++) {
            System.out.print("Neprijatelj " + (i + 1) + " - x: ");
            long x = sc.nextLong();
            System.out.print("Neprijatelj " + (i + 1) + " - y: ");
            long y = sc.nextLong();

            long MD = Math.abs(x) + Math.abs(y); 

            if (MD <= D) {
                brojPrijetnji++;
            }
        }

        long TD = brojPrijetnji * KA;

        System.out.println("\n REZULTAT");
        System.out.println("Broj otkrivenih prijetnji: " + brojPrijetnji);

        if (TD > HD) {
            System.out.println("Ukupna šteta je: " + TD);
        } else {
            System.out.println("Minimalna šteta (HD) je: " + HD);
        }

        sc.close();
    }
}
