package test;
import java.util.Scanner;

public class drugiZadatak {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.print("Unesi dužinu stranice a: ");
        double a = sc.nextDouble(); 
        System.out.print("Unesi dužinu stranice b: ");
        double b = sc.nextDouble();
        
        double povrsina = a * b/100;
        
        System.out.printf("Obim pravougaonika je:%.2f", povrsina);
        
        sc.close();
    }
}
