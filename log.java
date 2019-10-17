import java.io.*;
import java.util.*;

public class log {
	private String fileName;
	private String userword;
	private int size;
	private int filled;
	private ArrayList<ArrayList<String>> logins;
	
	public log(String un, String pass, String name) throws FileNotFoundException {
		userword = hash(un + pass);
		fileName = "resources/" + name + ".txt";
		
		Scanner fReader = new Scanner(new File(fileName));
		size = Integer.parseInt(fReader.next());
		filled = Integer.parseInt(fReader.next());
		
		logins = new ArrayList<ArrayList<String>>(size + 1);
		
		String hold = "";
		
		for(int i = 0; i < size; i++) {
			logins.add(i, new ArrayList<String>());
		}
		
		for(int i = 0; i < size; i++) {
			hold = fReader.next();
			String splHold[] = hold.split(",");
			
			for(int j = 0; j < splHold.length; j++) {
				logins.get(i).add(splHold[j]);
			}
		}
		
		fReader.close();
	}
	
	public void logIn() throws FileNotFoundException, InterruptedException {
		if(filled == 0) {
			System.err.println("Error: No logins yet, creating new login");
			newLog();
		}		
		else {
			for(int i = 0; i < size; i++) {
				if(logins.get(i).contains(userword)) {
					System.out.println("Login successful");
					System.exit(0);
				}
			}
			
			Scanner kbReader = new Scanner(System.in);
			
			System.err.println("No login information found");
			System.err.println("Create new login? y/n");
			
			if(kbReader.nextLine().equalsIgnoreCase("y")) {
				newLog();
				kbReader.close();
			}
			else {
				kbReader.close();
				System.exit(0);
			}
		}
	}
	
	public void newLog() throws FileNotFoundException, InterruptedException {
		PrintWriter print = new PrintWriter(fileName);
		
		if(logins.get(Integer.parseInt(userword)%size).get(0).equals("null")) {
			logins.get(Integer.parseInt(userword)%size).remove(0);
		}
		
		logins.get(Integer.parseInt(userword)%size).add(userword);
		
		print.println(size + " 1");
		
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < logins.get(i).size(); j++) {
				print.print(logins.get(i).get(j) + ",");
			}
			print.println();
		}
		
		print.close();
		
		System.out.println("Logging in with new login");
		System.out.print(".");
		Thread.sleep(1000);
		System.out.print("\t.");
		Thread.sleep(1000);
		System.out.print("\t.");
		Thread.sleep(1500);
		System.out.println();
		
		logIn();
	}
	
	public String hash(String word) {
		int hashed = 0;
		char chars[] = word.toCharArray();
		for(char c : chars) {
			hashed += (c * 21) - (c + 11);
		}
		return "" + Math.abs(hashed);
	}
	
	
	public static void newFile(String name, int size) throws FileNotFoundException{
		File newFile = new File("resources/" + name + ".txt");
		PrintWriter print = new PrintWriter(newFile);
		
		print.println(size + " 0");
		
		for(int i = 0; i < size; i++) {
			print.println("null,");
		}
		
		print.close();
	}
}
