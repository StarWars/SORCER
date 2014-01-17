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
import sorcer.core.provider.ServiceProvider;
import sorcer.ui.serviceui.UIComponentFactory;
import sorcer.ui.serviceui.UIDescriptorFactory;
import sorcer.util.Sorcer;

public class StringerView extends JPanel implements Observer {
	
	private static final long serialVersionUID = -3812646466769297683L;

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
	}

	private JPanel buildStringerPanel() {
		JPanel panel = new JPanel();
		return panel;
	}

	private String readTextField(JTextField moneyField) {
		return null;
	}

	public void update(Observable o, Object arg) {

	}

	public static UIDescriptor getUIDescriptor() {
		UIDescriptor uiDesc = null;
		try {
			uiDesc = UIDescriptorFactory.getUIDescriptor(MainUI.ROLE,
					new UIComponentFactory(new URL[] { new URL(Sorcer
							.getWebsterUrl()
							+ "/stringer-mvc-ui.jar") }, StringerView.class
							.getName()));
		} catch (Exception ex) {
			logger.throwing(StringerView.class.getName(), "getUIDescriptor", ex);
		}
		return uiDesc;
	}
}
