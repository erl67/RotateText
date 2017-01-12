package edu.pitt.is17.assignment3; 	// ERL 67 30SEP16,IS17_Assignment3

import javax.swing.JOptionPane;

public class RotateText {

	private static final int ROTFAC = 13;	//factor by which text is rotated [0-26]
	private static final int MAXLINE = 50;	//length at which lines should be broken

	public static void main(String[] args) {

		String userInput = "";				//string that holds the user input
		String rotatedInput = "";			//string that holds the results of ROT

		// while loop allows the program to run continuously until user decides to exit
		while (!userInput.equalsIgnoreCase("q")) {

			//get user input
			userInput = JOptionPane.showInputDialog("Enter the text to rotate, or \"Q\" to quit");

			//quit if user hits cancel or types q||Q
			if (userInput==null || userInput.equalsIgnoreCase("q")){
				System.out.println("Goodbye");
				System.exit(0);
			}

			rotatedInput = rotateText (userInput);	//call method to rotate the text

			//if string is longer than max length call method to break up lines
			if (rotatedInput.length() > MAXLINE) {
				rotatedInput = breakLines(MAXLINE, rotatedInput);
			}

			System.out.println("Your text rotation result is:\n" + userInput + "\n" + rotatedInput);	//print results
			
		}	// end while
		
	}	//end main

	//method that does ROT of user input based on the set ROT factor
	public static String rotateText (String userInput){
		String rotationResult = "";		//string that holds result of ROT
		char tempChar = 0;				//char for loop of each character in the string
		int tempCharVal = 0;			//ascii value of the character
		int newCharVal = 0;				//ascii value of the character after ROT
		char newChar = 0;				//char of the new ascii value to be added to the string of results

		//loop that cycles the length of the user input string and rotates the characters 
		for (int i=0; i<userInput.length(); i++){

			tempChar=userInput.charAt(i);
			tempCharVal = (int)tempChar;

			if ((tempCharVal >=65 && tempCharVal <=90) || (tempCharVal >= 97 && tempCharVal <= 122)){	//only runs for chars of letters

				//if statement that subtracts rotation factor if adding it would make value greater than Z, otherwise adds factor
				if (((tempCharVal >= 97) && (tempCharVal + ROTFAC > 122)) || ((tempCharVal <= 90) && (tempCharVal + ROTFAC > 90))) {
					newCharVal = tempCharVal - (26 - ROTFAC);	//26 is number of english letters, done this way to accommodate factors larger than 13			
				}
				else {
					newCharVal = tempCharVal + ROTFAC;
				}	//end if that adjusts by ROTfactor
			}	//end if that selects letters
			else {
				newCharVal = tempCharVal;	//keeps punctuation and other non-letter characters the same
			}

			newChar = (char)newCharVal;					//convert ascii value into a character
			rotationResult = rotationResult + newChar;	//string that accumulates rotated characters	
			//System.out.println(tempChar + " " + tempCharVal + " " + newCharVal + " " + newChar); //for testing purposes	
		}

		return rotationResult;	//return resulting ROT string to main
	}	//end rotate method

	//method to insert line breaks into text a designated intervals
	public static String breakLines (int maxLength, String rotatedInput){
		String brokenInput = "";	//string that holds the input separated by line breaks

		//loop that cycles through the string and inserts a line break after each 50 characters
		for (int i=0; i < rotatedInput.length(); i++){			
			if (((i!=0) && i % maxLength == 0)){	//if the position in the string is equally divisible by the maxLength adds a break 
				brokenInput = brokenInput + "\n";
			}
			brokenInput = brokenInput + rotatedInput.charAt(i);
		}

		return brokenInput;		//returns new string with the line breaks inserted
	} //end breakLines method

} //end class

//for testing abcdefghijklmnopqrstuvwxyz12345,.'"!@ ABCDEFGHIJKLMNOPQRSTUVWXYZ[}\n;