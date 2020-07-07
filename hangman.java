import java.util.*;
import java.io.*;

public class hangman {
	ArrayList<Character> letters;
	ArrayList<Character> not;
	String current;
	String answer;
	boolean in;
	public void run() throws Exception {
		Scanner file = new Scanner(new File("hangman.in"));
		Scanner sc = new Scanner(System.in);
		answer = file.nextLine().trim();
		current = "";
		for (int i = 0; i<answer.length(); i++) {
			char a = answer.charAt(i);
			if (!Character.isLetter(a)) current+=a;
			else current+="*";
		} //creating current
		
		System.out.println(current);
		
		if (current.indexOf('*') == -1) {
			System.out.println("There is nothing to guess! Restart");
			return;
		} //if the input is just symbols
		
		not = new ArrayList<Character>();
		letters = new ArrayList<Character>();
		//not == letters not in the answer
		//letters == contains all the letters. Takes it out once guessed
		for (int i = 97; i<97+26; i++) {
			letters.add((char) i);
		} //initializing letters
		in = false; //turns on when guessed a wrong letter. Prints the "Letters not in...."
		while(not.size()<6) {
			System.out.println();
			String a = sc.nextLine().toLowerCase().trim();
			if (a.equals(answer.toLowerCase())) {
					current = answer;
					printans();
					return;
			}
			if (notin(a)) continue;
			char b = a.charAt(0);
			
			letters.remove(letters.indexOf(b)); //removing letter from list of letters
			
			if (answer.toLowerCase().indexOf(b)<0) {
				not.add(b);
				System.out.println("This letter is not in the string!");
				System.out.print("Guesses that aren't in the String: ");
				for (char x: not) {
					System.out.print(x + " ");
				}
				System.out.println(); //printing out the guesses that are not in the string
				System.out.println("Number of wrong guesses left: " + (6-not.size()));
				System.out.println(current);
				in = true;
			} // letter not in the answer
			
			else {
				for (int i =0 ; i<current.length(); i++) {
					if (answer.toLowerCase().charAt(i) == (b)) {
						current = current.substring(0,i) + answer.charAt(i) + current.substring(i+1);
					} //changing the current to the updated one if letter is in the answer
				}
				if (not.size() >= 6) break; //too many guesses
				
				if (in) {
					System.out.print("Guesses that aren't in the String: ");
					for (char x: not) {
						System.out.print(x + " ");
					}
					System.out.println();
				} //printing out the guesses that are not in the string
			
				if (answer.equals(current)) {
					printans();
					return;
				} //if guessed the final answer
				
				else {
					System.out.print("Current string: ");
					System.out.println(current);
				} //prints out the updated one
			}
		}
			System.out.println("You took too many tries!");
			System.out.println("----------------------------------------------");
			System.out.println();
			System.out.println("Answer: \""+ answer + "\"");
	}
	public boolean notin(String a) {
		if (a.length()!=1) {
			System.out.println("Invalid Input: Only one letter please");
			return true;
		} //guessing something longer than a letter
	
		char b = a.charAt(0);
		
		if (!Character.isLetter(b)) {
			System.out.println("Not a Letter: Only one letter please");
			return true;
		} //guessing something that isn't a letter
		
		if (!letters.contains(b)) {
			System.out.println("You've already guessed this letter. Input another letter please");
			return true;
		} //if letter is already guessed
		
		return false;
	}
	public void printans() {
		System.out.println("That is the answer!");
		System.out.println("----------------------------------------------");
		System.out.println("Answer: \"" + current + "\"");
	}
	public static void main(String[] args) throws Exception {
		 hangman x = new hangman();
		 x.run();
	}
}