package testcode;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class RvTableCellRenderer extends DefaultTableCellRenderer{

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
		Component btn = null;
		if(column==3) {
			btn = new JButton("수정");
		}else if(column==4) {
			btn = new JButton("삭제");
		}
		return btn;
	}
	
}
