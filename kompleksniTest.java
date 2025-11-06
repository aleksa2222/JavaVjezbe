
public class kompleksniTest {

	public static void main(String[] args) {
		kompleksniBroj z1 = new kompleksniBroj();
		z1.Odtampaj();
		kompleksniBroj z2 = new kompleksniBroj(-3);
		z2.Odtampaj();
		kompleksniBroj z3 = new kompleksniBroj(3, -4);
		z3.Odtampaj();
		z3.postaviRealniDio(-4);
		z3.Odtampaj();
		
		System.out.printf("In[z3] = %.2f\n", z3.dajImaginarniDio());
		System.out.printf("In[z3] = %.2f\n", z3.modul());
		kompleksniBroj z4 = z3.saberi(z2);
		z4.Odtampaj();
		
	}

}
