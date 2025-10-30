
public class Vozilo {
	
	protected String proizvodjac;
	protected int godinaProizvodnje;
	protected int kubikaza;
	protected String boja;
	
	//konstruktor
	public Vozilo(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja) {
		this.proizvodjac = proizvodjac;
		this.godinaProizvodnje = godinaProizvodnje;
		this.kubikaza = kubikaza;
		this.boja = boja;
	}
	
	
	//geteri i seteri
	public String getProizvodjac() {
		return proizvodjac;
	}
	
	public void setProizvodjac(String proizvodjac) {
		this.proizvodjac = proizvodjac;
	}
	
	public int godinaProizvodnje() {
		return godinaProizvodnje;
	}
	
	public void setGodinaProizvodnje(int godinaProizvodnje) {
		this.godinaProizvodnje = godinaProizvodnje;
	}
	
	public int getKubikaza() {
		return kubikaza;
	}
	
	public void setKubikaza(int kubikaza) {
		this.kubikaza = kubikaza;
	}
	
	public String getBoja() {
		return boja;
	}
	
	public void setBoja(String boja) {
		this.boja = boja;
	}
	
	
    //metode za prikaz informacija
	public void prikaziInfo() {
		System.out.println("Proizvođač: " + proizvodjac);
		System.out.println("Godina proizvodnje: " + godinaProizvodnje);
		System.out.println("Kubikaža: " + kubikaza + " cm3");
		System.out.println("Boja: " + boja);
	}
	 
	
	//metoda za izračun cijene registracije
	 public int cijenaRegistracije() {
	        int cijena = 100;
	        if (godinaProizvodnje < 2010) {
	            cijena += 30;
	        }
	        if (kubikaza > 2000) {
	            cijena += 50;
	        }
	        return cijena;
	    }


}

//iz klase vozilo nasljeđuje klasa automobil
class Automobil extends Vozilo {
	
	//dodatni atrubuti
    protected int brojVrata;
    protected String tipMotora;

    public Automobil(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja,
                     int brojVrata, String tipMotora) {
        super(proizvodjac, godinaProizvodnje, kubikaza, boja);
        this.brojVrata = brojVrata;
        this.tipMotora = tipMotora;
    }
    
    
    //geteri i seteri
    
	public int getBrojVrata() {
		return brojVrata;
	}
	
	public void setBrojVrata(int brojVrata) {
		this.brojVrata = brojVrata;
	}
	
	public String getTipMotora() {
		return tipMotora;
	}
	
	public void setTipMotora(String tipMotora) {
		this.tipMotora = tipMotora;
	}
	
	
	@Override
    public void prikaziInfo() {
        System.out.println("[Automobil]");
        super.prikaziInfo();
        System.out.println("Broj vrata: " + brojVrata);
        System.out.println("Tip motora: " + tipMotora);
    }
	
	@Override
	 public int cijenaRegistracije() {
        int cijena = super.cijenaRegistracije();
        if (tipMotora != null && tipMotora.equalsIgnoreCase("dizel")) {
            cijena += 20;
        }
        return cijena;
    } 
	
}

class Kamion extends Vozilo {
    private int kapacitetTereta;
    private boolean prikolica;

    public Kamion(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja,
                  int kapacitetTereta, boolean prikolica) {
        super(proizvodjac, godinaProizvodnje, kubikaza, boja);
        this.kapacitetTereta = kapacitetTereta;
        this.prikolica = prikolica;
    }

    public int getKapacitetTereta() { return kapacitetTereta; }
    public void setKapacitetTereta(int kapacitetTereta) { this.kapacitetTereta = kapacitetTereta; }

    public boolean isPrikolica() { return prikolica; }
    public void setPrikolica(boolean prikolica) { this.prikolica = prikolica; }

    @Override
    public void prikaziInfo() {
        System.out.println("[Kamion]");
        super.prikaziInfo();
        System.out.println("Kapacitet tereta: " + kapacitetTereta);
        System.out.println("Prikolica: " + (prikolica ? "DA" : "NE"));
    }

    @Override
    public int cijenaRegistracije() {
        int cijena = super.cijenaRegistracije();
        if (prikolica) {
            cijena += 50;
        }
        return cijena;
    }
}

class Kombi extends Vozilo {
    private int kapacitetPutnika;

    public Kombi(String proizvodjac, int godinaProizvodnje, int kubikaza, String boja,
                 int kapacitetPutnika) {
        super(proizvodjac, godinaProizvodnje, kubikaza, boja);
        this.kapacitetPutnika = kapacitetPutnika;
    }

    public int getKapacitetPutnika() { return kapacitetPutnika; }
    public void setKapacitetPutnika(int kapacitetPutnika) { this.kapacitetPutnika = kapacitetPutnika; }

    @Override
    public void prikaziInfo() {
        System.out.println("[Kombi]");
        super.prikaziInfo();
        System.out.println("Kapacitet putnika: " + kapacitetPutnika);
    }

    @Override
    public int cijenaRegistracije() {
        int cijena = super.cijenaRegistracije();
        if (kapacitetPutnika > 8) {
            cijena += 30;
        }
        return cijena;
    }
}

 









