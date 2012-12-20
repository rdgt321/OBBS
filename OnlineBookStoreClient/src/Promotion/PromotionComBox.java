package Promotion;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

import ClientRunner.MComboBox;

@SuppressWarnings("serial")
public class PromotionComBox extends MComboBox<PromotionPO> {
	public PromotionComBox() {
		super();
		this.setRenderer(new MyCellRenderer());
	}

	public String getPromotionName(String name) {
		for (int i = 0; i < this.getItemCount(); i++) {
			if (name.equals(((PromotionPO) this.getItemAt(i)).getName())) {
				return ((PromotionPO) this.getItemAt(i)).getName();
			}
			i++;
		}
		return null;
	}
}

class PromotionItem {
	private String name;

	public PromotionItem(PromotionPO promotionPO) {
		this.name = promotionPO.getName();
	}

	public String getName() {
		return name;
	}
}

@SuppressWarnings("serial")
class MyCellRenderer extends DefaultListCellRenderer {

	@SuppressWarnings("rawtypes")
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		if (value != null) {
			this.setText(((PromotionPO) value).getName());
		}
		return this;
	}

}
