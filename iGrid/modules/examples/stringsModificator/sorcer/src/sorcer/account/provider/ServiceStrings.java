package sorcer.account.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;

@SuppressWarnings("rawtypes")
public interface ServiceStrings extends Remote {

	public Context getBalance(Context account) throws RemoteException,
			StringsException;

	public Context makeDeposit(Context account) throws RemoteException,
			StringsException;

	public Context makeWithdrawal(Context account) throws RemoteException,
			StringsException;

	public final static String ACCOUNT = "accout";

	public final static String DEPOSIT = "deposit";

	public final static String WITHDRAWAL = "withdrawal";

	public final static String AMOUNT = "amount";

	public final static String BALANCE = "balance";

	public final static String COMMENT = "comment";
}
