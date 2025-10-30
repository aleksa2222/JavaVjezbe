
//Hajdana Radanović 24/158
//Aleksa Božović 24/145

import java.util.ArrayList;

public class GameObject {
    private int x, y; //pozicija objekata na ekranu
    private int width, height;

    public GameObject(int x, int y, int width, int height) {
        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    public int getX() { 
     return x; 
     }
    
    public void setX(int x) { 
     this.x = x; 
     }

    public int getY() { 
     return y; 
    }
    
    
    public void setY(int y){ 
     this.y = y; 
     }

    public int getWidth() { 
     return width; 
     }
    
    public void setWidth(int width) { 
     this.width = Math.max(1, width); 
     }

    public int getHeight() {
     return height; 
     }
    public void setHeight(int height) { 
     this.height = Math.max(1, height); //ova metoda daje veci broj, a uslov nam je 
     } //da su dimenzije vece od 0


//Provjerava da lijeva strana ovog objekta (this.x) nije desno od desne strane drugog objekta 
//(other.x + other.width).
    
    public boolean intersects(GameObject other) { //da li se dva objekta preklapaju
        return this.x < other.x + other.width &&   
               this.x + this.width > other.x &&
               this.y < other.y + other.height &&
               this.y + this.height > other.y;
    }

    @Override
    public String toString() {
        return String.format("GameObject @ (%d,%d) %dx%d", x, y, width, height);
    }
}


class Player extends GameObject {
    private String name;
    private int health;

    public Player(String name, int x, int y, int width, int height, int health) {
        super(x, y, width, height);
        setName(name);
        setHealth(health);
    }

    public String getName() {
     return name; 
     }

   public void setName(String name) {
        if (name == null || name.trim().isEmpty()) { //trim - uklanja praznine s pocetka i kraja i isEmpty
            this.name = "Nepoznat"; //provjerava da li je sto ostalo prazno 
            return; //npr " ", vraca u "" 
        } 
        //replaceAll- razmaci unutar stringa se zamjenjuju sa jednim 
        //split - pravi niz stringova razdvojenih po space
        String[] words = name.trim().replaceAll("\\s+", " ").split(" ");
        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(Character.toUpperCase(w.charAt(0)))
              .append(w.substring(1).toLowerCase()).append(" ");
        }
        this.name = sb.toString().trim(); //pretvara stringbuilder nazad u string 
    }

    public int getHealth() {
     return health;
     }

    public void setHealth(int health) { //zivotni poeni izmedju 0 i 100
        if (health < 0) {
            this.health = 0;
        } else if (health > 100) {
            this.health = 100;
        } else {
            this.health = health;
        }
    }

    @Override
    public String toString() {
        return String.format("Player (%s) @ (%d,%d) %dx%d HP=%d",
                name, getX(), getY(), getWidth(), getHeight(), health);
    }
}


class Enemy extends GameObject {
    private String type;
    private int damage;
    private int health;

    public Enemy(String type, int x, int y, int width, int height, int damage, int health) {
        super(x, y, width, height);
        setType(type);
        setDamage(damage);
        setHealth(health);
    }

    public String getType() { 
     return type;
     }
    
    public void setType(String type) {
        this.type = (type == null || type.trim().isEmpty()) ? "Unknown" : type.trim();
    }

    public int getDamage() {
     return damage; 
     }
    
    public void setDamage(int damage) { 
     this.damage = Math.min(100, Math.max(0, damage));
     }

    public int getHealth() { 
     return health; 
     }
    
    public void setHealth(int health) { //zivotni poeni izmedju 0 i 100
        if (health < 0) {
            this.health = 0;
        } else if (health > 100) {
            this.health = 100;
        } else {
            this.health = health;
        }
    }
    
    @Override
    public String toString() {
        return String.format("Enemy [%s] @ (%d,%d) %dx%d DMG=%d HP=%d",
                type, getX(), getY(), getWidth(), getHeight(), damage, health);
    }
}


class MeleeEnemy extends Enemy {
    public MeleeEnemy(String type, int x, int y, int width, int height, int damage, int health) {
        super(type, x, y, width, height, damage, health);
    }

    @Override
    public String toString() {
        return String.format("MeleeEnemy [%s] @ (%d,%d) %dx%d DMG=%d HP=%d",
                getType(), getX(), getY(), getWidth(), getHeight(), getDamage(), getHealth());
    }
}


//JACI NEPRIJATELJ
class BossEnemy extends Enemy {
    public BossEnemy(String type, int x, int y, int width, int height, int damage, int health) {
        super(type, x, y, width, height, damage, health);
    }

    @Override
    public int getDamage() {
        return super.getDamage() * 2; //nanosi duplu stetu u odnosu na damage 
    }

    
    //kako je trazeno u zadatku za metodu toString()
    @Override
    public String toString() {
        return String.format("BossEnemy [%s] @ (%d,%d) %dx%d DMG=%dx2 HP=%d",
                getType(), getX(), getY(), getWidth(), getHeight(), super.getDamage(), getHealth());
    }
}


class Game { //nismo mogli public jer nam je klasa GameObject
    private Player player;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    private ArrayList<String> eventLog = new ArrayList<>();

    public Game(Player player) {
        this.player = player;
    }

    //ove metode smo malo modifikovali iz projekta 2
    public void addEnemy(Enemy e) {
        enemies.add(e);
        eventLog.add("ADD: " + e.getType() + " added to game.");
    }

    //uslovi 
    public ArrayList<Enemy> findByType(String query) {
        ArrayList<Enemy> result = new ArrayList<>();
        String q = query.toLowerCase();
        for (Enemy e : enemies) {
            if (e.getType().toLowerCase().contains(q)) {
                result.add(e);
            }
        }
        return result;
    }

    public boolean checkCollision(Player p, Enemy e) {
        return p.intersects(e);
    }

    public void decreaseHealth(Player p, Enemy e) {
        int dmg = (e instanceof BossEnemy) ? e.getDamage() : e.getDamage();
        int oldHP = p.getHealth();
        p.setHealth(p.getHealth() - dmg);
        eventLog.add(String.format("HIT: Player by %s for %d -> HP %d -> %d",
                e.getType(), dmg, oldHP, p.getHealth()));
    }

    public ArrayList<Enemy> collidingWithPlayer() {
        ArrayList<Enemy> sudar = new ArrayList<>();
        for (Enemy e : enemies) {
            if (checkCollision(player, e)) sudar.add(e);
        }
        return sudar;
    }

    public void resolveCollisions() {
        for (Enemy e : collidingWithPlayer()) {
            decreaseHealth(player, e);
        }
    }

    public void printEventLog() {
        System.out.println("Event Log: ");
        for (String s : eventLog) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) {
     Player igrac = new Player(" peTar petrovic ", 10, 5, 32, 32, 85);
     Game igra = new Game(igrac);

     Enemy neprijatelj1 = new MeleeEnemy("orc", 12, 5, 16, 16, 10, 50);
     Enemy neprijatelj2 = new BossEnemy("Goblin King", 50, 5, 32, 32, 25, 100);

     igra.addEnemy(neprijatelj1);
     igra.addEnemy(neprijatelj2);

     System.out.println(igrac);
     for (Enemy e : igra.enemies) {
         System.out.println(e);
     }

     System.out.println("\nNeprijatelji koji sadrže 'gob':");
     for (Enemy e : igra.findByType("gob")) {
         System.out.println(e);
     }

     System.out.println("\nNeprijatelji koji se sudaraju sa igračem:");
     for (Enemy e : igra.collidingWithPlayer()) {
         System.out.println(e);
     }

     igra.resolveCollisions();

     System.out.println("\nIgrač nakon sudara:");
     System.out.println(igrac);

     igra.printEventLog();
    }
}
