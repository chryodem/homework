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
import javax.swing.filechooser.FileFilter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

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

		JMenuBar menuBar = new JMenuBar();
		frmFlashcardReader.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		JMenuItem mntmOpen = new JMenuItem("Open");
		
		mnFile.add(mntmOpen);

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
					System.out.println("I'm opening the file");
					Flashcard flashcard = new Flashcard();
					flashcard.readData(filePath + fileName);
					final GUIgridExample grid = new GUIgridExample(flashcard.conversion());
					frmFlashcardReader.getContentPane().add(grid, BorderLayout.CENTER);
					frmFlashcardReader.setBounds(200, 200, 600, 400);
					

				} else 
				{
					System.out.println("INCORRECT TYPE OF FILE");
				}

			}
		
		};
		
		mntmOpen.addActionListener(new openFile());
		
		frmFlashcardReader.pack();
		frmFlashcardReader.setVisible(true);
	}
	

}
