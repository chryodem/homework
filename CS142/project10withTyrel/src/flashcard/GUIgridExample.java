package flashcard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GUIgridExample extends JPanel {
	
	//creates JTable
	public GUIgridExample(String[][] W) {
		final DefaultTableModel model = new DefaultTableModel(W, new String[] {	"word", "def" });
		final JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		//sets specific width for the columns in the JTable
		int vColIndex = 0;
		TableColumn col = table.getColumnModel().getColumn(vColIndex);
		int width = 100;
		col.setPreferredWidth(width);

		int vColIndex2 = 1;

		TableColumn col2 = table.getColumnModel().getColumn(vColIndex2);

		int width2 = 400;

		col2.setPreferredWidth(width2);

		add(table);
		
		//creates event handler when the cursor is on the last column of the table to create a new line
		table.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '\t') {
					if (table.getSelectedRow() == table.getRowCount() - 1 && table.getSelectedColumn() == table.getColumnCount() - 1) 
					{
						model.insertRow(model.getRowCount(),
								new Object[] { "" });
					}
				}
			}

		});
	}

}
