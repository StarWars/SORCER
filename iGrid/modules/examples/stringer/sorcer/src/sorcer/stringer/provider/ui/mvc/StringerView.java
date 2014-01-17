package sorcer.stringer.provider.ui.mvc;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.net.URL;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.jini.core.lookup.ServiceItem;
import net.jini.lookup.entry.UIDescriptor;
import net.jini.lookup.ui.MainUI;
import sorcer.stringer.provider.Stringer;
import sorcer.stringer.provider.Money;
import sorcer.core.provider.ServiceProvider;
import sorcer.ui.serviceui.UIComponentFactory;
import sorcer.ui.serviceui.UIDescriptorFactory;
import sorcer.util.Sorcer;

public class StringerView extends JPanel implements Observer {
	
	private static final long serialVersionUID = -3812646466769297683L;

	private JTextField balanceTextField;

	private JTextField withdrawalTextField;

	private JTextField depositTextField;

	private StringerModel model;

	private StringerDispatcher dispatcher;

	private final static Logger logger = Logger
			.getLogger("sorcer.provider.stringer.ui.mvc");

	public StringerView(Object provider) {
		super();
		getAccessibleContext().setAccessibleName("StringerView Tester");
		ServiceItem item = (ServiceItem) provider;

		if (item.service instanceof Stringer) {
			Stringer stringer = (Stringer) item.service;
			model = new StringerModel();
			//dispatcher = new StringerDispatcher(model, this, stringer);
			createView();
			model.addObserver(this);
			//dispatcher.getBalance();
		}
	}

	protected void createView() {
//		setLayout(new BorderLayout());
//		add(buildStringerPanel(), BorderLayout.CENTER);
	}

//	private JPanel buildStringerPanel() {
//		JPanel panel = new JPanel();
//		JPanel actionPanel = new JPanel(new GridLayout(3, 3));
//
//		actionPanel.add(new JLabel("Current Balance"));
//		balanceTextField = new JTextField();
//		balanceTextField.setEnabled(false);
//		actionPanel.add(balanceTextField);
//		actionPanel.add(new JLabel(" cents"));
//
//		actionPanel.add(new JLabel(StringerModel.WITHDRAW));
//		withdrawalTextField = new JTextField();
//		actionPanel.add(withdrawalTextField);
//		JButton withdrawalButton = new JButton("Do it");
//		withdrawalButton.setActionCommand(StringerModel.WITHDRAW);
//		withdrawalButton.addActionListener(dispatcher);
//		actionPanel.add(withdrawalButton);
//
//		actionPanel.add(new JLabel(StringerModel.DEPOSIT));
//		depositTextField = new JTextField();
//		actionPanel.add(depositTextField);
//		JButton depositButton = new JButton("Do it");
//		depositButton.setActionCommand(StringerModel.DEPOSIT);
//		depositButton.addActionListener(dispatcher);
//		actionPanel.add(depositButton);
//
//		panel.add(actionPanel);
//		return panel;
//	}
//
//	public Money getDepositAmount() {
//		return readTextField(depositTextField);
//	}
//
//	public Money getWithdrawalAmount() {
//		return readTextField(withdrawalTextField);
//	}
//
//	public void clearDepositAmount() {
//		depositTextField.setText("");
//	}
//
//	public void clearWithdrawalAmount() {
//		withdrawalTextField.setText("");
//	}
//
//	public void displayBalance() {
//		Money balance = model.getBalance();
//		balanceTextField.setText(balance.value());
//	}
//
//	private Money readTextField(JTextField moneyField) {
//		try {
//			Float floatValue = new Float(moneyField.getText());
//			float actualValue = floatValue.floatValue();
//			int cents = (int) (actualValue * 100);
//			return new Money(cents);
//		} catch (Exception e) {
//			logger.info("Field doesn't contain a valid value");
//		}
//		return null;
//	}

	public void update(Observable o, Object arg) {
//		logger.info("update>>arg: " + arg);
//		if (arg != null) {
//			if (arg.equals(StringerModel.DEPOSIT))
//				clearDepositAmount();
//			else if (arg.equals(StringerModel.WITHDRAW))
//				clearWithdrawalAmount();
//			else if (arg.equals(StringerModel.BALANCE))
//				displayBalance();;
//		}
	}

	/**
	 * Returns a service UI descriptorfor this service. Usally this method is
	 * used as an entry in provider configuration files when smart proxies are
	 * deployed with a standard off the shelf {@link ServiceProvider}.
	 * 
	 * @return service UI descriptor
	 */
	public static UIDescriptor getUIDescriptor() {
		UIDescriptor uiDesc = null;
		try {
			uiDesc = UIDescriptorFactory.getUIDescriptor(MainUI.ROLE,
					new UIComponentFactory(new URL[] { new URL(Sorcer
							.getWebsterUrl()
							+ "/accout-mvc-ui.jar") }, StringerView.class
							.getName()));
		} catch (Exception ex) {
			logger.throwing(StringerView.class.getName(), "getUIDescriptor", ex);
		}
		return uiDesc;
	}

}
