import java.util.Scanner;
public class testBankovniRacun {

	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Unesite ime valsnika: ");
		String ime = sc.nextLine();
		System.out.println("Unesite pocetno stanje: ");
		double pocetnoStanje = sc.nextDouble();
		
		BankovniRacun racun = new BankovniRacun(ime, pocetnoStanje);
		
		boolean kraj = false;
		
		while(!kraj) {
			System.out.println("\n--- Meni ---");
			System.out.println("1. Ispisi stanje");
			System.out.println("2. Uplati novac");
			System.out.println("3. Isplati novac");
			System.out.println("4. Izlaz");
			System.out.println("Izaberite opsciju:");
			int opcija = sc.nextInt();
			
			switch(opcija) {
			case 1:
				racun.ispis();
				break;
				
			case 2:
				System.out.println("Unesite iznos za uplatu: ");
				double uplata = sc.nextDouble();
				racun.uplati(uplata);
				break;
				
			case 3:
				System.out.println("Unesite iznos za isplatu: ");
				double isplata = sc.nextDouble();
				racun.isplati(isplata);
				break;
				
			case 4:
				kraj = true;
				System.out.println("Izlaz iz programa.");
				break;
			default:
				System.out.println("Nepoznata opcija, pokusajte ponovo.");
			}
		}
		sc.close();
	}
	
}
