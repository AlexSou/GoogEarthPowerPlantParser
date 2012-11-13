/*
 * Power Plants Viewer
 * Alejandro Soulier, Imperial College. as1007@imperial.ac.uk
 * Version: 1.0  	June 2011
 */

/*
 *  Headers.java
 *  Class that writes the KML headers to a file and runs the file.
 */

package ppv;

import java.io.*;
import java.util.*;

public class Headers {
	
	static int noNodes;
	static PrintWriter output;
	static ArrayList<String> nodeCoords;
	static HashMap<String, String[]> nodeData; 
	static final String curDir = System.getProperty("user.dir");

	//Start writing KML data to file.
	static void startWriting() throws IOException {
		String[] nodeInput;
		BufferedReader input = new BufferedReader (new FileReader (curDir + "/data/locations.csv"));
		String line = "";
		String kmlFileStr = curDir + "/data/Placemark.kml";

		noNodes = 0;
		nodeCoords= new ArrayList<String>();
		nodeData = new HashMap<String, String[]>();
		line = input.readLine ();
		File kmlFile = new File(kmlFileStr);
		if (kmlFile.exists()) {
			kmlFile.delete();
		}
		output = new PrintWriter (new FileWriter (kmlFileStr));
		output.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
		output.print("<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n");
		output.print("<Folder>\n");
		output.print("<name>Power Plants</name>\n");
		output.print("<open>1</open>\n");
		output.print("<Style id=\"PowerPlant\">\n");
		output.print("<IconStyle>\n");
		output.print("<Icon>\n");
		output.print("<href>icons/P.png</href>\n");
		output.print("</Icon>\n");
		output.print("</IconStyle>\n");
		output.print("</Style>\n");
		while (true){
			if (! input.ready()) break;
			else{
				line = input.readLine ();
				nodeInput = line.split(",");
				nodeCoords.add(nodeInput[4] + "," + nodeInput[3]);
				if(!nodeInput[2].equals("IMPORT")){
					String[] nodeContents = {nodeInput[0], nodeInput[3], nodeInput[4]};
					nodeData.put(nodeInput[1], nodeContents);
				}
			}
		}
		Nodes.initializeNodes();
		System.out.println("Nodes " + noNodes);
		BufferedReader input1 = new BufferedReader (new FileReader (curDir + "/data/lines.csv"));
		line = input1.readLine ();
		while (true){
			if (! input1.ready()) break;
			else{
				line = input1.readLine ();
				nodeInput = line.split(",");
				Lines.createLine(nodeInput[0], nodeInput[1], nodeInput[2], nodeInput[3]);
			}
		}
		output.print("</Folder>\n");
		output.print("</kml>\n");
		output.close();
		System.out.println("Done.\n");
		startKML();
	}

	//Run KML map.
	public static void startKML() {
		try {		 
			File kmlFile = new File(curDir + "/data/Placemark.kml");
			if (kmlFile.exists()) {
				Runtime.getRuntime().exec( "killall googleearth-bin" );
				Runtime.getRuntime().exec( String.format("gnome-open %s", kmlFile)  );
				
				
			} 
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}
}
