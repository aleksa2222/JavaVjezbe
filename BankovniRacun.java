
public class BankovniRacun {
	
	private String imeVlasnika;
	private double stanje;
	
	public BankovniRacun(String imeVlasnika, double stanje) {
		this.imeVlasnika = imeVlasnika;
		setStanje(stanje);
	}
	
	public String getImeVlasnika() {
		return imeVlasnika;
	}
	
	public double getStanje() {
		return stanje;
	}
	
	public void setImeVlasnika(String imeVlasnika) {
		this.imeVlasnika = imeVlasnika;
	}
	
	public void setStanje(double stanje) {
		if(stanje < 0) {
			System.out.println("Stanje ne moze biti negativno. Postavljam stanje na 0.");
			this.stanje = 0;
		}else {
			this.stanje = stanje;
		}
	}
	
	public void uplati(double iznos) {
		if(iznos <= 0) {
			System.out.println("Iznos uplate mora biti veci od 0");
			return;
		}
		
		stanje += iznos;
		System.out.println("Uplaceno: " + iznos + ". Novo stanje na racunu je: " + stanje);
	}
	
	public void isplati(double iznos) {
		if(iznos <= 0 ) {
			System.out.println("Iznos isplate mora biti veci od 0.");
			return;
		}
		
		if(stanje >= iznos) {
			stanje -= iznos;
			System.out.println("Isplaceno: " + iznos + ". Novo stanje je: " + stanje);
		}else {
			System.out.println("Nedovoljno sredstava za isplatu " + iznos + ". Trenutno stanje: " + stanje);
		}
	}
	
	public void ispis() {
		System.out.println("Vlasnik: " + imeVlasnika + ", Trenutno stanje: " + stanje);
	}
}
