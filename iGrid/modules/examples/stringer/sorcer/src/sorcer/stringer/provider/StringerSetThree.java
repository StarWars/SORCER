package sorcer.stringer.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

import sorcer.service.Context;
import sorcer.service.ContextException;

@SuppressWarnings("rawtypes")
public interface StringerSetThree extends Remote {
	public Context makeReverse(Context context) throws RemoteException, ContextException;
	public Context separateWithComma(Context context) throws RemoteException, ContextException;
	public Context makeHalf(Context context) throws RemoteException, ContextException;
	
	public final static String MAKEREVERSE = "makereverse";
	public final static String SEPARATEWITHCOMMA = "separatewithcomma";
	public final static String MAKEHALF = "makehalf";

	public final static String STRING3 = "string3";
	public final static String RESULT = "result";

}
