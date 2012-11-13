/*
 * Power Plants Viewer
 * Alejandro Soulier, Imperial College. as1007@imperial.ac.uk
 * Version: 1.0  	June 2011
 */

/*
 *  Style.java
 *  Class that decides the style in a KML file for a specific node.
 */

package ppv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class Style {
	
	static final String curDir = System.getProperty("user.dir");
	
	//Returns string that defines the KML style for a node.
	static String createStyle(String type, String capacity, String existent, String nodeId) throws IOException {
		int  timeFound;
		double demand, capacityDouble, capacityCode;
		int [] hour;
		String typeCode, exCode, nodeFile, iconCode;
		String demandCode = null;
		String style = null;
		String line = "";
		String[] nodeInput = null;

		hour = getHour();
		nodeFile = curDir + "/data/" + nodeId + ".csv";
		BufferedReader inputHour = new BufferedReader (new FileReader (nodeFile));
		timeFound = 0;
		line = inputHour.readLine ();
		while (timeFound == 0) {
			line = inputHour.readLine ();
			nodeInput = line.split(",");
			if ( hour[0] == Integer.parseInt(nodeInput[0]) && hour[1] == Integer.parseInt(nodeInput[1]) && hour[2] == Integer.parseInt(nodeInput[2])) {
				timeFound = 1;
				break;
			}
		}
		demand = Double.parseDouble(nodeInput[3]);
		if (demand >= 14 && demand < 26) {demandCode = "2";}
		if (demand >= 26 && demand <= 45) {demandCode = "3";}
		if (demand > 45) {demandCode = "4";}
		else {demandCode = "1";}	
		capacityDouble = Double.parseDouble(capacity);	
		capacityCode = capacityDouble / 250;
		if (capacityCode > 2) {capacityCode = 2;}
		else if (capacityCode < 0.6) {capacityCode = 0.6;}
		if (type.equals("GAS")) {typeCode = "G";}
		else if  (type.equals("BIO")) {typeCode = "B";}
		else if  (type.equals("COAL")) {typeCode = "C";}
		else if  (type.equals("true")) {typeCode = "W";}
		else if  (type.equals("false")) {typeCode = "S";}
		else if  (type.equals("OIL")) {typeCode = "O";}
		else {typeCode = "N";}
		if (existent.equals("true") ) {
			exCode = "e";
		}
		else {
			exCode = "n";
		}
		iconCode = typeCode + exCode + demandCode;
		style = "<Style>\n";
		style = style  + "<IconStyle>\n";
		style = style  + "<scale>" + capacityCode + "</scale>";
		style = style  + "<Icon>\n";
		style = style  + "<href>icons/" + iconCode +".png</href>\n";
		style = style  + "</Icon>\n";
		style = style  + "</IconStyle>\n";
		style = style  + "</Style>\n";
		return style;
	}
	
	//Return array with the demand time.
	static int[] getHour(){
		int[] time = new int[3];
		time[0] = StartWindow.month;
		time[1] = StartWindow.day;
		time[2] = StartWindow.hour;
		return time;
	}
}
