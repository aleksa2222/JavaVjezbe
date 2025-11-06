
public class Automobil {
	
	private String marka;
	private String model;
	private int godiste;
	private int brzina;
	
	public Automobil(String marka, String model, int godiste, int brzina) {
		this.marka = marka;
		this.model = model;
		this.godiste = godiste;
		setBrzina(brzina);
	}
	
	public String getMarka() {
		return marka;
	}
	
	public String getModel() {
		return model;
	}
	
	public int getGodiste() {
		return godiste;
	}
	
	public int getBrznia() {
		return brzina;
	}
	
	public void setMarka(String marka) {
		this.marka = marka;
	}
	
	public void setModel(String model) {
		this.model = model;
	}
	
	public void setGodiste(int godiste) {
		this.godiste = godiste;
	}
	
	public void setBrzina(int brzina) {
		if(brzina < 0 ) {
			System.out.println("Brzina ne moze biti manja od 0. Postavljam brzinu na 0.");
			this.brzina = 0;
		}else if(brzina > 200) {
			System.out.println("Brzina ne moze biti veca od 200. Postavljam brzinu na 200");
		}else {
			this.brzina = brzina;
		}
	}
	
	public void ubrzaj(int vrijednost) {
		if(vrijednost < 0) {
			System.out.println("Vrijednost ubrzanja mora biti pozitivna!");
			return;
		}
		
		brzina += vrijednost;
		
		if(brzina > 200) {
			brzina = 200;
			System.out.println("Dostigli ste maksimalnu brzinu (200km/h).");
		}
	}
	
	public void uspori(int vrijednost) {
		if(vrijednost < 0) {
			System.out.println("Vrijdnost usporavanja mora biti pozitivan!");
			return;
		}
		
		brzina -= vrijednost;
		
		if(brzina < 0) {
			brzina = 0;
			System.out.println("Vozilo se zaustavilo (brzina = 0).");
		}
	}
	
	public void ispis() {
		System.out.println("Marka: " + marka + ", model: " + model + ", godiste: " + godiste + ", brzina: " + brzina + "km/h");
	}
}
