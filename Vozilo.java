public abstract class Vozilo {
    protected int id;
    protected double maxBrzina; 

    public Vozilo(int id, double maxBrzina) {
        this.id = id;
        this.maxBrzina = maxBrzina;
    }

   
    public void info() {
        System.out.println("Vozilo ID: " + id);
        System.out.println("Tip: " + this.getClass().getSimpleName());
        System.out.println("Maksimalna brzina: " + maxBrzina + " km/h");
    }

  
    public abstract double izracunajVrijemeDostave(double udaljenostKm);
}
