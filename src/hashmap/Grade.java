package hashmap;

import java.util.InputMismatchException;

public class Grade {
	private double courseGrade = 0;

	/**
	 * @return the courseGrade
	 */
	public double getCourseGrade() {
		return courseGrade;
	}

	
	public void setCourseGrade() {
		double newGrade = 0;
		boolean rightInput = false;		
		
		do {
			try {
				System.out.println("Enter the Course Grade: ");
				System.out.print("--> ");
				newGrade = StudentFiles.scan.nextDouble();
				StudentFiles.scan.nextLine();
				this.courseGrade = newGrade;
				rightInput = true;
			} catch (InputMismatchException e){
				System.out.println ("Invalid input.");
				StudentFiles.scan.nextLine();
			}
			
		} while (!rightInput);
		
	}

}
