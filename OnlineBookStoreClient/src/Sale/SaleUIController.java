package Sale;

import ClientRunner.MainFrame;

public class SaleUIController {
	private MainFrame mainFrame;
	private PaymentPanel paymentPanel;
	private CartPanel cartPanel;
	private DealSuccessPanel dealSuccessPanel;

	public SaleUIController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void setCartView() {
		cartPanel = new CartPanel(this);
		cartPanel.init();
		mainFrame.clear();
		mainFrame.add(cartPanel);
		cartPanel.requestFocus();
		cartPanel.validate();
	}

	public void setPaymentView(int orderID) {
		paymentPanel = new PaymentPanel(this, orderID);
		paymentPanel.init();
		mainFrame.clear();
		mainFrame.add(paymentPanel);
		paymentPanel.requestFocus();
		paymentPanel.validate();
	}

	public void setDealSuccessView() {
		if (dealSuccessPanel == null) {
			dealSuccessPanel = new DealSuccessPanel(this);
			dealSuccessPanel.init();
		}
		mainFrame.clear();
		mainFrame.add(dealSuccessPanel);
		dealSuccessPanel.requestFocus();
		dealSuccessPanel.validate();
	}

	public MainFrame getMainFrame() {
		return mainFrame;
	}
}
