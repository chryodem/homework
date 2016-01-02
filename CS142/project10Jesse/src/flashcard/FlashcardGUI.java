package flashcard;

import java.awt.EventQueue;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FlashcardGUI {
	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FlashcardGUI window = new FlashcardGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public FlashcardGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Flash Card Program");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent arg0) {		//****************************************
				boolean correctFileType = false;
				String fileName = "";
				CharSequence fileType = ".rtml";
				WrongFileTypeException exception = new WrongFileTypeException("");
				int flashcardLength = 0;
				
				while(!correctFileType) {
					try {
						JFileChooser chooser = new JFileChooser();
						FileNameExtensionFilter filter = new FileNameExtensionFilter(
								"RTML files", "rtml");
						chooser.setFileFilter(filter);
						int returnVal = chooser.showOpenDialog(frame);
						if(returnVal == JFileChooser.APPROVE_OPTION) {
							fileName = chooser.getSelectedFile().getName();
							if (fileName.contains(fileType)) {
								correctFileType = true;
							} else {
								throw exception;
							}
						}
					} catch (WrongFileTypeException e) {
						JOptionPane.showMessageDialog(frame,
							    "Incorrect File Type chosen. \nPlease choose a .rtml file.",
							    "Error: File Type",
							    JOptionPane.ERROR_MESSAGE);
					}
				}
				try {
					flashcardLength = checkLength(fileName);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				Flashcard flashcard1 = null;
				flashcard1 = new Flashcard(new Word[flashcardLength], fileName);
				
				try {
					flashcard1.readData(fileName);  //
					
					
					String[] tableNames = {"Vocab Word", "Definition"};

					final GuiTableMaker TABLE1 = new GuiTableMaker(flashcard1.getDoubleArray(), tableNames);
					JComponent newContentPane = TABLE1;

					frame.getContentPane().add(TABLE1, BorderLayout.CENTER);
			        
//			        frame.pack();
			        frame.setVisible(true);
					
				} catch (BadFormatException e) {
					
					JOptionPane.showMessageDialog(frame,
						    e.getMessage(),
						    "Error: Incorrect Format",
						    JOptionPane.ERROR_MESSAGE);
//					System.out.println(e.getMessage());
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}							// ********************************************
		});
		mnFile.add(mntmOpen);
		
		JMenu mnPractice = new JMenu("Practice");
		menuBar.add(mnPractice);
		
	}
	
	public static int checkLength(String fileName) throws IOException {
		int length = 0;
		Scanner fileScan;
		
		fileScan = new Scanner (new File(fileName));
		fileScan.useDelimiter("[<>\n\r]");
		
		while (fileScan.hasNext()) {
			String nextWord = "";
			nextWord = fileScan.next();
			if (nextWord.equals("word")) {
				length++;
			}
		}
		return length;
	}
}
