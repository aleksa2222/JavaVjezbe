import java.util.ArrayList;

class AutoServis{
	public static ArrayList<Auto> neregistrovanoKojiSeMoguRegistrovati(ArrayList<Auto> auta) {
	    ArrayList<Auto> lista = new ArrayList<>();

	    for (Auto a : auta) {
	        if (a.mozeSeRegistrovati() && !a.isRegistrovano()) {
	            lista.add(a);
	        }
	    }

	    return lista;
}
	
public static double uupnaRegistracija(ArrayList<Auto> auta) {
	double ukupno = 0.0;

	for (Auto a : auta) {
		ukupno += a.cijenaRegistracije();
	}

	return ukupno;
}
}


public class TestAuto {

	public static void main(String[] args) {
		ArrayList<Auto> auta = new ArrayList<>();
		auta.add(new Auto("Toyota", 2010, 130.5f, 1.8f, true, false));
		auta.add(new Auto("BMW 320d", 2020, 90.0f, 2.0f, false, false));
		for(Auto a: AutoServis.neregistrovanoKojiSeMoguRegistrovati(auta)) {
			System.out.println(a);
		}
		System.out.println(auta.get(0));
		

	}

}


