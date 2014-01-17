package sorcer.account.provider;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import sorcer.core.SorcerConstants;
import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.util.Log;

import com.sun.jini.start.LifeCycle;

@SuppressWarnings("rawtypes")
public class StringsProvider extends ServiceTasker implements Strings,
		ServiceStrings, SorcerConstants {

	private static Logger logger = Log.getTestLog();

	private Money balance;

	/**
	 * Constructs an instance of the SORCER account provider implementing
	 * SorcerAccount and Account. This constructor is required by Jini 2 life
	 * cycle management.
	 * 
	 * @param args
	 * @param lifeCycle
	 * @throws Exception
	 */
	public StringsProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		String cents = getProperty("provider.balance");
		balance = new Money(Integer.parseInt(cents));
	}

	public Context getBalance(Context context) throws RemoteException,
			StringsException {
		return process(context, ServiceStrings.BALANCE);
	}

	public Context makeDeposit(Context context) throws RemoteException,
			StringsException {
		return process(context, ServiceStrings.DEPOSIT);
	}

	public Context makeWithdrawal(Context context) throws RemoteException,
			StringsException {
		return process(context, ServiceStrings.WITHDRAWAL);
	}

	private Context process(Context context, String selector)
			throws RemoteException, StringsException {
		try {
			logger.info("input context: \n" + context);

			Money result = null, amount = null;
			if (selector.equals(ServiceStrings.BALANCE)) {
				result = getBalance();
			} else if (selector.equals(ServiceStrings.DEPOSIT)) {
				amount = (Money) context.getValue(ServiceStrings.DEPOSIT + CPS
						+ ServiceStrings.AMOUNT);
				makeDeposit(amount);
				result = getBalance();
			} else if (selector.equals(ServiceStrings.WITHDRAWAL)) {
				amount = (Money) context.getValue(ServiceStrings.WITHDRAWAL
						+ CPS + ServiceStrings.AMOUNT);
				makeWithdrawal(amount);
				result = getBalance();
			}
			// set return value
			if (context.getReturnPath() != null) {
				context.setReturnValue(result);
			}
			logger.info(selector + " result: \n" + result);
			String outputMessage = "processed by " + getHostname();
			context.putValue(selector + CPS +
					ServiceStrings.BALANCE + CPS + ServiceStrings.AMOUNT, result);
			context.putValue(ServiceStrings.COMMENT, outputMessage);

		} catch (Exception ex) {
			throw new StringsException(ex);
		}
		return context;
	}

	public Money getBalance() throws RemoteException {
		return balance;
	}

	public void makeDeposit(Money amount) throws RemoteException,
			NegativeAmountException {
		checkForNegativeAmount(amount);
		balance.add(amount);
		return;
	}

	public void makeWithdrawal(Money amount) throws RemoteException,
			OverdraftException, NegativeAmountException {
		checkForNegativeAmount(amount);
		checkForOverdraft(amount);
		balance.subtract(amount);
		return;
	}

	private void checkForNegativeAmount(Money amount)
			throws NegativeAmountException {
		int cents = amount.getCents();

		if (0 > cents) {
			throw new NegativeAmountException();
		}
	}

	private void checkForOverdraft(Money amount) throws OverdraftException {
		if (amount.greaterThan(balance)) {
			throw new OverdraftException(false);
		}
		return;
	}

	/**
	 * Returns name of the local host.
	 * 
	 * @return local host name
	 * @throws UnknownHostException
	 */
	private String getHostname() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}
}
