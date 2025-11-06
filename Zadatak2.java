import java.util.Scanner;

public class Zadatak2 {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String text = sc.nextLine().trim();
		
		String OkrenutTekst = new StringBuilder(text).reverse().toString();
		
		System.out.println(text.equalsIgnoreCase(OkrenutTekst));
		
		sc.close();

	}

}
