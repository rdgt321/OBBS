package Promotion;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import ClientRunner.MComboBox;

@SuppressWarnings("serial")
public class DiscoutBondBox extends MComboBox<CouponsPO> {
	public DiscoutBondBox() {
		super();
		this.setRenderer(new CellRender());
	}
}

@SuppressWarnings("serial")
class CellRender extends DefaultListCellRenderer {
	@SuppressWarnings("rawtypes")
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value != null) {
			this.setText(((CouponsPO) value).getDiscountRate() + "折--券号:"
					+ ((CouponsPO) value).getCounponsID());
		}
		return this;
	}
}
