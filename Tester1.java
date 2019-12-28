import java.util.*;
import java.io.*;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Scanner kbReader = new Scanner(System.in);
		
		System.out.println("Name of file? Put \"New\" and a name for a new file and the size (with spaces between) or type"
				+ "file name");
		String file = kbReader.nextLine();
		String name[] = file.split(" ");
		
		if(name.length == 3) {
			log.newFile(name[1], Integer.parseInt(name[2]));
			file = name[1];
		}
		
		System.out.println("New login? y/n");
		String newLog = kbReader.nextLine();
		
		System.out.println("Username:");
		String username = kbReader.next();
		System.out.println("Password:");
		String password = kbReader.next();
		
		log logger = new log(username, password, file);
		
		if(newLog.equalsIgnoreCase("y")) {
			logger.newLog();
		}
		else {
			logger.logIn();
		}
		
		kbReader.close();
	}

}
