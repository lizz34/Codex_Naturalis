import java.io.File;
import java.io.FileNotFoundException; 
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		try {
			File carteStarter = new File("C:\\Users\\Raul\\Desktop\\ProgettoCodex\\Codex\\src\\carteStarter.txt");
			Scanner scanner = new Scanner(carteStarter);
			while (scanner.hasNextLine()) {
				String data = scanner.nextLine();
				System.out.println(data);
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

	}

}
