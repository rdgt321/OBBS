package Book;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import ClientRunner.MComboBox;

@SuppressWarnings("serial")
public class DirectoryComBox extends MComboBox<DirectoryPO> {
	public DirectoryComBox() {
		super();
		this.setRenderer(new MyCellRenderer());
	}
}

@SuppressWarnings("serial")
class MyCellRenderer extends DefaultListCellRenderer {

	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value != null) {
			setText(((DirectoryPO) value).getName());
		}
		return this;
	}

}
