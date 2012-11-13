/*
 * Power Plants Viewer
 * Alejandro Soulier, Imperial College. as1007@imperial.ac.uk
 * Version: 1.0  	June 2011
 */

/*
 *  StartWindow.java
 *  Class that loads the initial GUI window.
 */

package ppv;

import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class StartWindow {

	static JComboBox comboMonth, comboDay, comboHour;
	static int month, day, hour;
	static ArrayList<String> modNodes;
	private JFrame frame;
	private JTextField txtModNode;
	static final String curDir = System.getProperty("user.dir");

	//Run initial GUI window.
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartWindow window = new StartWindow();
					modNodes= new ArrayList<String>();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public StartWindow() {
		initialize();
	}

	//Load window GUI data.
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(180, 700, 990, 130);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblDemandDate = new JLabel("Power Demand Date");
		lblDemandDate.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblDemandDate.setBounds(20, 6, 262, 27);
		frame.getContentPane().add(lblDemandDate);

		comboMonth = new JComboBox();
		comboMonth.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				monthChange();
			}
		});
		comboMonth.setModel(new DefaultComboBoxModel(new String[] {"Month", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
		comboMonth.setBounds(20, 45, 122, 27);
		frame.getContentPane().add(comboMonth);

		comboDay = new JComboBox();
		comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day"}));
		comboDay.setBounds(142, 45, 122, 27);
		frame.getContentPane().add(comboDay);

		comboHour = new JComboBox();
		comboHour.setModel(new DefaultComboBoxModel(new String[] {"Hour", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"}));
		comboHour.setBounds(264, 45, 122, 27);
		frame.getContentPane().add(comboHour);

		JButton btnLoadMap = new JButton("Load Map");
		btnLoadMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				loadMap();
			}
		});
		btnLoadMap.setBounds(398, 45, 117, 29);
		frame.getContentPane().add(btnLoadMap);

		JButton btnUpdMap = new JButton("Update Data");
		btnUpdMap.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					ModNode.modNodes(modNodes);
				} catch (IOException e) {
					e.printStackTrace();
				}
				modNodes.clear();
				loadMap();
			}
		});
		btnUpdMap.setBounds(820, 45, 134, 29);
		frame.getContentPane().add(btnUpdMap);

		JLabel lblModifyNode = new JLabel("Modify Node/Line\n");
		lblModifyNode.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		lblModifyNode.setBounds(554, 6, 231, 27);
		frame.getContentPane().add(lblModifyNode);

		txtModNode = new JTextField();
		txtModNode.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent arg0) {
				if (txtModNode.getText().equals("Node Number")) {
					txtModNode.setText("");
				}
			}
		});
		txtModNode.setText("Node Number");
		txtModNode.setBounds(554, 44, 134, 28);
		frame.getContentPane().add(txtModNode);
		txtModNode.setColumns(10);

		JButton btnModify = new JButton("Modify Node");
		btnModify.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				modNodes.add(txtModNode.getText());
			}
		});
		btnModify.setBounds(701, 45, 124, 29);
		frame.getContentPane().add(btnModify);

		JButton btnModLine = new JButton("Modify Line");
		btnModLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LinesWindow.main();
			}
		});
		btnModLine.setBounds(701, 73, 122, 29);
		frame.getContentPane().add(btnModLine);
		
		JButton btnRunAlgorithm = new JButton("Run Algorithm");
		btnRunAlgorithm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) { //Run investment algorithm
				try {		 
					File algoProg = new File(curDir + "/data/InvAlgo");
					if (algoProg.exists()) {
						if (Desktop.isDesktopSupported()) {
							Desktop.getDesktop().open(algoProg);
						} 
					} 
					else{
						JOptionPane.showMessageDialog(null, "Investment Algorithm program not present.", "Algorithm program not found", JOptionPane.ERROR_MESSAGE); 
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
				
			}
		});
		btnRunAlgorithm.setBounds(820, 73, 134, 29);
		frame.getContentPane().add(btnRunAlgorithm);

	}

	//Change the GUI day combo box depending on the selected month.
	static void monthChange() {
		int month = comboMonth.getSelectedIndex();
		switch (month) {
		case 1: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})); break;
		case 2: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28"})); break;
		case 3: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})); break;
		case 4: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"})); break;
		case 5: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})); break;
		case 6: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"})); break;
		case 7: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})); break;
		case 8: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})); break;
		case 9: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"})); break;
		case 10: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})); break;
		case 11: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30"})); break;
		case 12: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"})); break;
		default: comboDay.setModel(new DefaultComboBoxModel(new String[] {"Day"}));
		}
	}

	//Load graphic map.
	static void loadMap() {
		if (comboMonth.getSelectedIndex() >= 1 && comboMonth.getSelectedIndex() <= 12 && comboDay.getSelectedIndex() >= 1 && comboDay.getSelectedIndex() <= 31 && comboHour.getSelectedIndex() >= 1 && comboHour.getSelectedIndex() <= 24 ) {
			month = comboMonth.getSelectedIndex() - 1;
			day = comboDay.getSelectedIndex() - 1;
			hour = comboHour.getSelectedIndex() - 1;
			try {
				Headers.startWriting();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "Please select a correct date for power demand.", "Date Selection Error", JOptionPane.ERROR_MESSAGE); 
		}
	}
}

