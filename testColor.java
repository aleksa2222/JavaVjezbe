
public class testColor {

	public static void main(String[] args) {
		
		Color c = new Color(120,200,100);
		
		c.printColor();
		c.addRed(50);
		c.addGreen(-250);
		c.addBlue(100);
		
		c.printColor();
		
		c.addRed(12);
		c.addGreen(32);
		c.addBlue(45);
		
		c.printColor();

	}

}
