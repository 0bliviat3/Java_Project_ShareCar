package testcode;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class CmTableCellRenderer extends DefaultTableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		Component deleteBtn = null;
		if(column==3) {
			deleteBtn = new JButton("ªË¡¶");
		}
		return deleteBtn;
	}
	

}
