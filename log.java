import java.io.*;
import java.util.*;

public class log {
	private String fileName;
	private String userword;
	private int size;
	private int filled;
	private ArrayList<String> logins;
	
	public log(String un, String pass, String name) throws FileNotFoundException {
		userword = hash(un + pass);
		fileName = "resources/" + name + ".txt";
		
		Scanner fReader = new Scanner(new File(fileName));
		size = Integer.parseInt(fReader.next());
		filled = Integer.parseInt(fReader.next());
		
		logins = new ArrayList<String>(size + 1);
		
		for(int i = 0; i < size; i++) {
			logins.add(fReader.next());
		}
		
		fReader.close();
	}
	
	public void logIn() throws FileNotFoundException, InterruptedException {
		if(filled == 0) {
			System.err.println("Error: No logins yet, creating new login");
			newLog();
		}		
		else {
			if(logins.contains(userword)) {
				System.out.println("Login successful");
				System.exit(0);
			}
			
			else {
				System.err.println("No login information found");
				System.err.println("Create new login? y/n");
				
				Scanner kbReader = new Scanner(System.in);
				
				if(kbReader.next().equalsIgnoreCase("y")) {
					newLog();
					kbReader.close();
				}
				else {
					kbReader.close();
					System.exit(0);
				}
			}
		}
	}
	
	public void newLog() throws FileNotFoundException, InterruptedException {
		if(filled == size) {
			System.err.println("Error: Database full");
			System.exit(0);
		}
		
		PrintWriter print = new PrintWriter(fileName);
		
		logins.set(filled, userword);
		filled++;
		
		print.println(size + " " + filled);
		
		for(int i = 0; i < size; i++) {
			print.println(logins.get(i));
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
			print.println("null");
		}
		
		print.close();
	}
}
