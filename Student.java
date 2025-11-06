
public class Student {
	private String ime;
	private String prezime;
	private String brojIndeksa;
	private int ocjena1;
	private int ocjena2;
	private int ocjena3;
	
	public static int brojStudenata = 0;
	
	public Student(String ime, String prezime, String brojIndeksa, int ocjena1, int ocjena2, int ocjena3) {
		this.ime = ime;
		this.prezime = prezime;
		this.brojIndeksa = brojIndeksa;
		this.ocjena1 = ocjena1;
		this.ocjena2 = ocjena2;
		this.ocjena3 = ocjena3;
		brojStudenata++;
	}
	
	public double izarcunajProsjek() {
		return(ocjena1 + ocjena2 + ocjena3)/3.00;
	}
	
	public boolean imaStipendiju() {
		return izarcunajProsjek() > 9.00;
	}
	
	public void ispisInformacija() {
		System.out.println(ime + " " + prezime + ", Stipendija: " + (imaStipendiju() ? "Da" : "Ne"));

	}
}
