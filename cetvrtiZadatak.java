package test;
import java.util.Scanner;

public class cetvrtiZadatak {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Unesite x1: ");
        double x1 = sc.nextDouble();
        
        System.out.print("Unesite y1: ");
        double y1 = sc.nextDouble();
        
        System.out.print("Unesite x2: ");
        double x2 = sc.nextDouble();
        
        System.out.print("Unesite y2: ");
        double y2 = sc.nextDouble();
        
        double a = Math.abs(x1 - x2);
        double b = Math.abs(y1 - y2);
        
        double p = a * b;          // površina
        double o = 2 * a + 2 * b;  // obim
       
        System.out.printf("Povrsina pravougaonika je: %.2f%n", p);
        System.out.printf("Obim pravougaonika je: %.2f%n", o);
        
        
        sc.close();
    }
}
