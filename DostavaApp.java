import java.util.ArrayList;
import java.util.List;

public class DostavaApp {
    public static void main(String[] args) {

        
        List<Vozilo> vozila = new ArrayList<>();
        vozila.add(new Bicikl(1, 20));      
        vozila.add(new Motor(2, 50));      
        vozila.add(new Automobil(3, 80));   

        double udaljenost = 10.0; 

        System.out.println("=== Dostava hrane - simulacija ===");
        System.out.println("Udaljenost dostave: " + udaljenost + " km\n");

        double najkraceVrijeme = Double.MAX_VALUE;
        Vozilo najbrzeVozilo = null;

        double najmanjaPotrosnja = Double.MAX_VALUE;
        Vozilo najEkonomicnijeVozilo = null;

        
        for (Vozilo v : vozila) {
            v.info();

            double vrijeme = v.izracunajVrijemeDostave(udaljenost);
            double vrijemeMin = vrijeme * 60;

            System.out.printf("Vrijeme dostave: %.2f h (%.0f min)%n", vrijeme, vrijemeMin);

      
            if (vrijeme < najkraceVrijeme) {
                najkraceVrijeme = vrijeme;
                najbrzeVozilo = v;
            }

            
            if (v instanceof Ekonomican) {
                Ekonomican eko = (Ekonomican) v;
                double ukupnaPotrosnja = eko.potrosnjaPoKm() * udaljenost;
                System.out.printf("Potrosnja na %.1f km: %.4f jedinica%n", udaljenost, ukupnaPotrosnja);

                
                if (ukupnaPotrosnja < najmanjaPotrosnja) {
                    najmanjaPotrosnja = ukupnaPotrosnja;
                    najEkonomicnijeVozilo = v;
                }
            } else {
                System.out.println("Ovo vozilo se ne smatra ekonomičnim (nema interfejs Ekonomican).");
            }

            System.out.println("------------------------------------");
        }

        
        System.out.println("\n=== Rezime ===");
        if (najbrzeVozilo != null) {
            System.out.printf("Najbrže vozilo: %s (ID: %d) - vrijeme: %.2f h (%.0f min)%n",
                    najbrzeVozilo.getClass().getSimpleName(),
                    najbrzeVozilo.id,
                    najkraceVrijeme,
                    najkraceVrijeme * 60);
        }

        if (najEkonomicnijeVozilo != null) {
            System.out.printf("Najekonomicnije vozilo (od onih sa interfejsom Ekonomican): %s (ID: %d) - potrošnja na %.1f km: %.4f jedinica%n",
                    najEkonomicnijeVozilo.getClass().getSimpleName(),
                    najEkonomicnijeVozilo.id,
                    udaljenost,
                    najmanjaPotrosnja);
        } else {
            System.out.println("Nijedno vozilo ne implementira interfejs Ekonomican.");
        }
    }
}
