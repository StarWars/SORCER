package sorcer.account.provider;

import java.rmi.RemoteException;

public class AccountImpl implements Account {
	private Money balance;
	private String mutableString;

	public AccountImpl(Money startingBalance) throws RemoteException {
		balance = startingBalance;
	}
	
	/*NEW STRING*/
	public AccountImpl(String startingString) throws RemoteException {
		setMutableString(startingString);
	}

	@Override
	public void makeConcatenation(String string) throws RemoteException {
		setMutableString(getMutableString() + string);
	}
	/***********/
	
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
	    <p> Add purpose of method here </p>
	   
	    @param none
	    @return the mutableString
	 */
	public String getMutableString() {
		return mutableString;
	}

	/**   
	    <p> Add purpose of method here </p>
	
	    @param mutableString the mutableString to set
	    @return nothing  
	 */
	public void setMutableString(String mutableString) {
		this.mutableString = mutableString;
	}
}
