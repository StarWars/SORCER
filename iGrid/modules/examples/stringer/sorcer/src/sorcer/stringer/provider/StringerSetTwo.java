package sorcer.stringer.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
import sorcer.service.ContextException;

@SuppressWarnings("rawtypes")
public interface StringerSetTwo extends Remote {
	public Context getLength(Context context) throws RemoteException, ContextException;
	public Context getSpaceSplit(Context context) throws RemoteException, ContextException;
	public Context addSmiley(Context context) throws RemoteException, ContextException;
	
	
	public final static String LENGTH = "length";
	public final static String SPACESPLIT = "spacesplit";
	public final static String SMILEY = "smiley";
	public final static String STRING2 = "string2";
	public final static String RESULT = "result";

}
