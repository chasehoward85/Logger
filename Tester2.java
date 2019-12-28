import java.util.*;
import java.io.*;

public class Tester {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Scanner kbReader = new Scanner(System.in);
		
		String file = "";
		
		System.out.println("New file? y/n");
		
		if(kbReader.next().equalsIgnoreCase("y")) {
			System.out.println("File name:");
			file = kbReader.next();
			
			System.out.println("Database size:");
			int size = kbReader.nextInt();
			
			log.newFile(file, size);
		}
		else {
			System.out.println("File name:");
			file = kbReader.next();
		}
		
		System.out.println("New login? y/n");
		String newLog = kbReader.next();
		
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
