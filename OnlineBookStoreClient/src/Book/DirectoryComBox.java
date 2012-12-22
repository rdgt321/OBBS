package Book;

<<<<<<< HEAD
import java.awt.Color;
=======
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
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
<<<<<<< HEAD
=======

	public int getDirectoryID(String name) {
		for (int i = 0; i < this.getItemCount(); i++) {
			if (name.equals(((DirectoryPO) this.getItemAt(i)).getName())) {
				return ((DirectoryPO) this.getItemAt(i)).getID();
			}
			i++;
		}
		return -1;
	}
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
}

@SuppressWarnings("serial")
class MyCellRenderer extends DefaultListCellRenderer {

	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
<<<<<<< HEAD
		if (value != null) {
			setText(((DirectoryPO) value).getName());
		}
=======
		this.setText(((DirectoryPO) value).getName());
>>>>>>> b6f5894d301826f968c00258bd419a29af4e5eca
		return this;
	}

}
