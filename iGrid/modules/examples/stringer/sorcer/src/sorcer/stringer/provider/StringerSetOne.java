package sorcer.stringer.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
import sorcer.service.ContextException;

@SuppressWarnings("rawtypes")
public interface StringerSetOne extends Remote {
	public Context toUpperCase(Context context) throws RemoteException, ContextException;
	public Context toLowerCase(Context context) throws RemoteException, ContextException;
	public Context firstToUpper(Context context) throws RemoteException, ContextException;
	public Context lastToUpper(Context context) throws RemoteException, ContextException;
	
	public final static String TOUPPER = "toupper";
	public final static String TOLOWER = "tolower";
	public final static String FIRSTTOUPPER = "firsttoupper";
	public final static String LASTTOUPPER = "lasttoupper";
	public final static String STRING1 = "string1";
	public final static String RESULT = "result";

}
