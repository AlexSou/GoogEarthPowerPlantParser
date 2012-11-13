/*
 * Power Plants Viewer
 * Alejandro Soulier, Imperial College. as1007@imperial.ac.uk
 * Version: 1.0  	June 2011
 */

/*
 *  LinesWindow.java
 *  Class that loads the line modification window.
 */

package ppv;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class LinesWindow extends JFrame {

	private static final long serialVersionUID = 413334521389240738L;
	private JPanel contentPane;
	private JTextField txtNode1;
	private JTextField txtNode2;
	private JTextField txtCapacity;
	static LinesWindow frame;

	//Start running the window.
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new LinesWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	//Window GUI data.
	public LinesWindow() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 329, 222);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblModLine = new JLabel("Modify Line\n");
		lblModLine.setBounds(115, 21, 131, 22);
		lblModLine.setFont(new Font("Lucida Grande", Font.BOLD, 18));
		contentPane.add(lblModLine);

		txtNode1 = new JTextField();
		txtNode1.setBounds(40, 82, 70, 28);
		contentPane.add(txtNode1);
		txtNode1.setColumns(10);

		JLabel lblNode1 = new JLabel("From Node");
		lblNode1.setBounds(40, 68, 80, 16);
		contentPane.add(lblNode1);

		txtNode2 = new JTextField();
		txtNode2.setColumns(10);
		txtNode2.setBounds(132, 82, 70, 28);
		contentPane.add(txtNode2);

		JLabel lblNode2 = new JLabel("To Node");
		lblNode2.setBounds(138, 68, 61, 16);
		contentPane.add(lblNode2);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispose();

			}
		});
		btnCancel.setBounds(19, 133, 101, 29);
		contentPane.add(btnCancel);

		JButton btnCreateLine = new JButton("Create");
		btnCreateLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtNode1.getText().equals("") || txtNode2.getText().equals("") ){
						JOptionPane.showMessageDialog(null, "Please select correct nodes.", "Node Selection Error", JOptionPane.ERROR_MESSAGE);
					}
					else {
						ModLine.modLine(txtNode1.getText(), txtNode2.getText(), "create", txtCapacity.getText());
					}
					frame.dispose();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
		btnCreateLine.setBounds(211, 133, 101, 29);
		contentPane.add(btnCreateLine);

		JButton btnDelLine = new JButton("Delete");
		btnDelLine.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					if (txtNode1.getText().equals("") || txtNode2.getText().equals("") ){
						JOptionPane.showMessageDialog(null, "Please select correct nodes.", "Node Selection Error", JOptionPane.ERROR_MESSAGE);
					}
					else{
						ModLine.modLine(txtNode1.getText(), txtNode2.getText(), "del", "");
					}
					frame.dispose();
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Please select correct nodes.", "Node Selection Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		btnDelLine.setBounds(115, 133, 101, 29);
		contentPane.add(btnDelLine);

		txtCapacity = new JTextField();
		txtCapacity.setColumns(10);
		txtCapacity.setBounds(221, 82, 70, 28);
		contentPane.add(txtCapacity);

		JLabel lblCapacity = new JLabel("Capacity");
		lblCapacity.setBounds(227, 68, 61, 16);
		contentPane.add(lblCapacity);
	}

}

