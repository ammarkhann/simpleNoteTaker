import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Ammar Khan Bachelors in Computer Science S005428
 * 
 */

public class NoteCat {

	public static void main(String[] args) throws IOException {

		userSelectionMenu();
	}

	public static void userSelectionMenu() throws IOException {

		printUserMenu();

		Scanner scanner = new Scanner(System.in);

		System.out.print("\n>>>");

		String userSelection = scanner.nextLine();

		if (userSelection.equals("1")) {
			createNewNote();

		} else if (userSelection.equals("2")) {
			showUserNote();

		} else if (userSelection.equals("3")) {
			appendToNote();

		} else if (userSelection.equals("4")) {
			deleteNote();
		} else if (userSelection.equals("5")) {
			listAllNotes();
		} else if (userSelection.equals("6")) {
			renameNote();
		} else if (userSelection.equals("7")) {
			userSelectionMenu();
		} else if (userSelection.equals("8")) {
			System.exit(0);
		} else {
			System.out.println("Wrong Selection. Select again.");
			userSelectionMenu();
		}

	}

	public static void printUserMenu() {

		System.out.println("\nWelcome to Simple Note Taker.");
		System.out.println("You can select from the following options.");
		System.out.println("Select 1, 2, 3 or 4 accordingly to your choice");
		System.out.println("\nOPTIONS:");
		System.out
				.println("1)new <note>   Create new note.End your note with \"#End\" ");
		System.out.println("2)show <note>  Display existing note");
		System.out.println("3)append <note> Append to existing note");
		System.out.println("4)delete <note> Delete existing note");
		System.out
				.println("5)list <note> or <all> 	List existing note or all notes");
		System.out.println("6)rename <note> Rename existing note");
		System.out.println("7)help         Prints this help menu");
		System.out.println("8)exit         Terminates NoteCat");

	}

	public static void createNewNote() throws IOException {
		printNewNoteHelp();
		Scanner scanner = new Scanner(System.in);
		System.out.print("\n>>>");
		String userInput = scanner.nextLine();
		try {
			String[] splitUserInput = userInput.split(" ", -1);
			String userFirstWord = splitUserInput[0];
			String userLastWord = splitUserInput[1];
			if (userFirstWord.equals("new") && !(userLastWord.isEmpty())
					&& !(splitUserInput.length > 2)) {
				File userNoteFile = new File(userLastWord + ".ncat");
				if (userNoteFile.exists()) {
					System.out
							.println("The note with the same name already exists.");
					System.out.println("Enter a new name for your note");
					createNewNote();
				}
				Scanner scan = new Scanner(System.in);
				System.out.print("\n>>> Enter your note: ");
				String userNote = scan.nextLine();
				if (!(userNote.contains("#END"))) {
					System.out
							.println("\nMake sure you are ending your note with correct end statement");
					createNewNote();
				}
				FileWriter fileWriter = new FileWriter(userNoteFile);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				while (userNote.contains("#END")) {
					String[] splitUserNote = userNote.split("#END");
					bufferedWriter.write(splitUserNote[0]);
					bufferedWriter.flush();
					bufferedWriter.close();
					System.out.println("\nNote taken successfully");
					userSelectionMenu();
				}
			} else if (splitUserInput.length > 2 || splitUserInput.length <= 1
					|| !(userFirstWord.equals("new"))) {
				System.out.println("\nInvalid number of arguments");
				createNewNote();
			}
		} catch (Exception e) {
			System.out.println("\nInvalid number of arguments");
			createNewNote();
		}
	}

	public static void showUserNote() throws IOException {

		printShowNoteHelp();
		Scanner scanner = new Scanner(System.in);

		System.out.print("\n>>>");
		String userInput = scanner.nextLine();
		try {
			String[] splitUserInput = userInput.split(" ", -1);

			String userFirstWord = splitUserInput[0];
			String userLastWord = splitUserInput[1];

			if (userFirstWord.equals("show") && !(userLastWord.isEmpty())
					&& !(splitUserInput.length > 2)) {

				File userNoteFile = new File(userLastWord + ".ncat");

				if (userNoteFile.exists()) {
					try {
						FileReader fileReader = new FileReader(userNoteFile);
						BufferedReader bufferedReader = new BufferedReader(
								fileReader);

						String userNote = null;

						while ((userNote = bufferedReader.readLine()) != null) {
							System.out.println(userNote);

							bufferedReader.close();
							fileReader.close();
							userSelectionMenu();
						}
					} catch (IOException e) {
						System.err.println("An IOException was caught :"
								+ e.getMessage());
					}
				} else {
					System.out
							.println("\nThe note with the name provided does not exist.");
					System.out
							.println("Make sure you are entering the correct note name");
					showUserNote();
				}

			} else if (splitUserInput.length > 2 || splitUserInput.length <= 1
					|| !(userFirstWord.equals("show"))) {
				System.out.println("\nInvalid number of arguments");
				showUserNote();
			}
		} catch (Exception e) {
			System.out.println("Invalid number of arguments");
			showUserNote();
		}

	}

	public static void appendToNote() throws IOException {

		printAppendNoteHelp();
		Scanner scanner = new Scanner(System.in);

		System.out.print("\n>>>");
		String userInput = scanner.nextLine();
		try {
			String[] splitUserInput = userInput.split(" ", -1);

			String userFirstWord = splitUserInput[0];
			String userLastWord = splitUserInput[1];

			if (userFirstWord.equals("append") && !(userLastWord.isEmpty())
					&& !(splitUserInput.length > 2)) {
				File userNoteFile = new File(userLastWord + ".ncat");

				Scanner scan = new Scanner(System.in);
				System.out.print("\n>>> Enter your note: ");
				String userNote = scan.nextLine();

				if (!(userNote.contains("#END"))) {

					System.out
							.println("\nMake sure you are ending your note with correct end statement");
					appendToNote();
				}

				if (userNoteFile.exists()) {

					FileWriter writeUserNote = new FileWriter(userNoteFile,
							true);

					BufferedWriter bufferedWriter = new BufferedWriter(
							writeUserNote);

					while (userNote.contains("#END")) {
						String[] splitUserNote = userNote.split("#END");
						bufferedWriter.write(splitUserNote[0] + "\n");
						bufferedWriter.flush();

						bufferedWriter.close();
						System.out.println("\nNote taken successfully");
						userSelectionMenu();
					}

				}

			} else if (splitUserInput.length > 2 || splitUserInput.length <= 1
					|| !(userFirstWord.equals("append"))) {
				System.out.println("\nInvalid number of arguments");
				appendToNote();
			}
		} catch (Exception e) {
			System.out.println("\nInvalid number of arguments");
			appendToNote();
		}

	}

	public static void deleteNote() {

		printDeleteNoteHelp();

		Scanner scanner = new Scanner(System.in);

		System.out.print("\n>>>");
		String userInput = scanner.nextLine();

		try {
			String[] splitUserInput = userInput.split(" ", -1);

			String userFirstWord = splitUserInput[0];
			String userLastWord = splitUserInput[1];

			if (userFirstWord.equals("delete") && !(userLastWord.isEmpty())
					&& !(splitUserInput.length > 2)) {

				File userNoteFile = new File(userLastWord + ".ncat");

				if (userNoteFile.exists()) {
					userNoteFile.delete();
					System.out.println("File deleted successfully");
					userSelectionMenu();

				} else {
					System.out
							.println("\nThe note with the name provided does not exist.");
					System.out
							.println("Make sure you are entering the correct note name");
					deleteNote();
				}
			} else if (splitUserInput.length > 2 || splitUserInput.length <= 1
					|| !(userFirstWord.equals("delete"))) {
				System.out.println("\nInvalid number of arguments");
				deleteNote();
			}

		} catch (Exception e) {
			System.out.println("\nInvalid number of arguments");
			deleteNote();
		}

	}

	public static void listAllNotes() throws IOException {
		printListNoteHelp();

		Scanner scanner = new Scanner(System.in);

		System.out.print("\n>>>");
		String userInput = scanner.nextLine();
		String[] splitUserInput = userInput.split(" ", -1);
		if (checkUserInput(splitUserInput)) {
			File directory = new File(".").getCanonicalFile();
			File[] listOfFiles = directory.listFiles();

			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()
						&& listOfFiles[i].getName().endsWith(".ncat")) {
					System.out.println(listOfFiles[i].getName());

				}
			}
			userSelectionMenu();
		}

	}

	public static boolean checkUserInput(String[] splittedUserInput)
			throws IOException {

		try {
			if (splittedUserInput.length > 2) {
				System.out.println("Invalid number of arguments");
				userSelectionMenu();
			}
			String userFirstWord = splittedUserInput[0];
			String userLastWord = splittedUserInput[1];

			if (userFirstWord.equals("list") && userLastWord.equals("all")
					&& !(userLastWord.isEmpty())) {
				return true;
			} else if (userFirstWord.equals("list")
					&& !(userLastWord.isEmpty())) {

				listSpecificNote(userLastWord);
			}
		} catch (Exception e) {
			System.out.println("Invalid number of arguments");
			userSelectionMenu();
		}

		return false;
	}

	public static void listSpecificNote(String userLastWord) throws IOException {

		File directory = new File(".").getCanonicalFile();
		File[] listOfFiles = directory.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			if (listOfFiles[i].isFile()
					&& listOfFiles[i].getName().equals(userLastWord + ".ncat")
					&& listOfFiles[i].getName().endsWith(".ncat")) {

				System.out.println(listOfFiles[i].getName());
				userSelectionMenu();

			}

		}

		System.out.println("Note with the name you entered does not exist");
		userSelectionMenu();

	}

	public static void renameNote() {

		printRenameNoteHelp();

		Scanner scanner = new Scanner(System.in);

		System.out.print("\n>>>");
		String userInput = scanner.nextLine();

		try {
			String[] splitUserInput = userInput.split(" ", -1);

			String userFirstWord = splitUserInput[0];
			String userLastWord = splitUserInput[1];

			if (userFirstWord.equals("rename") && !(userLastWord.isEmpty())
					&& !(splitUserInput.length > 2)) {

				File file = new File(userLastWord + ".ncat");

				if (file.exists()) {

					Scanner scan = new Scanner(System.in);
					System.out.print("\n>>> Enter new name for the note: ");
					String newNoteName = scan.nextLine();

					if (containsIllegal(newNoteName)) {
						System.out.println("\nThe name you entered contains "
								+ "illegal characters.");
						renameNote();
					}

					File newNameFile = new File(newNoteName + ".ncat");

					if (newNameFile.exists()) {
						System.out
								.println("\nThe file with the name already exists.");
						renameNote();
					} else {
						file.renameTo(newNameFile);
						System.out.println("\nNote file renamed successfully.");
						userSelectionMenu();
					}

				} else {
					System.out.println("\nThe note with the "
							+ "name you entered does not exist");
					renameNote();
				}

			} else if (splitUserInput.length > 2 || splitUserInput.length <= 1
					|| !(userFirstWord.equals("rename"))) {
				System.out.println("\nInvalid number of arguments");
				renameNote();
			}

		} catch (Exception e) {
			System.out.println("\nInvalid number of arguments");
			renameNote();
		}

	}

	private static boolean containsIllegal(String userInput) {
		Pattern pattern = Pattern
				.compile("[.'()=~!&$%~#@*+%{}<>\\[\\]|\"\\_^\\s]");
		Matcher matcher = pattern.matcher(userInput);
		return matcher.find();
	}

	public static void printNewNoteHelp() {
		System.out
				.println("\nYou can type new and then the name of your note to ");
		System.out
				.println("create a new note and #END to mark the end of your note");
		System.out
				.println("\nnew <note>   Create new note.End your note with \"#End\" ");
	}

	public static void printShowNoteHelp() {
		System.out
				.println("\nYou can type show and then the name of your note to ");
		System.out.println("see your written note.");

		System.out.println("\nshow <note>  Display existing note");
	}

	public static void printAppendNoteHelp() {
		System.out
				.println("\nYou can type append and then the name of your note to ");
		System.out
				.println("append to existing note and #END to mark the end of your note");
		System.out
				.println("\nappend <note>   Append note.End your note with \"#End\" ");
	}

	public static void printDeleteNoteHelp() {
		System.out
				.println("\nYou can type delete and then the name of your note to ");
		System.out.println("delete your written note.");
		System.out.println("\ndelete <note>  Delete existing note");
	}

	public static void printListNoteHelp() {
		System.out
				.println("\nYou can type list and then the name of your note to ");
		System.out
				.println("list your written note or list all to print all existing notes.");
		System.out.println("\nList <note>  List existing note");
	}

	public static void printRenameNoteHelp() {
		System.out
				.println("\nYou can type rename and then the name of your note to ");
		System.out.println("rename your written note.");
		System.out.println("\nRename <note>  Rename existing note");
	}
}