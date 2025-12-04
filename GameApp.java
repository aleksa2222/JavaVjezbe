import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GameApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameFrame frame = new GameFrame();
            frame.setVisible(true);
        });
    }
}

class GameFrame extends JFrame {
    private JTextField txtName;
    private JTextField txtHealth;
    private JTextField txtX;
    private JTextField txtY;
    private JTextField txtCsvPath;
    private JRadioButton rbRect;
    private JRadioButton rbCircle;
    private JTextArea txtOutput;

    public GameFrame() {
        setTitle("Projekat 5 - Igra");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 4, 4, 4);
        gbc.anchor = GridBagConstraints.WEST;

        txtName = new JTextField(15);
        txtHealth = new JTextField(5);
        txtX = new JTextField(5);
        txtY = new JTextField(5);
        txtCsvPath = new JTextField("enemies.csv", 15);

        rbRect = new JRadioButton("Pravougaoni kolajder", true);
        rbCircle = new JRadioButton("Kružni kolajder");
        ButtonGroup group = new ButtonGroup();
        group.add(rbRect);
        group.add(rbCircle);

        int row = 0;

        gbc.gridx = 0; gbc.gridy = row;
        inputPanel.add(new JLabel("Ime igrača:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtName, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        inputPanel.add(new JLabel("Health igrača (0-100):"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtHealth, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        inputPanel.add(new JLabel("Pozicija X:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtX, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        inputPanel.add(new JLabel("Pozicija Y:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtY, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        inputPanel.add(new JLabel("CSV fajl:"), gbc);
        gbc.gridx = 1;
        inputPanel.add(txtCsvPath, gbc);
        row++;

        gbc.gridx = 0; gbc.gridy = row;
        inputPanel.add(new JLabel("Kolajder:"), gbc);
        gbc.gridx = 1;
        JPanel colliderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        colliderPanel.add(rbRect);
        colliderPanel.add(rbCircle);
        inputPanel.add(colliderPanel, gbc);
        row++;

        JButton btnStart = new JButton("Pokreni igru");
        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        inputPanel.add(btnStart, gbc);

        txtOutput = new JTextArea();
        txtOutput.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(txtOutput);

        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startGame();
            }
        });
    }

    private void startGame() {
        try {
            String name = txtName.getText();
            int health = Integer.parseInt(txtHealth.getText().trim());
            int x = Integer.parseInt(txtX.getText().trim());
            int y = Integer.parseInt(txtY.getText().trim());
            String csvPath = txtCsvPath.getText().trim();

            Collidable collider;
            if (rbRect.isSelected()) {
                collider = new RectangleCollider(x, y, 32, 32);
            } else {
                collider = new CircleCollider(x, y, 16);
            }

            Player player = new Player(name, health, x, y, collider);

            List<Enemy> enemies = Game.loadEnemiesFromCSV(csvPath);
            Game game = new Game(player);
            for (Enemy enemy : enemies) {
                game.addEnemy(enemy);
            }

            game.resolveCollisions();

            StringBuilder sb = new StringBuilder();
            sb.append("IGRAČ:\n");
            sb.append(player).append("\n\n");

            sb.append("SVI NEPRIJATELJI:\n");
            for (Enemy enemy : game.getEnemies()) {
                sb.append(enemy).append("\n");
            }
            sb.append("\nNEPRIJATELJI U KOLIZIJI SA IGRAČEM:\n");
            for (Enemy enemy : game.collidingWithPlayer()) {
                sb.append(enemy).append("\n");
            }

            sb.append("\nLOG DOGAĐAJA:\n");
            for (String logLine : game.getLog()) {
                sb.append(logLine).append("\n");
            }

            txtOutput.setText(sb.toString());

            if (player.getHealth() <= 0) {
                JOptionPane.showMessageDialog(this,
                        "Igrač je poražen!",
                        "Kraj igre",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Igrač je preživio napade!",
                        "Kraj igre",
                        JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Greška pri parsiranju brojeva. Provjeri X, Y i health.",
                    "Greška",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this,
                    "Greška: " + ex.getMessage(),
                    "Greška",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    "Greška pri čitanju CSV fajla: " + ex.getMessage(),
                    "Greška",
                    JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Neočekivana greška: " + ex.getClass().getSimpleName()
                            + "\nPoruka: " + ex.getMessage(),
                    "Neočekivana greška",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}

interface Collidable {
    boolean intersects(Collidable other);
}

abstract class GameObject {
    private int x;
    private int y;
    private Collidable collider;

    public GameObject(int x, int y, Collidable collider) {
        setX(x);
        setY(y);
        setCollider(collider);
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

    public void setY(int y) {
        this.y = y;
    }

    public Collidable getCollider() {
        return collider;
    }

    public void setCollider(Collidable collider) {
        if (collider == null) {
            throw new IllegalArgumentException("Collider ne smije biti null");
        }
        this.collider = collider;
    }

    public boolean intersects(GameObject other) {
        if (other == null || this.collider == null || other.collider == null) return false;
        return this.collider.intersects(other.collider);
    }

    public abstract String getDisplayName();

    @Override
    public String toString() {
        return getDisplayName() + " @(" + x + "," + y + ")";
    }
}

class RectangleCollider implements Collidable {
    private int x;
    private int y;
    private int width;
    private int height;

    public RectangleCollider(int x, int y, int width, int height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Dimenzije pravougaonika moraju biti > 0");
        }
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean intersects(Collidable other) {
        if (other instanceof RectangleCollider) {
            RectangleCollider r = (RectangleCollider) other;
            return x < r.x + r.width &&
                    x + width > r.x &&
                    y < r.y + r.height &&
                    y + height > r.y;
        } else if (other instanceof CircleCollider) {
            return other.intersects(this);
        }
        return false;
    }
}

class CircleCollider implements Collidable {
    private int centerX;
    private int centerY;
    private int radius;

    public CircleCollider(int centerX, int centerY, int radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Poluprečnik mora biti > 0");
        }
        this.centerX = centerX;
        this.centerY = centerY;
        this.radius = radius;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getRadius() {
        return radius;
    }

    @Override
    public boolean intersects(Collidable other) {
        if (other instanceof CircleCollider) {
            CircleCollider c = (CircleCollider) other;
            int dx = centerX - c.centerX;
            int dy = centerY - c.centerY;
            int distSq = dx * dx + dy * dy;
            int radSum = radius + c.radius;
            return distSq <= radSum * radSum;
        } else if (other instanceof RectangleCollider) {
            RectangleCollider rect = (RectangleCollider) other;
            int closestX = clamp(centerX, rect.getX(), rect.getX() + rect.getWidth());
            int closestY = clamp(centerY, rect.getY(), rect.getY() + rect.getHeight());
            int dx = centerX - closestX;
            int dy = centerY - closestY;
            return dx * dx + dy * dy <= radius * radius;
        }
        return false;
    }

    private static int clamp(int value, int min, int max) {
        if (value < min) return min;
        if (value > max) return max;
        return value;
    }
}

class Player extends GameObject {
    private String name;
    private int health;

    public Player(String name, int health, int x, int y, Collidable collider) {
        super(x, y, collider);
        setName(name);
        setHealth(health);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Ime ne smije biti null");
        }
        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Ime ne smije biti prazno");
        }
        char first = Character.toUpperCase(trimmed.charAt(0));
        String rest = (trimmed.length() > 1) ? trimmed.substring(1) : "";
        this.name = first + rest;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0 || health > 100) {
            throw new IllegalArgumentException("Health igrača mora biti u opsegu 0-100");
        }
        this.health = health;
    }

    @Override
    public String getDisplayName() {
        return name;
    }

    @Override
    public String toString() {
        return "Player{name='" + name + "', health=" + health +
                ", position=(" + getX() + "," + getY() + ")}";
    }
}

interface Attacker {
    int getEffectiveDamage();
}

class Enemy extends GameObject implements Attacker {
    private String type;
    private int damage;
    private int health;

    public Enemy(String type, int damage, int health, int x, int y, Collidable collider) {
        super(x, y, collider);
        setType(type);
        setDamage(damage);
        setHealth(health);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Type ne smije biti prazan");
        }
        this.type = type.trim();
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        if (damage < 0) {
            throw new IllegalArgumentException("Damage neprijatelja mora biti >= 0");
        }
        this.damage = damage;
    }

    public int getEnemyHealth() {
        return health;
    }

    public void setHealth(int health) {
        if (health < 0) {
            throw new IllegalArgumentException("Health neprijatelja mora biti >= 0");
        }
        this.health = health;
    }

    @Override
    public int getEffectiveDamage() {
        return damage;
    }

    @Override
    public String getDisplayName() {
        return type;
    }

    @Override
    public String toString() {
        return "Enemy{type='" + type + "', damage=" + damage +
                ", health=" + health +
                ", position=(" + getX() + "," + getY() + ")}";
    }
}

class MeleeEnemy extends Enemy {
    public MeleeEnemy(String type, int damage, int health, int x, int y, Collidable collider) {
        super(type, damage, health, x, y, collider);
    }
}

class BossEnemy extends Enemy {
    public BossEnemy(String type, int damage, int health, int x, int y, Collidable collider) {
        super(type, damage, health, x, y, collider);
    }

    @Override
    public int getEffectiveDamage() {
        return super.getEffectiveDamage() * 2;
    }

    @Override
    public String toString() {
        return "BOSS " + super.toString();
    }
}

class Game {
    private Player player;
    private List<Enemy> enemies;
    private List<String> log;

    public Game(Player player) {
        if (player == null) {
            throw new IllegalArgumentException("Player ne smije biti null");
        }
        this.player = player;
        this.enemies = new ArrayList<>();
        this.log = new ArrayList<>();
    }

    public Player getPlayer() {
        return player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<String> getLog() {
        return log;
    }

    public boolean checkCollision(Player p, Enemy e) {
        return p.intersects(e);
    }

    public void decreaseHealth(Player p, Enemy e) {
        int before = p.getHealth();
        int damage = e.getEffectiveDamage();
        int after = before - damage;
        if (after < 0) after = 0;
        p.setHealth(after);
        log.add("Sudar sa " + e.getType() + " (" + e.getClass().getSimpleName() + ")" +
                " -> -" + damage + " HP (" + before + " -> " + after + ")");
    }

    public void addEnemy(Enemy e) {
        enemies.add(e);
        log.add("Dodat neprijatelj: " + e);
    }

    public List<Enemy> collidingWithPlayer() {
        List<Enemy> list = new ArrayList<>();
        for (Enemy e : enemies) {
            if (checkCollision(player, e)) {
                list.add(e);
            }
        }
        return list;
    }

    public void resolveCollisions() {
        for (Enemy e : enemies) {
            if (checkCollision(player, e)) {
                decreaseHealth(player, e);
            }
        }
    }

    private static String getPart(String[] parts, int index) {
        if (index < parts.length) {
            return parts[index].trim();
        }
        return "";
    }

    public static List<Enemy> loadEnemiesFromCSV(String filePath) throws IOException {
        List<Enemy> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNum = 0;
            while ((line = br.readLine()) != null) {
                lineNum++;
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split(",");

                if (lineNum == 1 && parts[0].trim().equalsIgnoreCase("type")) {
                    continue;
                }

                String type = getPart(parts, 0);
                String clazz = getPart(parts, 1).toLowerCase();
                String dmgStr = getPart(parts, 2);
                String healthStr = getPart(parts, 3);
                String xStr = getPart(parts, 4);
                String yStr = getPart(parts, 5);
                String shape = getPart(parts, 6).toLowerCase();
                String widthStr = getPart(parts, 7);
                String heightStr = getPart(parts, 8);
                String radiusStr = getPart(parts, 9);

                if (type.isEmpty() || clazz.isEmpty() || dmgStr.isEmpty() || healthStr.isEmpty()
                        || xStr.isEmpty() || yStr.isEmpty() || shape.isEmpty()) {
                    throw new IllegalArgumentException("Linija " + lineNum + ": nedostaje neka obavezna kolona -> " + line);
                }

                try {
                    int damage = Integer.parseInt(dmgStr);
                    int health = Integer.parseInt(healthStr);
                    int x = Integer.parseInt(xStr);
                    int y = Integer.parseInt(yStr);

                    Collidable collider;
                    if (shape.equals("rectangle") || shape.equals("rect")) {
                        if (widthStr.isEmpty() || heightStr.isEmpty()) {
                            throw new IllegalArgumentException("Linija " + lineNum + ": rectangle zahtijeva width i height -> " + line);
                        }
                        int width = Integer.parseInt(widthStr);
                        int height = Integer.parseInt(heightStr);
                        collider = new RectangleCollider(x, y, width, height);
                    } else if (shape.equals("circle")) {
                        if (radiusStr.isEmpty()) {
                            throw new IllegalArgumentException("Linija " + lineNum + ": circle zahtijeva radius -> " + line);
                        }
                        int radius = Integer.parseInt(radiusStr);
                        collider = new CircleCollider(x, y, radius);
                    } else {
                        throw new IllegalArgumentException("Linija " + lineNum + ": nepoznat shape '" + shape + "' -> " + line);
                    }

                    Enemy enemy;
                    if (clazz.equals("melee")) {
                        enemy = new MeleeEnemy(type, damage, health, x, y, collider);
                    } else if (clazz.equals("boss")) {
                        enemy = new BossEnemy(type, damage, health, x, y, collider);
                    } else {
                        throw new IllegalArgumentException("Linija " + lineNum + ": nepoznata class vrijednost '" + clazz + "' -> " + line);
                    }

                    result.add(enemy);
                } catch (NumberFormatException ex) {
                    throw new IllegalArgumentException("Linija " + lineNum + ": greška pri parsiranju broja -> " + line);
                }
            }
        }
        return result;
    }
}
