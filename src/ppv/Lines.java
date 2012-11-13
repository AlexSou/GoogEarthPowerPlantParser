/*
 * Power Plants Viewer
 * Alejandro Soulier, Imperial College. as1007@imperial.ac.uk
 * Version: 1.0  	June 2011
 */

/*
 *  Lines.java
 *  Class that outputs the lines data to a KML file.
 */

package ppv;

public class Lines {

	//Write line data to file.
	static void createLine(String from, String to, String exists, String capacityString) {
		int from1, to1, width;
		float capacity;

		from1 = Integer.parseInt(from.trim());
		to1 = Integer.parseInt(to.trim());
		capacity = Float.parseFloat(capacityString.trim());
		if ( !Headers.nodeCoords.get(from1).equals("-1,-1") && !Headers.nodeCoords.get(to1).equals("-1,-1") ) {
			Headers.output.print("\n");
			Headers.output.print("<Placemark>\n");	
			Headers.output.print("<name>" + from+" "+to + "</name>\n");
			Headers.output.print("<visibility>1</visibility>");
			Headers.output.print("<description>Line</description>");
			Headers.output.print("<Style>\n");
			Headers.output.print("<LineStyle>\n");
			if (exists.equals("true")){
				Headers.output.print("<color>ff042edc</color>\n");
			}
			else {
				Headers.output.print("<color>ff9a9a9a</color>\n");
			}
			Headers.output.print("<width>");
			if (capacity < 1000){
				width = 2;
			}
			else if (capacity >= 1000 && capacity< 2500) {
				width = 3;
			}
			else if (capacity >= 2500 && capacity < 4000) {
				width = 4;
			}
			else if (capacity >= 4000 && capacity < 5500) {
				width = 5;
			}
			else if (capacity >= 5500 && capacity < 7500) {
				width = 6;
			}
			else if (capacity >= 7500 && capacity < 10000) {
				width = 7;
			}
			else if (capacity >= 10000 && capacity < 20000) {
				width = 8;
			}
			else if (capacity >= 20000 && capacity < 100000) {
				width = 10;
			}
			else if (capacity >= 100000 && capacity < 1000000) {
				width = 13;
			}
			else if (capacity >= 1000000) {
				width = 15;
			}
			else {
				width = 4;
			}
			Headers.output.print(width);
			Headers.output.print("</width>\n");
			Headers.output.print("</LineStyle>\n");
			Headers.output.print("</Style>\n");
			Headers.output.print("<LineString>");
			Headers.output.print("<tessellate>1</tessellate>");
			Headers.output.print("<altitudeMode>clampedToGround</altitudeMode>");
			Headers.output.print("<coordinates>");
			Headers.output.print(Headers.nodeCoords.get(from1) + ",0\n");
			Headers.output.print(Headers.nodeCoords.get(to1) + ",0\n");
			Headers.output.print("</coordinates> \n  </LineString> \n </Placemark> \n");
		}
	}
}
