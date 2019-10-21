package part3;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.*;
import part1.Directory;
import part1.Entry;
import part2.HashDirectory;

/**
 * 
 * This class implements GUI
 * 
 * @author Eigminas Slavinskas b8021850
 *
 */

public class App {

	public static void main(String[] args) throws FileNotFoundException {

		Directory<Entry> dir = new HashDirectory();

		// Reading from file

		FileReader from = new FileReader("C:\\Users\\slavi\\Desktop\\Programming\\inputData.txt"); // <-- file can be changed here
																		
		Scanner sc = new Scanner(from);


		String surname, initials, extension;
		
		try{
			while (sc.hasNext()) {
				
				surname = sc.next();
				initials = sc.next();
				extension = sc.next();
				
				if(surname.matches(".*\\d.*") || initials.length() != 4 || !extension.matches("[0-9]+")){
					
					from.close();
					throw new IllegalArgumentException();
				}
				
				dir.insert(new Entry(surname, initials, extension));
			}
		}
		catch (Exception e){
			System.out.print("Invalid entry found");
			System.exit(1);
		}

		// Creating GUI components

		JFrame frame = new JFrame("Phone directory");
		
		JLabel surnameLabel = new JLabel("Surname");
		JTextField surnameTextField = new JTextField(10);

		JLabel initialsLabel = new JLabel("Initials");
		JTextField initialsTextField = new JTextField(10);

		JLabel extensionLabel = new JLabel("Extension");
		JTextField extensionTextField = new JTextField(10);
		
		JButton insert = new JButton("insert");
		JButton delete = new JButton("delete");
		JButton findExtensionNumber = new JButton("find number");
		JButton changeNumber = new JButton("change number");
		JButton print = new JButton("print");
		
		JTextArea textArea = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(textArea);
		// setting properties to the main frame
		
		frame.setSize(1000, 1000);
		frame.setResizable(false); 
		frame.setLocationRelativeTo(null); // frame will appear in the middle of screen
		frame.setLayout(new FlowLayout());
		
		
		
		
		// adding components to the main frame
		
		// labels and text fields
		frame.add(surnameLabel);
		frame.add(surnameTextField);

		frame.add(initialsLabel);
		frame.add(initialsTextField);

		frame.add(extensionLabel);
		frame.add(extensionTextField);
		
		// buttons
		frame.add(insert);
		frame.add(delete);
		frame.add(findExtensionNumber);
		frame.add(changeNumber);
		frame.add(print);

		// text area
		textArea.setEditable(false);
		textArea.setFont(new Font("Consolas", 15, 15));

		textArea.setText(dir.toString());

		// scroll
		scrollPane.setPreferredSize(new Dimension(400, 800));

		frame.add(scrollPane);

		// adding action listeners to the buttons
		
		insert.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Case 1: No data was entered
				if (surnameTextField.getText().hashCode() == 0 || initialsTextField.getText().hashCode() == 0 || extensionTextField.getText().hashCode() == 0) {
					JOptionPane.showMessageDialog(frame, "Please enter data");
				}
				else{
					// case 2: invalid data entered
					if(surnameTextField.getText().substring(0, 1).toUpperCase().hashCode() < 65 || surnameTextField.getText().substring(0, 1).toUpperCase().hashCode() > 90){
						JOptionPane.showMessageDialog(frame, "Invalid surname");
					}
					else if(initialsTextField.getText().length() != 4){
						JOptionPane.showMessageDialog(frame, "Invalid initials");
					}
					else if(!extensionTextField.getText().matches("[0-9]+")){
						JOptionPane.showMessageDialog(frame, "Invalid extention number");
					}
					else{
						dir.insert(new Entry(surnameTextField.getText(), initialsTextField.getText(),
								extensionTextField.getText()));
						textArea.setText(dir.toString());
						JOptionPane.showMessageDialog(frame, "Entry inserted");
					}
				}
				
				
			}

		});

		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// Case 1: No data was entered
				if (surnameTextField.getText().hashCode() == 0 && extensionTextField.getText().hashCode() == 0) {
					JOptionPane.showMessageDialog(frame, "Please enter surname or number");
				}
				else{
					if(extensionTextField.getText().matches("^[0-9]+$")){ // check if extension number is valid
						boolean deleteFlag = dir.delete(extensionTextField.getText());
						if (!deleteFlag) {
							JOptionPane.showMessageDialog(frame, "There is no such entry");
						}
						else{
							textArea.setText(dir.toString());
							JOptionPane.showMessageDialog(frame, "Entry deleted");
						}
					}
					else if(extensionTextField.getText().hashCode() != 0 && !extensionTextField.getText().matches("^[0-9]+$")){ // check if number is invalid
						JOptionPane.showMessageDialog(frame, "Invalid extension number");
					}
					else{
						// Case 2: Surname starts with lower case letter or other invalid symbol
						if (surnameTextField.getText().substring(0, 1).hashCode() < 65 || surnameTextField.getText().substring(0, 1).hashCode() > 90){
							// If surname starts with lower case letter, convert it to valid format!
							if(surnameTextField.getText().substring(0, 1).toUpperCase().hashCode() >= 65 && surnameTextField.getText().substring(0, 1).toUpperCase().hashCode() <= 90){
								String sur = surnameTextField.getText().substring(0, 1).toUpperCase() + surnameTextField.getText().substring(1);
								
								
								boolean deleteFlag = dir.delete(sur);
								
								
								if (!deleteFlag) {
									JOptionPane.showMessageDialog(frame, "There is no such entry");
								}
								else{
									textArea.setText(dir.toString());
									JOptionPane.showMessageDialog(frame, "Entry deleted");
								}
							}
							// Surname invalid
							else{
								JOptionPane.showMessageDialog(frame, "Invalid surname");
							}
						}
						// print entry if it exists
						else{
							boolean deleteFlag = dir.delete(surnameTextField.getText());
							
							if (!deleteFlag) {
								JOptionPane.showMessageDialog(frame, "There is no such entry");
							}
							else{
								textArea.setText(dir.toString());
								JOptionPane.showMessageDialog(frame, "Entry deleted");
							}
						}
					}
				}
				
				
				
			}

		});

		findExtensionNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Case 1: No data was entered
				if (surnameTextField.getText().hashCode() == 0) {
					JOptionPane.showMessageDialog(frame, "Please enter surname");
				}
				else{
					// Case 2: Surname starts with lower case letter or other invalid symbol
					if (surnameTextField.getText().substring(0, 1).hashCode() < 65 || surnameTextField.getText().substring(0, 1).hashCode() > 90){
						// If surname starts with lower case letter, convert it to valid format!
						if(surnameTextField.getText().substring(0, 1).toUpperCase().hashCode() >= 65 && surnameTextField.getText().substring(0, 1).toUpperCase().hashCode() <= 90){
							String sur = surnameTextField.getText().substring(0, 1).toUpperCase() + surnameTextField.getText().substring(1);
							// Check if entry was found
							if (dir.findExNumber(sur) == null) {
								JOptionPane.showMessageDialog(frame, "There is no such entry");
							}
							// Entry found, print it!
							else{
								JOptionPane.showMessageDialog(frame, dir.findExNumber(sur));
							}
						}
						// Surname invalid
						else{
							JOptionPane.showMessageDialog(frame, "Invalid surname");
						}
					}
					// check if entry exist
					else if (dir.findExNumber(surnameTextField.getText()) == null) {
						JOptionPane.showMessageDialog(frame, "There is no such entry");
					}
					// print entry if it exists
					else{
						JOptionPane.showMessageDialog(frame, dir.findExNumber(surnameTextField.getText()));
					}
				}
				
			}

		});

		changeNumber.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Case 1: No data was entered
				if (surnameTextField.getText().hashCode() == 0) {
					JOptionPane.showMessageDialog(frame, "Please enter surname");
				}
				else{
					// Case 2: Surname starts with lower case letter or other invalid symbol
					if (surnameTextField.getText().substring(0, 1).hashCode() < 65 || surnameTextField.getText().substring(0, 1).hashCode() > 90){
						// If surname starts with lower case letter, convert it to valid format!
						if(surnameTextField.getText().substring(0, 1).toUpperCase().hashCode() >= 65 && surnameTextField.getText().substring(0, 1).toUpperCase().hashCode() <= 90){
							String sur = surnameTextField.getText().substring(0, 1).toUpperCase() + surnameTextField.getText().substring(1);
							// Check if entry was found
							if (dir.findExNumber(sur) == null) {
								JOptionPane.showMessageDialog(frame, "There is no such entry");
							}
							else{
								changeTheNumber(sur, dir, frame, textArea);
							}
							
						}
						else{
							JOptionPane.showMessageDialog(frame, "Ivalid surname");
						}
					}
					else if (dir.findExNumber(surnameTextField.getText()) == null) {
						JOptionPane.showMessageDialog(frame, "There is no such entry");
					}
					// print entry if it exists
					else{
						changeTheNumber(surnameTextField.getText(), dir, frame, textArea);
					}
				}
				
				
			}

		});
		
		
		print.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				JFrame printFrame = new JFrame("Directory");
				JTextArea area = new JTextArea();
				JScrollPane scrollPane1 = new JScrollPane(area);
				
				printFrame.setSize(450, 800);
				printFrame.setResizable(false);
				printFrame.setLocationRelativeTo(null);
				
				area.setEditable(false);
				area.setFont(new Font("Consolas", 15, 15));
				area.setText(dir.toString());
				
				printFrame.add(scrollPane1);
				
				printFrame.setVisible(true);
			}
			
		});
		
		frame.setVisible(true);	
		

	}
/**
 * This method changed the number. This method helps to reduce lines of code.
 * 
 * 
 * @param surname
 * @param dir
 * @param frame
 * @param textArea
 */
	private static void changeTheNumber(String surname, Directory<Entry> dir, JFrame frame, JTextArea textArea){
		
		// new frame will appear after 'change number' button is clicked
		JFrame changeNumberFrame = new JFrame();
		
		// setting properties for the new frame
		changeNumberFrame.setSize(350, 200);
		changeNumberFrame.setResizable(false);
		changeNumberFrame.setLocationRelativeTo(null);												
		changeNumberFrame.setLayout(new FlowLayout());
		
		// Creating components for the new frame
		JLabel newNumberLabel = new JLabel("Enter new number");
		JTextField newNumberTextField = new JTextField(10);
		JButton button = new JButton("change");
		
		// adding components to the new frame
		changeNumberFrame.add(newNumberLabel);
		changeNumberFrame.add(newNumberTextField);
		changeNumberFrame.add(button);
		changeNumberFrame.setVisible(true);
		
		// action listener for change button that is in the new frame
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// check if data is valid
				if (newNumberTextField.getText().matches("^[0-9]+$")) {

					dir.changeNumber(surname,
							newNumberTextField.getText());
					
						textArea.setText(dir.toString());
						changeNumberFrame.setVisible(false);
						JOptionPane.showMessageDialog(frame, "Number changed");

					
				} else {
					JOptionPane.showMessageDialog(frame, "Number must be valid"); // ask for valid data
				}

			}

		});
		
		
	}
	
}
