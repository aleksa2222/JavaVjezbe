package test;
import java.util.Scanner;

public class petiZadatak {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        double xh = sc.nextDouble();
        double yh = sc.nextDouble();
        
        double xk = sc.nextDouble();
        double yk = sc.nextDouble();
        
        double xb = xk+2;
        double yb = yk-3;
        
        double HB = Math.sqrt(Math.pow(yb-yh,2) + Math.pow(xb-xh, 2));
        
        System.out.printf("Udaljenost od blaga je %.2f",HB);
     
        //double UDHK = Math.sqrt(Math.pow(yk-yh, 2)+ Math.pow(xk-xh,2));
        
        sc.close();
    }
}
