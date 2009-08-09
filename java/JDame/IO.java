/*
 * (c) by 2BI
 * HTL Wien 3 Rennweg
 * Gerald Schreiber
 * gerald.schreiber@gmx.at
 */

import java.io.*;

public class IO {
	/*
	 * Filestruktur: Typ Farbe X Y
	 */
	public Figur[][] Input(String dateiname) {
		Figur[][] in = new Figur[8][8];
		String s;
		String[] split;
		int i = 0;
		int j = 0;
		try {
			BufferedReader read = new BufferedReader(new FileReader(dateiname));
			while ((s = read.readLine()) != null) {
				split = s.split(" ");
				if (Integer.parseInt(split[0]) == 0) {
					in[i][j] = new Figur(Byte.parseByte(split[1]), Byte.parseByte(split[2]), Byte.parseByte(split[3]));
				} else if (Integer.parseInt(split[0]) == 1) {
					in[i][j] = new Dame(Byte.parseByte(split[1]), Byte.parseByte(split[2]), Byte.parseByte(split[3]));
				}
				j++;
				if (j == 8) {
					i++;
					j = 0;
				}
			}
			read.close();
		} catch (IOException e) {

		}
		return in;
	}

	public void Output(Figur[][] out, String dateiname) {
		try {
			PrintWriter print = new PrintWriter(new FileWriter(dateiname));
			for (int i = 0; i < out.length; i++) {
				for (int j = 0; j < out[i].length; j++) {
					print.println(out[i][j].getType() + "" + out[i][j].farbe
							+ " " + out[i][j].x + " " + out[i][j].y);
				}
			}
			print.close();
		} catch (IOException e) {

		}
	}
}
