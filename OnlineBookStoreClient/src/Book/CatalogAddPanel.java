package Book;

import java.awt.Font;

import javax.swing.JLabel;

import ClientRunner.MPanel;
import ClientRunner.MTextField;

@SuppressWarnings("serial")
public class CatalogAddPanel extends MPanel{
	private BookUIController bookUIController;
	
	private JLabel newKindLabel;
	private MTextField newKindField;
	
	public CatalogAddPanel(BookUIController bookUIController){
		this.bookUIController = bookUIController;
	}
	
	public void init(){
		setLayout(null);
		setSize(425, 100);
		
		newKindLabel = new JLabel("添加图书类别:");
		newKindLabel.setSize(180, 50);
		newKindLabel.setFont(new Font("楷体_gb2312", Font.PLAIN, 23));
		newKindLabel.setLocation(50, 10);
		
		newKindField = new MTextField();
		newKindField.setSize(100, 40);
		newKindField.setFont(new Font("楷体_gb2312", Font.PLAIN, 23));
		newKindField.setLocation(240, 15);
		
		add(newKindLabel);
		add(newKindField);
	}
	
	public String getCatalog(){
		return newKindField.getText().trim();
	}
	
	public void clear(){
		newKindField.setText("");
	}
}
