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

	public StringerDispatcher(StringerModel model, StringerView view,
			Stringer stringer) {
		this.model = model;
		this.view = view;
		this.stringer = stringer;
	}

	public void getBalance() {
		try {
			model.setBalance(stringer.getBalance());
		} catch (RemoteException e) {
			logger.info("Error occurred while getting stringer balance");
			logger.throwing(getClass().getName(), "getBalance", e);
		}
	}

	private void makeWithdrawl() {
		try {
			model.setWithdrawalAmount(view.getWithdrawalAmount());
			stringer.makeWithdrawal(model.getWithdrawalAmount());
			getBalance();
		} catch (Exception exception) {
			System.out.println("Couldn't talk to stringer. Error was \n "
					+ exception);
			exception.printStackTrace();
		}
	}

	private void makeDeposit() {
		try {
			model.setDepositAmount(view.getDepositAmount());
			stringer.makeDeposit(model.getDepositAmount());
			getBalance();
		} catch (Exception exception) {
			System.out.println("Couldn't talk to stringer. Error was \n "
					+ exception);
			exception.printStackTrace();
		}
	}

	public void actionPerformed(ActionEvent event) {
		String action = event.getActionCommand();
		logger.info("actionPerformed>>action: " + action);
//		if (action == StringerModel.DEPOSIT)
//			makeDeposit();
//		else if (action == StringerModel.WITHDRAW)
//			makeWithdrawl();
	}
}
