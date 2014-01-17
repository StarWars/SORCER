package sorcer.stringer.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;

@SuppressWarnings("rawtypes")
public interface ServiceStringer extends Remote {
	
	public Context makeConcatenation(Context context) throws RemoteException, StringerException;

	public final static String STRING1 = "string1";

	public final static String STRING2 = "string2";
	
	public final static String CONCATENATION = "concatenation";
	
}
