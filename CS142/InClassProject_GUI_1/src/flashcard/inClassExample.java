package flashcard;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.TextField;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class inClassExample {

	private JFrame frame;
	private JTextField txtSayHi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					inClassExample window = new inClassExample();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inClassExample() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmHi = new JMenuItem("hi");
		mntmHi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				txtSayHi.setText("say hi");
			}
		});
		mnFile.add(mntmHi);
		
		txtSayHi = new JTextField();
		//txtSayHi.setText("say hi");
		frame.getContentPane().add(txtSayHi, BorderLayout.SOUTH);
		txtSayHi.setColumns(10);
	}

}
