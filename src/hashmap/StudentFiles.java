package hashmap;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * @author Mbinintsoa "Ram" Ramarolahy
 *
 * <p><b>Project Name: </b>CSC372-PORTFOLIO PROJECT
 *
 * <p><b>Project Purpose: </b> Implement Hashmap Sort in a Java program
 *
 * <p><b>Algorithm Used: </b> Use a hashmap to store student name and student grade
 *
 * <p><b>Program Inputs: </b> The user is prompted to input a names and grades for each student
 *
 * <p><b>Program Outputs: </b> The program prints out the student list 
 *
 * <p><b>Program Limitations: </b> modifying the student name can be achieved by removing the existing entry and adding a new one
 *
 * <p><b>Program Errors: </b> N/A
 *
 * <br> ==================
 */
public class StudentFiles {
	public static final Scanner scan = new Scanner(System.in);
	static Map<String, Double> studentList = new HashMap<>();
	static boolean correctInput = false;
	static boolean endProgram = false;
	static boolean correctEntry = false;
	static int selectedOption = 0;
	static int entryNum;
	static int i;
	static int j;

	/**
	 * @author mbinintsoaramarolahy
	 * 
	 *
	 */
	static void printStudentList() {
		System.out.println("");
		System.out.println("Printing ...");
		System.out.printf("%-16s%5s%-16s\n", "Student", "     ", "Course Grade");   //Prints table header
		System.out.println("----------------     ----------------");
		Map<String, Double> sortedList = new TreeMap<String, Double>(studentList);	//Stores studentList in a TreeMap in order to be sorted
		Set<Entry<String, Double>> set = sortedList.entrySet();
		Iterator<Entry<String, Double>> iterator = set.iterator();
		// Using the iterator to print through the TreeMap entries
		// Inform the user if the list is Empty
		if (studentList.isEmpty()) {
			System.out.println("No Entry ...");
		} else {
			while (iterator.hasNext()) {
				@SuppressWarnings("rawtypes")
				Map.Entry mentry = (Map.Entry) iterator.next();
				System.out.printf("%-16s%5s%-16s\n", mentry.getKey(), "  :  ", mentry.getValue());
			}
		}
		System.out.println("----------------     ----------------\n");
	}

	static void addStudents() { 
		boolean noDuplicate = false;
		// Interface to ask the user the number of students they would like to enter
		System.out.println("How many students would you like to add?");
		do {
			try {
				System.out.print("--> ");
				entryNum = scan.nextInt();
				scan.nextLine();

				correctInput = true;
			} catch (InputMismatchException e) {
				System.out.println("Invalid input.");
				scan.nextLine();
				correctInput = false;
			}
		} while (!correctInput);

		for (i = 0; i < entryNum; i++) { // Iterates through entering each student

			Student newStudent = new Student(); // Create new class to store new data
			Grade newGrade = new Grade();

			if (i >= 1) { // Changes the prompt message if user wants to add
							// more than 1 car
				System.out.println("Please enter the next student.");
			}

			do {
				newStudent.setName();
				// I am changing the names to uppercase since this would be the easiest way to make sure the 
				// the user does not enter name variances such as jim or Jim
				String firstName = newStudent.getName().toUpperCase();
				newGrade.setCourseGrade();
				Double courseGrade = newGrade.getCourseGrade();
				
				// Interface to check to if the new name added already exists
				// Warn the user if it does.
				if (studentList.containsKey(firstName)) {
					System.out.println("The list already contains this entry.");
					System.out.println("Please make sure to make the name unique. eg Add Middle Initial");
					System.out.println("");
					noDuplicate = false;
				} else if (firstName.equals("")) {   // Stops the user from entering an empty name
					System.out.println("Invalid Name");
					noDuplicate = false;
				} else {
					studentList.put(firstName, courseGrade);   // Adds the new student to the list
					noDuplicate = true;
				}
			} while (!noDuplicate);
		}

		System.out.println("End of entry ...");  // Lets the user know the end of entry
	}

	static void removeStudent() {
		boolean cancelRemove = false;
		int confirmRemove;

		if (studentList.isEmpty()) {   // Lets the user know if the list is currently empty
			System.out.println("The list is currently empty.");
			System.out.println("Please add students first.\n");
		} else {

			do {
				System.out.println("Which student would you like to remove?");
				System.out.print("--> ");
				String deleteStudent = StudentFiles.scan.nextLine().toUpperCase();

				if (studentList.containsKey(deleteStudent)) {
					// For UX purpose, let's warn the user before they make an irreversible action.
					System.out.println("Please confirm Remove Student: [1]: REMOVE   [2]: CANCEL");
					do {
						try {
							System.out.print("--> ");
							confirmRemove = scan.nextInt();
							scan.nextLine();
							switch (confirmRemove) {
							case 1:
								correctInput = true;
								break;
							case 2:
								cancelRemove = true;
								correctInput = true;
								break;
							default:
								System.out.println("Unavailable Option");
								correctInput = false;
								break;
							}

						} catch (InputMismatchException e) {
							System.out.println("Invalid input.");
							scan.nextLine();
							correctInput = false;
						}
					} while (!correctInput);

					if (!cancelRemove) {   // If action is confirmed
						studentList.remove(deleteStudent);
						// Lets the user know that the entry has be removed
						System.out.println(deleteStudent + " has been removed from the list.");
						correctEntry = true;
					} else {   // If the user cancels the action
						correctEntry = true;
						System.out.println("Remove Student has been cancelled.");
					}

				} else {
					System.out.println(deleteStudent + " is not on the list.");
				}
			} while (!correctEntry);
		}
	}

	static void modifyGrade() {
		boolean rightInput = false;
		String modifyStudent = "";
		double modifyGrade = 0;

		if (studentList.isEmpty()) {   // Lets the user know if the list is currently empty
			System.out.println("The list is currently empty.");
			System.out.println("Please add students first.\n");
		} else {

			do {
				System.out.println("Which Student would would like to modify?");
				System.out.print("--> ");
				// To facilitate comparison and for UX purposes, I am changing the input to all uppercase .
				modifyStudent = scan.nextLine().toUpperCase();

				if (studentList.containsKey(modifyStudent)) {
					System.out.println("Please enter the new Grade:");
					do {
						try {
							System.out.print("--> ");
							modifyGrade = StudentFiles.scan.nextDouble();
							StudentFiles.scan.nextLine();
							rightInput = true;
						} catch (InputMismatchException e) {
							System.out.println("Invalid input.");
							StudentFiles.scan.nextLine();
						}

					} while (!rightInput);
					// Once the user entered the correct key, the list will override the existing key/value pair
					// with the new value entered by the user.
					// A similar algorithm can be written for a program that requires the user to be able to 
					// change the name of the student, although this action can be achieved by removing the entry first
					// then adding the new entry.
					studentList.put(modifyStudent, modifyGrade);
					System.out.println("The file has been updated.");
					printStudentList();

				} else {   // Warns the user if they entered a key that is not on the list.
					System.out.println("This student is not currently on the list.");
				}
			} while (!rightInput);
		}
	}

	public static void main(String[] args) {

		do {
			System.out.println("Please select an option below:");
			System.out.println(
					"[1]: Add Students   [2]: Remove Student   [3]: Modify Grade   [4]: Print Student List   [5]: Exit");
			do {
				try {
					System.out.print("--> ");
					selectedOption = scan.nextInt();
					scan.nextLine();

					correctInput = true;
				} catch (InputMismatchException e) {
					System.out.println("Invalid input.");
					scan.nextLine();
					correctInput = false;
				}
			} while (!correctInput);

			switch (selectedOption) {
			case 1: // Add Student
				addStudents();
				break;
			case 2: // Remove Student
				removeStudent();
				break;
			case 3: // Modify Grade
				modifyGrade();
				break;
			case 4: // Print Student List
				printStudentList();
				break;
			case 5: // Exit program
				System.out.println("Thank you, Good Bye!");
				endProgram = true;

				break;
			default:
				System.out.println("Unavailable Option");
				break;
			}
		} while (!endProgram);

	}

}
