package flashcard;

import java.awt.EventQueue;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.JTable;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class FlashcardWindows {

	private JFrame frmFlashcardReader;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
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
	private void initialize() {
		frmFlashcardReader = new JFrame();
		frmFlashcardReader.setTitle("Flashcard Reader");
		frmFlashcardReader.setBounds(100, 100, 450, 300);
		frmFlashcardReader.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// public myPanel(string[][] array)
		// create panel and then create the jtable
		// DefaultTableModel model = new DefaultTableModel(words2D);
		// JTable table = new JTable(model);
		// private class key listener implement keylistener.

		JMenuBar menuBar = new JMenuBar();
		frmFlashcardReader.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmOpen = new JMenuItem("Open"); //creates menu open
		mntmOpen.addActionListener(new ActionListener() { //adds action listener
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser("."); //when action listener is used let's the user choose files
				chooser.showOpenDialog(chooser); //shows the dialog to choose the file
				File chosenFile = chooser.getSelectedFile(); //gets the files
				String fileName = chosenFile.getName(); //gets the name of the file
				String filePath = chosenFile.getAbsolutePath(); //stores the absolute path to the selected file

				if (fileName.endsWith(".rtml")) { //checks in the file ends with .rtml
					
					Flashcard flashcard = new Flashcard();
					flashcard.readData(filePath); //passes in path of the file into the flashcard class
					JTable wordGrid = new JTable();
					DefaultTableModel model = new DefaultTableModel();
					wordGrid.setModel(model);
					model.setColumnIdentifiers(new String[] {"Word", "Def"});
					wordGrid.setVisible(true);
					frmFlashcardReader.getContentPane().add(wordGrid, BorderLayout.WEST);

					// Populate the JTable (TableModel) with data from ArrayList
					for (Word p : flashcard.theWordList)
					{
					   model.addRow(new String[] {p.getVocabWord(), p.getDefinition()});
					}
					
					
				//	myPanel gridPanel = new myPanel();
					//gridPanel.JTableCreation(String words2D);
				//	frame.getContentPane().add(gridPanel);
				//	frame.refresh();
					
					
					

				} else {
					System.out.println("INCORRECT TYPE OF FILE"); //if invalid file type is chosen shows error
				}

			}
		});
		mnFile.add(mntmOpen);

		JMenu mnPractice = new JMenu("Practice");
		menuBar.add(mnPractice);
	}

}
