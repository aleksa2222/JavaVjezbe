
public class testAutomobil {

	public static void main(String[] args) {
		
		Automobil a1 = new Automobil("VW", "Golf 8 GTI", 2025, 190);
		
		a1.ispis();
		
		a1.ubrzaj(50);
		a1.ispis();
		
		a1.uspori(50);
		a1.ispis();
		
		a1.uspori(150);
		a1.ispis();
	}

}
