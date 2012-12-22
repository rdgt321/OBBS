package Promotion;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import ClientRunner.MComboBox;

@SuppressWarnings("serial")
public class EquiBondBox extends MComboBox<EquivalentBondPO> {
	public EquiBondBox() {
		super();
		this.setRenderer(new MyCellRender());
	}

	@SuppressWarnings("serial")
	class MyCellRender extends DefaultListCellRenderer {

		@SuppressWarnings("rawtypes")
		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			if (value != null) {
				this.setText(((EquivalentBondPO) value)
						.getEquivalentDenomination()
						+ "元--券号"
						+ ((EquivalentBondPO) value).getEquivalentBondID());
			}
			return this;
		}
	}
}
