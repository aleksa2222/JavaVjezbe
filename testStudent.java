
public class testStudent {

	public static void main(String[] args) {
		
		Student s1 = new Student("Marko", "Maric", "IB123", 10, 9, 9);
		Student s2 = new Student("Ana", "Jovic", "IB124",  8, 9, 7);
		Student s3= new Student("Luka", "Petrovic", "IB125", 10, 10, 10);
		Student s4 = new Student("Sara", "Nikolic", "IB126", 9, 9, 10);
		
		System.out.println("Spisak studenata:");
		s1.ispisInformacija();
		s2.ispisInformacija();
		s3.ispisInformacija();
		s4.ispisInformacija();
		
		System.out.println("\nUkupan broj studenata: " + Student.brojStudenata);

	}

}
