package Book;

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

	public int getDirectoryID(String name) {
		for (int i = 0; i < this.getItemCount(); i++) {
			if (name.equals(((DirectoryPO) this.getItemAt(i)).getName())) {
				return ((DirectoryPO) this.getItemAt(i)).getID();
			}
			i++;
		}
		return -1;
	}
}

@SuppressWarnings("serial")
class MyCellRenderer extends DefaultListCellRenderer {

	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		this.setText(((DirectoryPO) value).getName());
		return this;
	}

}
