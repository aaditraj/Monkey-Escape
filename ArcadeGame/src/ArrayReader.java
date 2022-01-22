import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This class reads an unknown number of integers from a given file, 
 * assuming that all numbers are separated by one of the Scanner class' 
 * default token characters.
 * 
 * @author john_shelby
 */
public class ArrayReader {
	
	private String filename;
	
	/**
	 * 
	 * @param filename The path to the file to be read. The file must be a list of numbers separated by one of the Scanner class' default token characters (such as a new line or space).
	 */
	public ArrayReader(String filename) {
		this.filename = filename;
	}
	
	/**
	 * Fills the input array with data from the file.
	 * 
	 * @param fill The array to fill with data from the file. Must have a length equal to or greater than the number of integers in the file.
	 * @return The number of integers that were read from the file (could be less than the length of the input array).
	 */
	public int fillDataArray(Leaderboard fill) {

		FileReader reader = null;
		int i = 0;
    	try {
    			reader = new FileReader(filename);
				Scanner in = new Scanner(reader);
				while (in.hasNext()) {
					String input = in.next();
					Double value = Double.parseDouble(input);
					fill.add(value);
					i++;
				}
    	} catch (IOException ex) {
    		System.out.println("File cannot be read.");
			return 0;
		} catch (NumberFormatException ex) {
			System.out.println("File is in the wrong format.");
			return 0;
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Array passed in was not large enough to hold the data.");
			return 0;
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
	    		System.out.println("File cannot be closed.");
				return 0;
			}
		}
    	
    	return i;
	}
	
	/**
	 * Fills the input array with data from the file.
	 * 
	 * @param fill The array to fill with data from the file. Must have a length equal to or greater than the number of integers in the file.
	 * @return The number of integers that were read from the file (could be less than the length of the input array).
	 */
	public int fillStringArray(Leaderboard fill) {

		FileReader reader = null;
		int i = 0;
    	try {
    			reader = new FileReader(filename);
				Scanner in = new Scanner(reader);
				while (in.hasNext()) {
					String input = in.next();
					String value = input;
					fill.add(value);
					i++;
				}
    	} catch (IOException ex) {
    		System.out.println("File cannot be read.");
			return 0;
		} catch (NumberFormatException ex) {
			System.out.println("File is in the wrong format.");
			return 0;
		} catch (ArrayIndexOutOfBoundsException ex) {
			System.out.println("Array passed in was not large enough to hold the data.");
			return 0;
		} finally {
			try {
				reader.close();
			} catch (IOException ex) {
	    		System.out.println("File cannot be closed.");
				return 0;
			}
		}
    	
    	return i;
	}
	
}
