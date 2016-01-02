package flashcard;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;


public class GuiTableMaker extends JPanel{
	public GuiTableMaker(String[][] flashcard, String[] columnNames) {
		super(new GridLayout(1,0));
		
		final DefaultTableModel model = new DefaultTableModel(flashcard, columnNames);
		final JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		int vColIndex = 0;
		TableColumn col = table.getColumnModel().getColumn(vColIndex);
		int width = 100;
		col.setPreferredWidth(width);
		
		int vColIndex2 = 1;
		TableColumn col2 = table.getColumnModel().getColumn(vColIndex2);
		int width2 = 400;
		col2.setPreferredWidth(width2);
		
		add(table);
		
		
		
		
		table.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(e.getKeyChar() == '\t') {
//					System.out.println("Selected Column: " + table.getSelectedColumn());
//					System.out.println("Selected Row: " + table.getSelectedRow());
//					System.out.println("Column Count: " + table.getColumnCount());
//					System.out.println("Row Count: " + table.getRowCount());
					
					if (table.getSelectedRow() == table.getRowCount()-1 &&
							table.getSelectedColumn() == table.getColumnCount()-1) {
						
						model.insertRow(model.getRowCount(), new Object[]{""});
					}
				}
			}
		});
		//	end of constructor
	}
}
