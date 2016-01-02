package flashcard;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FileDialog;

import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class FlashcardWindows {

	JMenuItem mntmSave = new JMenuItem("Save");
	private JFrame frmFlashcardReader;
	GUIgridExample grid;
	Flashcard flashcard;// = new GUIgridExample(null);

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
	private void initialize() {
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

		JMenu mnPractice = new JMenu("Practice");
		menuBar.add(mnPractice);



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
	}


}
