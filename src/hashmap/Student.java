/**
 * 
 */
package hashmap;

/**
 * @author mbinintsoaramarolahy
 *
 */
public class Student {
	private String studentName = "";
	
	public void setName(){
		String newName = "";
		
		System.out.println("Enter student First Name: ");
		System.out.print("--> ");
		newName = StudentFiles.scan.nextLine();
		studentName = newName;
	}
	
	public String getName(){
		return studentName;
	}
	
	public Student(){
		studentName = "";
	}

}
