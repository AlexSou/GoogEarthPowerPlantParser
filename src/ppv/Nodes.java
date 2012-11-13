/*
 * Power Plants Viewer
 * Alejandro Soulier, Imperial College. as1007@imperial.ac.uk
 * Version: 1.0  	June 2011
 */

/*
 *  Nodes.java
 *  Class that writes node data to a KML file.
 */

package ppv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Nodes {
	
	static final String curDir = System.getProperty("user.dir");
	
	//Output initial set of nodes.
	static void initializeNodes() throws IOException {
		String[] nodeInput;
		String line = "";
		BufferedReader inputIni = new BufferedReader (new FileReader (curDir + "/data/generators_nonrenewable.csv"));
		
		line = inputIni.readLine ();
		while (true){
			if (! inputIni.ready()) break;
			else{
				line = inputIni.readLine ();
				nodeInput = line.split(",");
				createNode(nodeInput[1], nodeInput[5], nodeInput[0], nodeInput[2], nodeInput[3], nodeInput[4]); 
			}     
		}
		BufferedReader inputIni2 = new BufferedReader (new FileReader (curDir + "/data/generators_renewable.csv"));
		line = inputIni2.readLine ();
		while (true){
			if (! inputIni2.ready()) break;
			else{
				line = inputIni2.readLine ();
				nodeInput = line.split(",");
				createNode(nodeInput[1], nodeInput[4], nodeInput[0], nodeInput[2], nodeInput[3], "NA");
			}
		}

	}
	
	//Write a specific node to a file.
	static void createNode(String location, String type, String generatorName, String capacity, String existent, String shortLead) throws IOException {
		String lat, lon, nodeId, style; 
		String[] nodeContents;
		
		nodeContents = Headers.nodeData.get(location);
		nodeId = nodeContents[0];
		lat = nodeContents[1];
		lon = nodeContents[2];
		Headers.noNodes = Headers.noNodes + 1;
		style = Style.createStyle(type, capacity, existent, nodeId);
		Headers.output.print("\n");
		Headers.output.print("<Placemark>\n");
		Headers.output.print("<description> Node:\n" + nodeId + "\n Generator: "  + generatorName + "\n Short Lead: " + shortLead + "</description>\n");
		Headers.output.print(style);
		Headers.output.print("<Point>\n");
		Headers.output.print("<coordinates>" + lon + "," + lat + ", 0</coordinates>\n");
		Headers.output.print("</Point>\n");
		Headers.output.print("</Placemark>\n");
		Headers.output.print("\n");
	}
}
