
public class Color {
	
	private int red;
	private int green;
	private int blue;
	
	public Color(int red, int green, int blue) {
		setRed(red);
		setGreen(green);
		setBlue(blue);
	}
	
	public int getRed() {
		return red;
	}
	
	public int getGreen() {
		return green;
	}
	
	public int getBlue() {
		return blue;
	}
	
	public void setRed(int red) {
		if(red >= 0 && red <= 255) {
			this.red = red;
		}
		else {
			System.out.println("Greska: red mora biti izmedju 0 i 255");
		}
	}
	
	public void setGreen(int green) {
		if(green >= 0 && green <= 255) {
			this.green = green;
		}else {
			System.out.println("Greska: green mora biti izmedju 0 i 255");
		}
	}
	
	public void setBlue(int blue) {
		if(blue >= 0 && blue <= 255) {
			this.blue = blue;
		}else {
			System.out.println("Greska: blue mora biti zimedju 0 i 255");
		}
	}
	
	public void addRed(int i) {
		red += i;
		if(red > 255) {
			System.out.println("Vrijednoat red je presal granicu 255, postavljam na 255.");
			red = 255;
		}else if(red < 0) {
			System.out.println("Vrijednost red je ispod granice 0, postavljam red na 0");
			red = 0;
		}
	}
	
	public void addGreen(int i) {
		green += i;
		if(green > 255) {
			System.out.println("Vrijednost green je presla granicu od 255, postavljam green na 255");
			green = 255;
		}else if (green < 0){
			System.out.println("Vrijednost gree je pala ispod 0, postavljam green na 0");
			green = 0;
		}
	}
	
	public void addBlue(int i) {
		blue += i;
		if(blue > 255) {
			System.out.println("Vrijednost blue je presla granicu od 255, postavljam vrijednost blue na 255");
			blue = 255;
		}else if(blue < 0) {
			System.out.println("Vrijednost blue je pala ispod granice 0, postavljam vrijednost blue na 0");
			blue = 0;
		}
	}
	
	public void printColor() {
		System.out.println("red: " + red + ", green: " + green + ", blue: " + blue);
	}
}
