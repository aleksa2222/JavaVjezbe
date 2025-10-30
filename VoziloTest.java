

public class VoziloTest {
    public static void main(String[] args) {
        Automobil a = new Automobil("VW", 2008, 1896, "siva", 5, "dizel");
        Kamion k = new Kamion("MAN", 2015, 10518, "bijela", 18000, true);
        Kombi  kb = new Kombi("Ford", 2012, 2198, "plava", 9);

        a.prikaziInfo();
        System.out.println("Ukupna cijena registracije: " + a.cijenaRegistracije() + " EUR");
        

        k.prikaziInfo();
        System.out.println("Ukupna cijena registracije: " + k.cijenaRegistracije() + " EUR");
        

        kb.prikaziInfo();
        System.out.println("Ukupna cijena registracije: " + kb.cijenaRegistracije() + " EUR");
        
    }
}
