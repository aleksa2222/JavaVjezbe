import java.util.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

abstract class Product {
    protected String description;
    protected String barcode;
    protected String countryOfOrigin;
    protected int yearOfProduction;
    protected double importPrice;

    public Product(String description, String barcode, String countryOfOrigin, int yearOfProduction, double importPrice) {
        this.description = description;
        this.barcode = barcode;
        this.countryOfOrigin = countryOfOrigin;
        this.yearOfProduction = yearOfProduction;
        this.importPrice = importPrice;
    }

    public abstract double calculateRetailPrice();
    public abstract String getCategory();

    public void printInfo() {
        System.out.println("Kategorija: " + getCategory());
        System.out.println("Opis: " + description);
        System.out.println("Barkod: " + barcode);
        System.out.println("Zemlja proizvodnje: " + countryOfOrigin);
        System.out.println("Godina proizvodnje: " + yearOfProduction);
        System.out.println("Uvozna cijena: " + importPrice);
    }
}

class Garderoba extends Product {
    private String material;
    private String season;

    public Garderoba(String description, String barcode, String countryOfOrigin, int yearOfProduction, double importPrice, String material, String season) {
        super(description, barcode, countryOfOrigin, yearOfProduction, importPrice);
        this.material = material;
        this.season = season;
    }

    @Override
    public double calculateRetailPrice() {
        return importPrice * 1.05;
    }

    @Override
    public String getCategory() {
        return "Garderoba";
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Materijal: " + material);
        System.out.println("Sezona: " + season);
        System.out.println("Maloprodajna cijena: " + calculateRetailPrice());
        System.out.println("-----------------------------");
    }
}

class HemijskiProizvod extends Product {
    private double storageTemperature;
    private boolean flammable;

    public HemijskiProizvod(String description, String barcode, String countryOfOrigin, int yearOfProduction, double importPrice, double storageTemperature, boolean flammable) {
        super(description, barcode, countryOfOrigin, yearOfProduction, importPrice);
        this.storageTemperature = storageTemperature;
        this.flammable = flammable;
    }

    @Override
    public double calculateRetailPrice() {
        if (flammable) return importPrice * 1.125;
        return importPrice * 1.10;
    }

    @Override
    public String getCategory() {
        return "Hemijski proizvod";
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Temperatura skladistenja: " + storageTemperature);
        System.out.println("Zapaljivo: " + (flammable ? "Da" : "Ne"));
        System.out.println("Maloprodajna cijena: " + calculateRetailPrice());
        System.out.println("-----------------------------");
    }
}

class Hrana extends Product {
    private int calories;
    private LocalDate expirationDate;

    public Hrana(String description, String barcode, String countryOfOrigin, int yearOfProduction, double importPrice, int calories, LocalDate expirationDate) {
        super(description, barcode, countryOfOrigin, yearOfProduction, importPrice);
        this.calories = calories;
        this.expirationDate = expirationDate;
    }

    @Override
    public double calculateRetailPrice() {
        LocalDate now = LocalDate.now();
        long days = ChronoUnit.DAYS.between(now, expirationDate);
        if (days < 180) return importPrice * 1.05;
        return importPrice * 1.10;
    }

    @Override
    public String getCategory() {
        return "Hrana";
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println("Broj kalorija: " + calories);
        System.out.println("Rok upotrebe: " + expirationDate);
        System.out.println("Maloprodajna cijena: " + calculateRetailPrice());
        System.out.println("-----------------------------");
    }
}

public class Proizvodi {
    static List<Product> products = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;
        while(running) {
            System.out.println("\n1. Unesi proizvod");
            System.out.println("2. Prikazi sve proizvode");
            System.out.println("3. Prikazi maloprodajne cijene svih proizvoda");
            System.out.println("4. Prikazi prosjecnu maloprodajnu cijenu po kategoriji");
            System.out.println("5. Izlaz");
            System.out.print("Izbor: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1: addProduct(); break;
                case 2: printAllProducts(); break;
                case 3: printAllRetailPrices(); break;
                case 4: printAveragePriceByCategory(); break;
                case 5: running = false; break;
                default: System.out.println("Pogresan unos.");
            }
        }
    }

    static void addProduct() {
        System.out.print("Unesite kategoriju (1-Garderoba, 2-Hemijski proizvod, 3-Hrana): ");
        int type = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Opis: ");
        String description = scanner.nextLine();
        System.out.print("Barkod: ");
        String barcode = scanner.nextLine();
        System.out.print("Zemlja proizvodnje: ");
        String country = scanner.nextLine();
        System.out.print("Godina proizvodnje: ");
        int year = scanner.nextInt();
        System.out.print("Uvozna cijena: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        switch(type) {
            case 1:
                System.out.print("Materijal proizvodnje: ");
                String material = scanner.nextLine();
                System.out.print("Sezona: ");
                String season = scanner.nextLine();
                products.add(new Garderoba(description, barcode, country, year, price, material, season));
                break;
            case 2:
                System.out.print("Temperatura skladistenja: ");
                double temp = scanner.nextDouble();
                System.out.print("Da li je zapaljivo (true/false): ");
                boolean flammable = scanner.nextBoolean();
                scanner.nextLine();
                products.add(new HemijskiProizvod(description, barcode, country, year, price, temp, flammable));
                break;
            case 3:
                System.out.print("Broj kalorija: ");
                int calories = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Datum roka upotrebe (YYYY-MM-DD): ");
                String date = scanner.nextLine();
                LocalDate expDate = LocalDate.parse(date);
                products.add(new Hrana(description, barcode, country, year, price, calories, expDate));
                break;
            default:
                System.out.println("Nepoznata kategorija.");
        }
    }

    static void printAllProducts() {
        if (products.isEmpty()) {
            System.out.println("Nema proizvoda.");
            return;
        }
        for (Product p : products) {
            p.printInfo();
        }
    }

    static void printAllRetailPrices() {
        if (products.isEmpty()) {
            System.out.println("Nema proizvoda.");
            return;
        }
        for (Product p : products) {
            System.out.println(p.getCategory() + " [" + p.description + "] - Maloprodajna cijena: " + p.calculateRetailPrice());
        }
    }

    static void printAveragePriceByCategory() {
        System.out.print("Odaberite kategoriju (1-Garderoba, 2-Hemijski proizvod, 3-Hrana): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String category = switch (choice) {
            case 1 -> "Garderoba";
            case 2 -> "Hemijski proizvod";
            case 3 -> "Hrana";
            default -> { System.out.println("Nepoznata kategorija.");   return; }
          
        };
        double sum = 0;
        int count = 0;
        for (Product p : products) {
            if (p.getCategory().equals(category)) {
                sum += p.calculateRetailPrice();
                count++;
            }
        }
        if (count > 0) {
            System.out.println("Prosjecna maloprodajna cijena za " + category + ": " + (sum / count));
        } else {
            System.out.println("Nema proizvoda u toj kategoriji.");
        }
    }
}