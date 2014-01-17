package sorcer.stringer.provider;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Stringer extends Remote {

	public void makeConcatenation(String string1, String string2) throws RemoteException;

}
