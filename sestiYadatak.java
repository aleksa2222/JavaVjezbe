package test;
import java.util.Scanner;
public class sestiYadatak {

	public static void main(String[] args) {
		 
		Scanner sc = new Scanner(System.in);
		
		
		System.out.println("Unesi x koordinatu drona: ");
		double x = sc.nextDouble();
		System.out.println("Unesi y koordinatu drona: ");
		double y = sc.nextDouble();
		
		
		System.out.println("Unesi broj paketa: ");
		int n = sc.nextInt();
		
		for(int i=1; i <= 10; i++) {
			System.out.println("Unesi koordinaut x paketa: ");
			double xp = sc.nextDouble();
			System.out.println("Unesite kooedinatu y paketa: ");
			double yp = sc.nextDouble();
			
			double distanca =  Math.sqrt(Math.pow(xp - x, 2) + Math.pow(yp - y, 2));
			System.out.println("Udaljenost drona do paketa " + i + " je: " + distanca);
			sc.close();
			
		}

	}

}

