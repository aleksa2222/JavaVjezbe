import java.util.ArrayList;

public class Main {

    
    public static class Zaposleni {
        protected int id;
        protected String ime;
        protected String prezime;
        protected double plataPoSatu;
        protected int ukupanBrojSati; 

        public Zaposleni(int id, String ime, String prezime,
                         double plataPoSatu, int ukupanBrojSati) {
            this.id = id;
            this.ime = ime;
            this.prezime = prezime;
            this.plataPoSatu = plataPoSatu;
            this.ukupanBrojSati = ukupanBrojSati;
        }

        
        public double izracunajMesecnuPlatu() {
            return 4 * ukupanBrojSati * plataPoSatu;
        }

        public String getTip() {
            return "zaposleni";
        }

        @Override
        public String toString() {
            return id + " - " + ime + " " + prezime;
        }
    }

    
    public static class Konobar extends Zaposleni {
        private int prekovremeniSati;

        public Konobar(int id, String ime, String prezime,
                       double plataPoSatu, int ukupanBrojSati,
                       int prekovremeniSati) {
            super(id, ime, prezime, plataPoSatu, ukupanBrojSati);
            this.prekovremeniSati = prekovremeniSati;
        }

        @Override
        public double izracunajMesecnuPlatu() {
            double osnovica = 4 * ukupanBrojSati * plataPoSatu;
            double dioZaPrekovremene = 4 * prekovremeniSati * (plataPoSatu * 1.2);
            return osnovica + dioZaPrekovremene;
        }

        public int getPrekovremeniSati() {
            return prekovremeniSati;
        }

        @Override
        public String getTip() {
            return "konobar";
        }
    }

    
    public static class Kuvar extends Zaposleni {

        public Kuvar(int id, String ime, String prezime,
                     double plataPoSatu, int ukupanBrojSati) {
            super(id, ime, prezime, plataPoSatu, ukupanBrojSati);
        }

        @Override
        public double izracunajMesecnuPlatu() {
            return 1500 + 4 * ukupanBrojSati * plataPoSatu;
        }

        @Override
        public String getTip() {
            return "kuvar";
        }
    }

   
    public static class Menadzer extends Zaposleni {
        private double bonus;

        public Menadzer(int id, String ime, String prezime,
                        double plataPoSatu, int ukupanBrojSati,
                        double bonus) {
            super(id, ime, prezime, plataPoSatu, ukupanBrojSati);
            this.bonus = bonus;
        }

        @Override
        public double izracunajMesecnuPlatu() {
            return 1300 + 4 * ukupanBrojSati * plataPoSatu + bonus;
        }

        public double getBonus() {
            return bonus;
        }

        @Override
        public String getTip() {
            return "menadzer";
        }
    }
    
    
    public static class ObracunPlate {
        int mjesec;
        int godina;
        Zaposleni zaposleni;
        double iznos;
        String napomena;

        public ObracunPlate(int mjesec, int godina, Zaposleni zaposleni,
                            double iznos, String napomena) {
            this.mjesec = mjesec;
            this.godina = godina;
            this.zaposleni = zaposleni;
            this.iznos = iznos;
            this.napomena = napomena;
        }
    }

    
    public static class Restoran {
        private String naziv;
        private String adresa;
        private String pib;
        private ArrayList<Zaposleni> zaposleni;

        public Restoran(String naziv, String adresa, String pib) {
            this.naziv = naziv;
            this.adresa = adresa;
            this.pib = pib;
            this.zaposleni = new ArrayList<>();
        }

        public void dodajZaposlenog(Zaposleni z) {
            zaposleni.add(z);
        }

        public boolean ukloniZaposlenog(int id) {
            return zaposleni.removeIf(z -> z.id == id);
        }

        public Zaposleni pronadjiPoId(int id) {
            for (Zaposleni z : zaposleni) {
                if (z.id == id) return z;
            }
            return null;
        }

        
        public double generisiObracun(int mjesec, int godina) {
            System.out.println("Obračun plata za " + mjesec + "/" + godina);
            System.out.println("Restoran: " + naziv);
            
            System.out.printf("%-4s %-10s %-12s %-10s %-10s %-15s %-10s%n",
                    "ID", "Ime", "Prezime", "Tip", "Sati", "Prekovremeni/Bonus", "Plata");
           

            double ukupno = 0;

            for (Zaposleni z : zaposleni) {
                double plata = z.izracunajMesecnuPlatu();
                ukupno += plata;

                String extra = "-";

                if (z instanceof Konobar) {
                    extra = "prekovr: " + ((Konobar) z).getPrekovremeniSati() + "/sedm.";
                } else if (z instanceof Menadzer) {
                    extra = "bonus: " + ((Menadzer) z).getBonus();
                }

                System.out.printf("%-4d %-10s %-12s %-10s %-10d %-15s %-10.2f%n",
                        z.id, z.ime, z.prezime, z.getTip(), z.ukupanBrojSati, extra, plata);

                
            }

            System.out.println("---------------------------------------------------------------");
            System.out.printf("UKUPAN TROŠAK PLATA: %.2f EUR%n", ukupno);
            System.out.println();
            return ukupno;
        }
    }

    
    public static void main(String[] args) {

        
        Restoran r = new Restoran("Kod Bucka", "Bulevar 12", "12345678");

       
        Zaposleni z1 = new Konobar(1, "Marko", "Markovic", 5.5, 40, 3);
        Zaposleni z2 = new Kuvar(2, "Ana", "Anic", 6.0, 38);
        Zaposleni z3 = new Menadzer(3, "Petar", "Petrovic", 7.5, 35, 250);
        Zaposleni z4 = new Konobar(4, "Jovan", "Jovic", 5.0, 30, 5);
        Zaposleni z5 = new Kuvar(5, "Mina", "Minic", 6.5, 40);

        
        r.dodajZaposlenog(z1);
        r.dodajZaposlenog(z2);
        r.dodajZaposlenog(z3);
        r.dodajZaposlenog(z4);
        r.dodajZaposlenog(z5);

        
        r.generisiObracun(10, 2025);

       
        Zaposleni trazeni = r.pronadjiPoId(3);
        if (trazeni != null) {
            System.out.println("Pronađen zaposleni: " + trazeni + ", plata: " + trazeni.izracunajMesecnuPlatu());
        }
    }
}
