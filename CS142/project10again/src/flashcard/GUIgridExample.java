package flashcard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GUIgridExample extends JTable {

	public GUIgridExample(String[][] W) {
		final DefaultTableModel model = new DefaultTableModel(W, new String[] {	"word", "def" });
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
