package sorcer.stringer.provider.ui.mvc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import sorcer.stringer.provider.Stringer;

public class StringerDispatcher implements ActionListener {

	private final static Logger logger = Logger
			.getLogger("sorcer.provider.stringer.ui.mvc");

	private StringerModel model;

	private StringerView view;

	private Stringer stringer;

	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		logger.info("actionPerformed>>action: " + action);

	}
}
