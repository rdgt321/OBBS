package Sale;

import ClientRunner.MainFrame;

public class SaleUIController {
	private MainFrame mainFrame;
	private OrderEnsurePanel orderEnsurePanel;
	private PaymentPanel paymentPanel;
	private CartPanel cartPanel;

	public SaleUIController(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
	}

	public void setCartView() {
		if (cartPanel == null) {
			cartPanel = new CartPanel(this);
			cartPanel.init();
		}
		mainFrame.clear();
		mainFrame.add(cartPanel);
		cartPanel.requestFocus();
		cartPanel.validate();
	}

	public void setOrderEnsureView() {
		if (orderEnsurePanel == null) {
			orderEnsurePanel = new OrderEnsurePanel(this);
			orderEnsurePanel.init();
		}
		mainFrame.clear();
		mainFrame.add(orderEnsurePanel);
		orderEnsurePanel.requestFocus();
		orderEnsurePanel.validate();
	}

	public void setPaymentView() {
		if (paymentPanel == null) {
			paymentPanel = new PaymentPanel(this);
			paymentPanel.init();
		}
		mainFrame.clear();
		mainFrame.add(paymentPanel);
		paymentPanel.requestFocus();
		paymentPanel.validate();
	}
}
