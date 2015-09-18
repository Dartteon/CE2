// CS2103T (AY2015/6 Semester 1)
// CE1
// Name: Baron Chan Wei Ming	
// Matric. No.: A0122545
// Program manipulates text in a file with five commands
// add, delete, display, clear and exit

import java.util.*;
import java.io.*;
import java.io.PrintWriter;

public class TextBuddy{
	
	//Display messages for the user	
	private static final String MESSAGE_WELCOME = "Welcome to TextBuddy! %1s is ready for use. ";
	private static final String MESSAGE_ENTER_COMMAND = "Enter command: ";
	private static final String MESSAGE_ADDED = "added to %1s: '%2s'";
	private static final String MESSAGE_DELETED = "deleted from %1s: '%2s'";
	private static final String MESSAGE_EMPTY = "%1s is empty";
	private static final String MESSAGE_SHOW_LINE = "%1s. %2s";
	private static final String MESSAGE_CLEAR_CONTENT = "all content deleted from %1s";
	private static final String MESSAGE_INCORRECT_INPUT = "command not recognized";
	private static final String MESSAGE_ERROR = "Caught IOException: %1s";
	
	//Commands user can input
	private static final String COMMANDTYPE_ADD = "Add";
	private static final String COMMANDTYPE_DELETE = "Delete";
	private static final String COMMANDTYPE_DISPLAY = "Display";
	private static final String COMMANDTYPE_CLEAR = "Clear";
	private static final String COMMANDTYPE_EXIT = "Exit";

	//Array positions of letters in string
	private static final int POS_FIRST_LETTER = 0;
	private static final int POS_SECOND_LETTER = 1;
	
	//Array to number and number to array offset
	private static final int OFFSET_ARRAY_TO_NUM = 1;
	
	//Parameter to split a string input into its command and input string
	private static final int PARAM_COMMAND_AND_STRING = 2;
	
	//Object variables to store user inputs and file name
	ArrayList<String> textsList;
	private String fileName;
	
	//Constructor; requires name of file to be save in to be passed as parameter
	public TextBuddy(String newFileName) { 
		textsList = new ArrayList<String>(); 
		fileName = newFileName;
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner(System.in);		
		System.out.println(String.format(MESSAGE_WELCOME, args[POS_FIRST_LETTER]));		
		TextBuddy textBuddy = new TextBuddy(args[POS_FIRST_LETTER]);		
		textBuddy.loadFile();
		textBuddy.scanInputs(sc);		
	}
	
	/*Method takes in a scanner and continuously scans for input lines
	and terminates when the method it calls returns false*/
	private void scanInputs(Scanner sc){
		boolean isExitCalled = false;
		while(!isExitCalled){
			System.out.print(MESSAGE_ENTER_COMMAND);
			String line = sc.nextLine();
			if (!evaluateCommand(line))
				isExitCalled = true;
		}
	}
	
	/*Method splits input lines and calls different methods depending on
	the first word of line. If first word is 'exit' it returns false,
	otherwise it executes the intended command then returns true*/
	boolean evaluateCommand(String line){
		String lineArray[] = line.split(" ", PARAM_COMMAND_AND_STRING);
		String firstWord = lineArray[POS_FIRST_LETTER];
		
		if (firstWord.equals(COMMANDTYPE_ADD)){
			addString(lineArray[POS_SECOND_LETTER]);			
			return true;
		}
		else if (firstWord.equals(COMMANDTYPE_DELETE)){
			deleteLine(lineArray[POS_SECOND_LETTER]);
			return true;
		}
		else if (firstWord.equals(COMMANDTYPE_DISPLAY)){
			displayAll();
			return true;
		}
		else if (firstWord.equals(COMMANDTYPE_CLEAR)){
			clearAll();
			return true;
		}	
		else if (firstWord.equals(COMMANDTYPE_EXIT)){
			return false;
		}
		else{
			System.out.println(MESSAGE_INCORRECT_INPUT);
			return true;
		}
	}
	
	//Method adds string to ArrayList then saves into file
	void addString(String line){
		textsList.add(line);
		System.out.println(String.format(MESSAGE_ADDED, fileName, line));
		saveFile();
	}
	
	//Method deletes string of specified index from ArrayList then saves into file
	void deleteLine(String line){
		int lineToDelete = Integer.parseInt(line) - OFFSET_ARRAY_TO_NUM;
		String deletedLine = textsList.remove(lineToDelete);
		System.out.println(String.format(MESSAGE_DELETED, fileName, deletedLine));
		saveFile();
	}
	
	//Method checks if list is empty, else calls list to be printed
	void displayAll(){
		if (textsList.isEmpty())
			System.out.println(String.format(MESSAGE_EMPTY, fileName));
		else{
			printAllLines();
		}
	}
	
	//Method displays all text currently in ArrayList
	void printAllLines(){
		for (int i=0; i<textsList.size(); i++){
			System.out.println(String.format(MESSAGE_SHOW_LINE, (i+OFFSET_ARRAY_TO_NUM), textsList.get(i)));
		}
	}
	

	//Method deletes all text currently in ArrayList then saves into file
	private void clearAll(){
		textsList.clear();		
		System.out.println(String.format(MESSAGE_CLEAR_CONTENT, fileName));
		saveFile();		
	}
	
	//Method loads file if file specified exists in same directory as Java program
	private void loadFile(){
		File file = new File(fileName);
		if (file.exists()){
			loadFileContents(file);
		}
			
	}
	
	//Method reads all lines in file and adds them to object's textsList
	void loadFileContents(File file){
		try{
			BufferedReader fileReader = new BufferedReader(new FileReader(fileName));
			String readingLine = fileReader.readLine();
			while (readingLine != null){
				textsList.add(readingLine);
				readingLine = fileReader.readLine();
			}
			fileReader.close();
		}
		catch (IOException e){
			System.out.println(String.format(MESSAGE_ERROR, e.getMessage()));
		}
		finally{
		}
	
	}
	
	//Method combines texts in ArrayList then calls it to be saved
	private void saveFile(){
		StringBuilder textCollage = combineTextsList();
		writeToFile(textCollage);
	}
	
	//Method saves textCollage into file
	void writeToFile(StringBuilder textCollage){
		try{		
			PrintWriter outFile = new PrintWriter(fileName);
			outFile.write(textCollage.toString());
			outFile.close( );
		} catch (IOException e) {
			System.out.println(String.format(MESSAGE_ERROR, e.getMessage()));
		}finally{
		}
	}
	
	//Method combines all lines in textsList into StringBuilder
	StringBuilder combineTextsList(){
		StringBuilder textCollage = new StringBuilder();
		for (int i=0; i<textsList.size(); i++){
			textCollage.append(textsList.get(i));			
			textCollage.append(System.getProperty("line.separator"));
		}
		return textCollage;
	}
	
	ArrayList<String> searchTexts(String word){
		ArrayList<String> linesWithSearchWord = new ArrayList<String>();
		for (int i=0; i<textsList.size(); i++){
			if (textsList.get(i).contains(word)){
				linesWithSearchWord.add(textsList.get(i));
			}
		
		}
	return linesWithSearchWord;
}
	
}

