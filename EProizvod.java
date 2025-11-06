public class EProizvod {
    protected String opis;
    protected String sifra;
    protected double uvoznaCijena;
    
    
    public EProizvod(String opis, String sifra, double uvoznaCijena) {
        this.opis = opis;
        this.sifra = sifra;
        this.uvoznaCijena = uvoznaCijena;
    }

    protected double osnovnaMaloprodajna() {
        return uvoznaCijena * 1.05;
    }

    public double izracunajMaloprodajnuCijenu() {
        return osnovnaMaloprodajna();
    }

    public String getSifra() {
        return sifra;
    }

    public String toString() {
        return opis + " (" + sifra + ")";
    }
}

class Racunar extends EProizvod {
    private String procesor;
    private int memorijaGB;

    public Racunar(String opis, String sifra, double uvoznaCijena, String procesor, int memorijaGB) {
        super(opis, sifra, uvoznaCijena);
        this.procesor = procesor;
        this.memorijaGB = memorijaGB;
    }

    @Override
    public double izracunajMaloprodajnuCijenu() {
        double c = osnovnaMaloprodajna();
        c = c * 1.05;
        return c;
    }

    @Override
    public String toString() {
        return "Racunar: " + opis + ", " + procesor + ", " + memorijaGB + "GB, sifra: " + sifra;
    }
}

class Telefon extends EProizvod {
    private String operativniSistem;
    private double velicinaEkrana;

    public Telefon(String opis, String sifra, double uvoznaCijena, String operativniSistem, double velicinaEkrana) {
        super(opis, sifra, uvoznaCijena);
        this.operativniSistem = operativniSistem;
        this.velicinaEkrana = velicinaEkrana;
    }

    @Override
    public double izracunajMaloprodajnuCijenu() {
        double c = osnovnaMaloprodajna();
        if (velicinaEkrana > 6.0) {
            c = c * 1.03;
        }
        return c;
    }

    @Override
    public String toString() {
        return "Telefon: " + opis + ", OS: " + operativniSistem + ", ekran: " + velicinaEkrana + "\", sifra: " + sifra;
    }
}

class TVUredjaj extends EProizvod {
    private double velicinaEkrana;

    public TVUredjaj(String opis, String sifra, double uvoznaCijena, double velicinaEkrana) {
        super(opis, sifra, uvoznaCijena);
        this.velicinaEkrana = velicinaEkrana;
    }

    @Override
    public double izracunajMaloprodajnuCijenu() {
        double c = osnovnaMaloprodajna();
        if (velicinaEkrana > 65) {
            c = c * 1.10;
        }
        return c;
    }

    @Override
    public String toString() {
        return "TV: " + opis + ", ekran: " + velicinaEkrana + "\", sifra: " + sifra;
    }
}

