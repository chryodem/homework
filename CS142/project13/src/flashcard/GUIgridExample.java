package flashcard;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class GUIgridExample extends JPanel {
	final DefaultTableModel model; 
	final JTable table;
	
	//creates JTable
	public GUIgridExample(String[][] W) {
		//table.get
		
		model = new DefaultTableModel(W, new String[] {	"word", "def" });
		table  = new JTable(model);
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
		table.addKeyListener(new KeyAdapter() 
		{
			
			public void keyPressed(KeyEvent e) {
				if (e.getKeyChar() == '\t') {
					if (table.getSelectedRow() == table.getRowCount() - 1 && table.getSelectedColumn() == table.getColumnCount() - 1) 
					{
						model.insertRow(model.getRowCount(),
								new String[] { "","" });
					}
				}
			}

		} //end of "tab" key listener
		);
	} //end of GUIgridExample constructor
	public JTable getTable(){
		return table;
	}
	/*
	
	public String[] Choices(){
	int row = table.getRowCount();
	Random rand = new Random();
	int rNC = rand.nextInt(row);
	int rN2 = rand.nextInt(row);
	int rN3 = rand.nextInt(row);
	while(rNC == rN2 || rN2 == rN3 || rNC == rN3){ //makes sure random numbers are different
		rN2= rand.nextInt(row);
		rN3= rand.nextInt(row);
	}
	String newWord = (String) table.getValueAt(rNC, 0);
	String newDef = (String) table.getValueAt(rNC, 1);
	String wrongDef1 = (String)table.getValueAt(rN2, 1);
	String wrongDef2 = (String)table.getValueAt(rN3, 1);
	
	
	String[] stringToReturn ={newWord, newDef,wrongDef1,wrongDef2};
	
	return stringToReturn;
	}
	 */

	public String XMLTabelText() {
		
		String xml = "<vocab>\n";


		//getValueAt - returns what's in the cell;


		for( int i=0; i < table.getRowCount() ; i++){
		String vocabWord = (String) table.getValueAt(i,0);	
		String vocabDef = (String) table.getValueAt(i,1);
		if( !vocabWord.equals("") && !vocabDef.equals("") ){

		xml += "<word>";
		//add word
		xml += table.getValueAt (i,0);// for the words
		xml += "</word \n>";

		xml+="<def>";
		//add def
		xml += table.getValueAt (i,1);
		xml+="</def> \n";
					}
		}

		xml += "</vocab>\n";
		
		//System.out.println(xml);
		return xml;
	}//end of XMLTableText

}
