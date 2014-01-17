package sorcer.stringer.provider.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
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

public class StringerUI extends JPanel {

	private static final long serialVersionUID = -3171243785170712405L;

	private JTextField balanceTextField;

	private JTextField withdrawalTextField;

	private JTextField depositTextField;

	private Stringer stringer;

	private ServiceItem item;

	private final static Logger logger = Logger.getLogger(StringerUI.class
			.getName());

	public StringerUI(Object provider) {
		super();
		getAccessibleContext().setAccessibleName("Stringer Tester");
		item = (ServiceItem) provider;

		if (item.service instanceof Stringer) {
			stringer = (Stringer) item.service;
			createUI();
		}

	}

	protected void createUI() {
		setLayout(new BorderLayout());
		add(buildStringerPanel(), BorderLayout.CENTER);
		resetBalanceField();
	}

	private JPanel buildStringerPanel() {
		JPanel panel = new JPanel();

		return panel;
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
							+ "/accout-ui.jar") }, StringerUI.class.getName()));
		} catch (Exception ex) {
			logger.throwing(StringerUI.class.getName(), "getUIDescriptor", ex);
		}
		return uiDesc;
	}
}
