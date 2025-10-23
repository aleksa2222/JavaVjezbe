
// Hajdana Radanović 24/158
// Elena Božarić 24/070
//Aleksa Božović 24/145
import java.util.ArrayList;

class Player {
	private String name;
	private int x;
	private int y;
	private int width, height;
	private int health;

	public Player(String name, int x, int y, int width, int height, int health) {
		setName(name);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setHealth(health);
	}

	public void setName(String name) {
		name = name.trim().replaceAll("\\s+", " ");//trim - da maknemo space sa pocetka i kraja
		String[] reci = name.split(" ");
		StringBuilder sb = new StringBuilder();//zbog toga sto preko njega mijenjamo string
		for (String r : reci) {
			sb.append(Character.toUpperCase(r.charAt(0)))//pretvara u veliko slovo prvi karakter odvojenih rijeci
			  .append(r.substring(1).toLowerCase()).append(" "); //substring ide od drugog slova i pravi sve u malo
  
		}
		this.name = sb.toString().trim(); //vraca u string i mice sa pocetka i kraja 
	}

	public void setHealth(int health) {
		if (health < 0) health = 0;
		if (health > 100) health = 100;
		this.health = health;
	}

	public String getName() { return name; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getHealth() { return health; }

	@Override
	public String toString() {
		return String.format("Player [%s] @ (%d,%d) %dx%d HP=%d", name, x, y, width, height, health);
	}
}

class Enemy {
	private String type;
	private int x, y;
	private int width, height;
	private int damage;

	public Enemy(String type, int x, int y, int width, int height, int damage) {
		setType(type);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setDamage(damage);
	}

	public void setType(String type) {
		this.type = type.trim().toLowerCase();
	}

	public void setDamage(int damage) {
		if (damage < 1) damage = 1;
		if (damage > 100) damage = 100;
		this.damage = damage;
	}

	public String getType() { return type; }
	public int getX() { return x; }
	public int getY() { return y; }
	public int getWidth() { return width; }
	public int getHeight() { return height; }
	public int getDamage() { return damage; }

	@Override
	public String toString() {
		return String.format("Enemy [%s] @ (%d,%d) %dx%d DMG=%d", type, x, y, width, height, damage);
	}
}

public class Game {
	private Player player;
	private ArrayList<Enemy> neprijatelj = new ArrayList<>(); //lista neprijatelja
	private ArrayList<String> eventLog = new ArrayList<>();  //dnevnik dogadjaja

	public Game(Player player) {
		this.player = player;
	}

	public boolean checkCollision(Player p, Enemy e) {  //prekopirali kod od projekta 1
		return p.getX() < e.getX() + e.getWidth() &&
			   p.getX() + p.getWidth() > e.getX() &&
			   p.getY() < e.getY() + e.getHeight() &&
			   p.getY() + p.getHeight() > e.getY();
	}

	public void decreaseHealth(Player p, Enemy e) {
		int oldHP = p.getHealth();  //uzimamo trenutno zdravlje igraca
		int newHP = Math.max(0, oldHP - e.getDamage());  //novo zdravlje nakon sto je igrac pogodjen
		p.setHealth(newHP); //zbog max ne pada ispod 0
		eventLog.add("Player hit by " + e.getType() + " for " + e.getDamage() +
					 " -> HP " + oldHP + " -> " + newHP);  //Type - daje tip koji ga neprijatelj napada
	}

	public void addEnemy(Enemy e) {
		neprijatelj.add(e);  //dodajemo napadaca
		eventLog.add("Enemy added: " + e.toString());
	}

	public ArrayList<Enemy> findByType(String query) {
		ArrayList<Enemy> found = new ArrayList<>();
		query = query.toLowerCase().trim();
		for (Enemy e : neprijatelj)
			if (e.getType().contains(query)) found.add(e);
		return found;
	}

	public ArrayList<Enemy> collidingWithPlayer() {
		ArrayList<Enemy> sudar = new ArrayList<>();
		for (Enemy e : neprijatelj)
			if (checkCollision(player, e)) sudar.add(e);  //proveravamo da li se sudaraju
		return sudar; 
	}

	public void resolveCollisions() {
		for (Enemy e : collidingWithPlayer())  //proveravamo iz prethpodne
			decreaseHealth(player, e);  //poziva se ako se sudara
	}
	
	public static Enemy enemyFromString(String data) {
		String[] djelovi = data.split(";");
	    String type = djelovi[0].trim();

	    // Dijeli koordinate i dimenzije na 3 dijela jer su "12,5,16x16"
	    String[] koordinate = djelovi[1].split(",");
	    int x = Integer.parseInt(koordinate[0].trim());
	    int y = Integer.parseInt(koordinate[1].trim());
	    

	    String[] dimenzije = koordinate[2].split("x");
	    int width = Integer.parseInt(dimenzije[0].trim());  //pretvara string u cijeli broj
	    int height = Integer.parseInt(dimenzije[1].trim());

	    int dmg = Integer.parseInt(djelovi[2].trim()); 

	    return new Enemy(type, x, y, width, height, dmg);
	    
	}   


	public static void main(String[] args) {
		Player p = new Player("   player   jedan  ", 10, 5, 32, 32, 85);
		Game g = new Game(p);

		Enemy e1 = new Enemy("orc", 50, 5, 16, 16, 15);
		Enemy e2 = enemyFromString("Goblin; 12,5,16x16;20");
		System.out.println("Enemy coordinates: (" + e2.getX() + ", " + e2.getY() + ")");
		g.addEnemy(e1);
		g.addEnemy(e2);

		g.findByType("gob");
		g.collidingWithPlayer();
		g.resolveCollisions();
	}
}