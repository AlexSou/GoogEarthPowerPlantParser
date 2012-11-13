/*
 * Power Plants Viewer
 * Alejandro Soulier, Imperial College. as1007@imperial.ac.uk
 * Version: 1.0  	June 2011
 */

/*
 *  ModLine.java
 *  Class that modifies the lines source file.
 */

package ppv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class ModLine {

	static final String curDir = System.getProperty("user.dir");
	
	//Modify line in source file.
	static void modLine(String node1, String node2, String action, String capacity) throws IOException{
		BufferedReader input;
		String[] nodeInput;
		PrintWriter output;
		String line = "";
		String lines = curDir + "/data/lines.csv";
		String linesOLD = curDir + "/data/linesOLD.csv";

		if (action.equals("del")) { //Delete line.
			File linesFile = new File(lines);
			File linesOLDFile = new File(linesOLD);
			linesFile.renameTo(linesOLDFile);
			linesFile.createNewFile();
			input = new BufferedReader (new FileReader (linesOLD));
			output = new PrintWriter (new FileWriter (lines));
			while (true){
				if (! input.ready()) break;
				else{
					line = input.readLine();
					nodeInput = line.split(",");
					if (!(nodeInput[0].equals(node1) && nodeInput[1].equals(node2))) {
						output.println(line);
					}
				}     
			}
			input.close();
			output.close();
			linesOLDFile.delete();
		}
		else { //create line
			FileWriter linesWriter = new FileWriter(lines, true);
			output = new PrintWriter (linesWriter);
			output.println(node1 + "," + node2 + ",false," + capacity + ",0");
			output.close();
		}
	}
}
