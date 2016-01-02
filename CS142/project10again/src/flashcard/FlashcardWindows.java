package flashcard;

import java.awt.BorderLayout;
import java.awt.EventQueue;

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
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser chooser = new JFileChooser(".");
				chooser.showOpenDialog(chooser);
				File chosenFile = chooser.getSelectedFile();
				String fileName = chosenFile.getName();
				String filePath = chosenFile.getAbsolutePath();
				

				if (fileName.endsWith(".rtml")) 
				{
					Flashcard flashcard = new Flashcard();
					flashcard.readData(filePath);
					final GUIgridExample grid = new GUIgridExample(flashcard.conversion());
					frmFlashcardReader.getContentPane().add(grid, BorderLayout.CENTER);
					frmFlashcardReader.setBounds(200, 200, 600, 400);

				} else 
				{
					System.out.println("INCORRECT TYPE OF FILE");
				}

			}
		});
		mnFile.add(mntmOpen);

		JMenu mnPractice = new JMenu("Practice");
		menuBar.add(mnPractice);
		frmFlashcardReader.pack();
		frmFlashcardReader.setVisible(true);
	}

}
