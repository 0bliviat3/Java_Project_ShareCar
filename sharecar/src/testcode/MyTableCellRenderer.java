package testcode;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyTableCellRenderer extends DefaultTableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		Component likesBtn = null;
		if(column==4) {
			likesBtn = new JButton("ÃßÃµ");
		}
				
				
		return likesBtn;
	}
	

}
