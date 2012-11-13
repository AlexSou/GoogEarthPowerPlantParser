/*
 * Power Plants Viewer
 * Alejandro Soulier, Imperial College. as1007@imperial.ac.uk
 * Version: 1.0  	June 2011
 */

/*
 *  ModNode.java
 *  Class that modifies a list of nodes at the source files.
 */

package ppv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class ModNode {

	static final String curDir = System.getProperty("user.dir");
	
	//Process a list of nodes to modify.
	static void modNodes(ArrayList<String> modNodes) throws IOException {
		String currNode, lon, lat;
		String [] coordsTxt, lonTxt;
		BufferedReader input;
		String kmlFileStr = curDir + "/data/Placemark.kml";
		String line = "";
		File kmlFile = new File(kmlFileStr);
		int done = 0;
		if (kmlFile.exists()) {
			while (!modNodes.isEmpty() ) {
				done = 0;
				currNode = modNodes.remove(0);
				input = new BufferedReader (new FileReader (kmlFileStr));
				while (done == 0) {
					if (!input.ready()) {
						modNode(currNode, "del", "", "");
						done = 1;
					} 
					else {
						line = input.readLine ();
						if (line.equals(currNode)) {
							while (!line.contains ("<coordinates>")){
								line = input.readLine();
							}
							coordsTxt = line.split(",");
							lonTxt = coordsTxt[0].split(">");
							lon = lonTxt[1].trim();
							lat = coordsTxt[1].trim();
							modNode(currNode, "mod", lat, lon);
							done = 1;
						}		
					}
				}
				input.close();
			}
		}
	}

	//Modify a specific node.
	private static void modNode(String node, String action, String lat, String lon) throws IOException {
		BufferedReader input;
		String[] nodeInput;
		PrintWriter output;
		String locations = curDir + "/data/locations.csv";
		String locOLD = curDir + "/data/locOLD.csv";
		String line = "";
		String modStr = "";
		
		File locationsFile = new File(locations);
		File locOLDFile = new File(locOLD);
		locationsFile.renameTo(locOLDFile);
		locationsFile.createNewFile();
		input = new BufferedReader (new FileReader (locOLD));
		output = new PrintWriter (new FileWriter (locations));
		while (true){
			if (! input.ready()) break;
			else{
				line = input.readLine();
				nodeInput = line.split(",");
				if (nodeInput[0].equals(node)) {
					if (action.equals("mod")) {
						modStr = nodeInput[0] + "," + nodeInput[1] + "," + nodeInput[2] + "," + lat + "," + lon;
						output.println(modStr);					
					}
					else {
						modStr = nodeInput[0] + "," + nodeInput[1] + "," + nodeInput[2] + "," + "-1" + "," + "-1";
						output.println(modStr);		
					}
				}
				else {
					output.println(line);
				}
			}     
		}
		input.close();
		output.close();
		locOLDFile.delete();
	}
}
