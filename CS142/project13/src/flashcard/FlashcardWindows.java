package flashcard;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.TableColumn;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;

public class FlashcardWindows {

	JMenuItem mntmSave = new JMenuItem("Save");
	private JFrame frmFlashcardReader;
	private JFrame test;
	GUIgridExample grid;
	Flashcard flashcard;// = new GUIgridExample(null);
	JRadioButton rdbtnChoice;
	JRadioButton rdbtnChoice_1;
	JRadioButton rdbtnChoice_2;
	String newDef;
	String wrongDef1;
	String wrongDef2;
	String newWord;
	String correctDef;
	JLabel lblNewLabel;
	int one;
	int two;
	int three; 
	int rNC;
	int rN2;
	int rN3;
	int row;
	double wrongAnswer; //= 0;
	double correctAnswer;// = 0;
	double totalTries;// = 0;
	double score;// = 0;
	int firstTime;
	private ArrayList<Word> Words;//creates array list
	private Word temp;
	int index;
	int r;
	String word;
	String def;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() 
			
			{
				try {
					FlashcardWindows window = new FlashcardWindows();
					window.frmFlashcardReader.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FlashcardWindows() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() 
	{ //opens the original application
		frmFlashcardReader = new JFrame();
		frmFlashcardReader.setTitle("Flashcard Reader");
		frmFlashcardReader.setBounds(100, 100, 450, 300);
		frmFlashcardReader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JMenuBar menuBar = new JMenuBar();
		frmFlashcardReader.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenuItem mntmOpen = new JMenuItem("Open");

		mnFile.add(mntmOpen);

		mntmSave = new JMenuItem("Save",KeyEvent.VK_T);
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FileDialog fd = new FileDialog((FileDialog)null, "SAVE FILE", FileDialog.SAVE);

				fd.setVisible(true);
				String fileName = fd.getFile();
				String filePath = fd.getDirectory();
//				JFileChooser chooser = new JFileChooser();
//								chooser.showSaveDialog(chooser);
//								File chosenFile = chooser.getSelectedFile();
//								//String fileName = chosenFile.getName();
//								String filePath = chosenFile.getAbsolutePath();
				String xmlText = grid.XMLTabelText();
				
				try {
					File f = new File(filePath + fileName);
					PrintWriter outFile = new PrintWriter(f);
					outFile.println(xmlText);
					outFile.close();
					
					//call method from GUIgridExample
					
					
				} catch (FileNotFoundException e) {
					
					//e.printStackTrace();
				}
								
				
			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		
		mntmSave.setEnabled(false);
		mnFile.add(mntmSave);
		
		JMenuItem mntmClose = new JMenuItem("Close");
		mnFile.add(mntmClose);

		JMenu mnPractice = new JMenu("Practice");
		menuBar.add(mnPractice);
		
		JMenuItem mntmTest = new JMenuItem("Test"); //creates test instance
		mnPractice.add(mntmTest);
		mntmTest.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				
				String[] stringToRecieve = Choices();
				String newWord = stringToRecieve[0]; //correct word
				newDef = stringToRecieve[1]; // correct definition
				String wrongDef1 = stringToRecieve[2]; // wrong def1
				String wrongDef2 = stringToRecieve[3]; // wrong def2
				
				//randomizer for assigning values randomly to Radiobuttons
//				int one;
//				int two;
//				int three; 

			
				
				ButtonGroup buttonGroup = new ButtonGroup();
				test = new JFrame();
				test.setBounds(100, 100, 450, 300);
				//test.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				lblNewLabel = new JLabel("New label");
				test.getContentPane().add(lblNewLabel, BorderLayout.NORTH);
				
				JPanel panel = new JPanel();
				test.getContentPane().add(panel, BorderLayout.WEST);
				
				//JRadioButton rdbtnNewRadioButton = new JRadioButton("New radio button");
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				
				rdbtnChoice = new JRadioButton("choice1");
				buttonGroup.add(rdbtnChoice);
				panel.add(rdbtnChoice);
				
				rdbtnChoice_1 = new JRadioButton("choice2");
				buttonGroup.add(rdbtnChoice_1);
				panel.add(rdbtnChoice_1);
				
				rdbtnChoice_2 = new JRadioButton("choice3");
				buttonGroup.add(rdbtnChoice_2);
				panel.add(rdbtnChoice_2);
				
				JButton btnCheck = new JButton("Check");
				btnCheck.addActionListener(new ActionListener() { //check button listener
					public void actionPerformed(ActionEvent arg0) {

						boolean rightAnswer = false; //check for right answer
						if (rdbtnChoice.isSelected()){
							if(rdbtnChoice.getText().equals(newDef)){
								if(wrongAnswer == 0){
									firstTime++;
									Words.remove(temp);
									
								}
								if(!(r==1)){
									reDo();
									}
								else{
									
								}
								rightAnswer=true;
							}
						}
						else if(rdbtnChoice_1.isSelected()){
							if(rdbtnChoice_1.getText().equals(newDef)){
								if(wrongAnswer == 0){
									firstTime++;
									Words.remove(temp);
									
								}
								if(!(r==1)){
									reDo();
									}
								else{
									
								}
								rightAnswer=true;
							}
						}
						else if(rdbtnChoice_2.isSelected()){
							if(rdbtnChoice_2.getText().equals(newDef)){
								if(wrongAnswer == 0){
									firstTime++;
									Words.remove(temp);
									
								}
								if(!(r==1)){
								reDo();
								}
								else{
									
								}
								
								rightAnswer = true;
							}
						}
						
						if(rightAnswer){
							
							JOptionPane.showMessageDialog(test, "You are correct!");
						
							correctAnswer++;
							totalTries++;
							//System.out.println("number of correct answers is: " + correctAnswer);
							score = (correctAnswer/totalTries)*100;
							System.out.println("Percentage is " + score + "%");
							System.out.println("The number of times you guess right the first time is " + firstTime);
							
							
							
							
												
						}//end of if Right
						
						else {
							JOptionPane.showMessageDialog(test, "Try again!");
							//System.out.println("wrong!");
							//wrongAnswer++;
							totalTries++;
							wrongAnswer++;
							System.out.println("number of wrong answers is: " +wrongAnswer);
						}
						
						
					}
				});
				
				test.getContentPane().add(btnCheck, BorderLayout.EAST);
				
				JButton btnQuit = new JButton("Quit");
				btnQuit.addActionListener(new ActionListener() { //quit button listener
					public void actionPerformed(ActionEvent arg0) {
						
						System.exit(0);
						
					}
				});
				test.getContentPane().add(btnQuit, BorderLayout.SOUTH);	
				
				
				test.setVisible(true);
				lblNewLabel.setText(newWord); //shows the word
				
				Random rand = new Random();
				one = 1+ rand.nextInt(3);
				two = 1 + rand.nextInt(3);
				three = 1 + rand.nextInt(3);

				while ( one == two || two == three || one == three) // to make sure numbers on those are different
				{
				two = 1 + rand.nextInt(3);
				three = 1 + rand.nextInt(3);
				}
				
				if (one == 1){
					rdbtnChoice.setText(newDef);
					rdbtnChoice_1.setText(wrongDef1);
					rdbtnChoice_2.setText(wrongDef2);
					
				}
				if (one == 2){
					rdbtnChoice.setText(wrongDef1);
					rdbtnChoice_1.setText(wrongDef2);
					rdbtnChoice_2.setText(newDef);
				}
				if (one == 3){
					rdbtnChoice.setText(wrongDef2);
					rdbtnChoice_1.setText(wrongDef1);
					rdbtnChoice_2.setText(newDef);
				}
				/*
				switch(one){ //assigning value to the buttons
				
				case 0:
					rdbtnChoice.setText(newDef);
					rdbtnChoice_1.setText(wrongDef1);
					rdbtnChoice_2.setText(wrongDef2);
					break;
				case 1:
					rdbtnChoice.setText(wrongDef1);
					rdbtnChoice_1.setText(wrongDef2);
					rdbtnChoice_2.setText(newDef);
					break;
				case 2:
					rdbtnChoice.setText(wrongDef2);
					rdbtnChoice_1.setText(wrongDef1);
					rdbtnChoice_2.setText(newDef);
					break;
				default: //never uses
					
					
				}//end of switch
				*/
				
				
			}//end of action performed in listener
		} //end of listener
		);

		class openFile implements ActionListener 
		{
			//mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				FileDialog fd = new FileDialog((FileDialog)null, "open file", FileDialog.LOAD);

				fd.setVisible(true);
				String fileName = fd.getFile();
				String filePath = fd.getDirectory();
				//				JFileChooser chooser = new JFileChooser(".");
				//				chooser.showOpenDialog(chooser);
				//				File chosenFile = chooser.getSelectedFile();
				//				String fileName = chosenFile.getName();
				//				String filePath = chosenFile.getAbsolutePath();


				if (fileName.endsWith(".rtml")) 
				{
					//System.out.println("I'm opening the file");
					flashcard = new Flashcard();
					mntmSave.setEnabled(true);
					flashcard.readData(filePath + fileName);
					grid = new GUIgridExample(flashcard.conversion());
					frmFlashcardReader.getContentPane().add(grid, BorderLayout.CENTER);
					frmFlashcardReader.setBounds(200, 200, 600, 400);



				} else 
				{
					System.out.println("INCORRECT TYPE OF FILE");
				}

			}

		};

		mntmOpen.addActionListener(new openFile());

		//frmFlashcardReader.pack();
		frmFlashcardReader.setVisible(true);
	} //end of initialize, original JFrame
	
	public String[] Choices(){
		JTable myTable = new JTable();
		myTable = grid.table;
		row = myTable.getRowCount();
		Random rand = new Random();
		
		//rNC = rand.nextInt(row);
		rN2 = rand.nextInt(row);
		rN3 = rand.nextInt(row);
		while(rNC == rN2 || rN2 == rN3 || rNC == rN3){ //makes sure random numbers are different
			rN2= rand.nextInt(row);
			rN3= rand.nextInt(row);
		}
		Words = flashcard.getArrayList();
		r = Words.size(); 
		index = rand.nextInt(r);
		temp = Words.get(index); //random location in the array list
		
		word = temp.getVocabWord();
		def = temp.getDefinition();
		//newWord = (String) myTable.getValueAt(rNC, 0);
		//correctDef = (String) myTable.getValueAt(rNC, 1);
		wrongDef1 = (String)myTable.getValueAt(rN2, 1);
		wrongDef2 = (String)myTable.getValueAt(rN3, 1);
		
		while(def.equals(wrongDef1)||wrongDef1.equals(wrongDef2)||wrongDef2.equals(def)){
			rN2 = rand.nextInt(row);
			rN3 = rand.nextInt(row);
			wrongDef1 = (String)myTable.getValueAt(rN2, 1);
			wrongDef2 = (String)myTable.getValueAt(rN3, 1);
			//System.out.println("stuck inside of while loop");
		}
		
		newWord = word;
		correctDef = def;
		String[] stringToReturn ={newWord, correctDef,wrongDef1,wrongDef2};
		
		return stringToReturn;
		} //end of choices
	
	public void reDo(){
		String[] stringToRecieve = Choices();
		newWord = stringToRecieve[0]; //correct word
		newDef = stringToRecieve[1]; // correct definition
		wrongDef1 = stringToRecieve[2]; // wrong def1
		wrongDef2 = stringToRecieve[3]; // wrong def2
		

		Random rand = new Random();
		one = 1+ rand.nextInt(3);
		two = 1 + rand.nextInt(3);
		three = 1 + rand.nextInt(3);

		while ( one == two || two == three || one == three) // to make sure numbers on those are different
		{
		two = 1 + rand.nextInt(3);
		three = 1 + rand.nextInt(3);
		}
		
		lblNewLabel.setText(newWord); //shows the word
		

		if (one == 1){
			rdbtnChoice.setText(newDef);
			rdbtnChoice_1.setText(wrongDef1);
			rdbtnChoice_2.setText(wrongDef2);
			
		}
		if (one == 2){
			rdbtnChoice.setText(wrongDef1);
			rdbtnChoice_1.setText(wrongDef2);
			rdbtnChoice_2.setText(newDef);
		}
		if (one == 3){
			rdbtnChoice.setText(wrongDef2);
			rdbtnChoice_1.setText(wrongDef1);
			rdbtnChoice_2.setText(newDef);
		}
		wrongAnswer=0;
		
		/*
		
		switch(one){ //assigning value to the buttons
		
		case 0:
			rdbtnChoice.setText(newDef);
			rdbtnChoice_1.setText(wrongDef1);
			rdbtnChoice_2.setText(wrongDef2);
			break;
		case 1:
			rdbtnChoice.setText(wrongDef1);
			rdbtnChoice_1.setText(wrongDef2);
			rdbtnChoice_2.setText(newDef);
			break;
		case 2:
			rdbtnChoice.setText(wrongDef2);
			rdbtnChoice_1.setText(wrongDef1);
			rdbtnChoice_2.setText(newDef);
			break;
		default: //never uses
		
	}
	*/
	}

}
