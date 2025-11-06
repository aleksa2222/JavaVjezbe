
public class kompleksniBroj {
	
	private float realniDio;
	private float imaginarniDio;
	
	//Konstruktor
	public kompleksniBroj() {
		realniDio = 0;
		imaginarniDio = 0;	
	}
	
	public kompleksniBroj(float a, float b) {
		realniDio = a;
		imaginarniDio = b;
	}
	
	public kompleksniBroj(float a) {
		realniDio = a;
		imaginarniDio = 0;
	}
	
	public float dajRealniDio() {
		return realniDio;
	}
	
	public float dajImaginarniDio() {
		return realniDio;
	}
	
	public void postaviRealniDio(float a) {
		realniDio = a;
	}
	
	public void postaviImaginarniDio(float a) {
		imaginarniDio = a;
	}
	
	
	public void Odtampaj() {
		if(imaginarniDio > 0 )
		System.out.printf("%.2f + %.2fi\n", realniDio, imaginarniDio);
		else
			System.out.printf("%.2f + %.2fi\n", realniDio, -imaginarniDio);
	}
	

	
	public float modul() {
	
		return (float)Math.sqrt(realniDio * realniDio + imaginarniDio * imaginarniDio);
		
	}
	
	// sabritanje
	public kompleksniBroj saberi(kompleksniBroj a) {
		kompleksniBroj rez = new kompleksniBroj();
		rez.realniDio = realniDio + a.realniDio;
		rez.imaginarniDio = imaginarniDio + a.imaginarniDio;
		return rez;
		
	}
	
	// oduzimanje
	
	public kompleksniBroj oduzmi(kompleksniBroj a) {
		kompleksniBroj rez = new kompleksniBroj();
		rez.realniDio = dajRealniDio() - a.dajRealniDio();
		rez.imaginarniDio = dajImaginarniDio() + a.imaginarniDio;
		return rez;
		
	}
}
