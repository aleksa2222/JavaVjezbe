
public class Auto {
	private String markaAuta;
	private int godisteAuta;
	private float snagaMotora;
	private boolean prodato;
	private float kubikaza;
	private boolean registrovano;
	public static int brojProdatihAuta = 0;
	
	public Auto(String markaAuta, int godisteAuta, float snagaMotora, float kubikaza, boolean registrovano, boolean prodato) {
		this.markaAuta = markaAuta;
		this.godisteAuta = godisteAuta;
		this.snagaMotora = snagaMotora;
		this.kubikaza = kubikaza;
		this.registrovano = registrovano;
		this.prodato = prodato;
		
		if (prodato) brojProdatihAuta++;
		this.registrovano = godisteAuta < 1985 ? false : registrovano;
	}
	
	public String getMarkaAuta() {
		return markaAuta;
	}
	
	public void setMarkaAuta(String markaAuta) {
        this.markaAuta = markaAuta;
    }
	
	public int getGodisteAuta() {
		return godisteAuta;
	}
	
	public void setGodisteAuta(int godisteAuta) {
		if (godisteAuta >= 2025) {
			System.out.println("Neispravno godiste auta");
			return;
        }
		this.godisteAuta = godisteAuta;
		if (godisteAuta < 1985) {
            this.registrovano = false;
	}
	}
	
	public float getSnagaMotora() {
		return snagaMotora;
	}
	
	public void setSnagaMotora(float snagaMotora) {
		this.snagaMotora = snagaMotora;
	}
	
	public boolean isProdato() {
        return prodato;
	}
	
	public void setProdato(boolean prodato) {
		if(this.prodato != prodato && prodato) {
            brojProdatihAuta++;
        }
        this.prodato = prodato;
    }
	
	public float getKubikaza() {
		return kubikaza;
	}
	
	public void setKubikaza(float kubikaza) {
		this.kubikaza = kubikaza;
	}
	
	public boolean isRegistrovano() {
		return registrovano;
	}
	
	public void setRegistrovano(boolean registrovano) {
		if (godisteAuta >= 1985) {
			this.registrovano = registrovano;
		}
	}
	
	public static int brojProdatihAuta() {
		return brojProdatihAuta;
	}
	
	public boolean mozeSeRegistrovati() {
		return godisteAuta >= 1985;
	}
	
	public static double koeficijentZaGodiste(int godiste) {
		if(godiste < 1985) return 0.0;
		if(godiste <= 2000) return 3.0;
		if(godiste <= 2010) return 2.0;
		return 1.5;
	}
	
	public double cijenaRegistracije() {
		if(!mozeSeRegistrovati()) return 0.0;
		return koeficijentZaGodiste(godisteAuta) * kubikaza * snagaMotora;
	}
	
	@Override
	public String toString() {
		return String.format("Marka auta: %s, Godište: %d, Snaga motora: %.2f kW, Kubikaža: %.2f L, Registrovano: %b, Prodato: %b, cijena registracije: %.2f",
                markaAuta, godisteAuta, snagaMotora, kubikaza, registrovano ?"da" : "ne", prodato ?"da" : "ne", cijenaRegistracije());
	}

	
	
	
}
