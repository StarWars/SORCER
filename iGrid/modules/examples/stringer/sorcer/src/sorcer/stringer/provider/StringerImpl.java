package sorcer.stringer.provider;

import java.rmi.RemoteException;

public class StringerImpl implements Stringer {
	
	private String mutableString = new String();
	
	/*Constructor*/
	public StringerImpl(String startingString) throws RemoteException {
		setMutableString(startingString);
	}

	public String makeConcatenation(String string1, String string2) throws RemoteException {
		setMutableString(getMutableString() + string1 + string2);
		return getMutableString();
	}


	/**
	    <p> getter of mutableString </p>
	   
	    @param none
	    @return the mutableString
	 */
	public String getMutableString() {
		return mutableString;
	}

	/**   
	    <p> setter of mutableString </p>
	
	    @param mutableString the mutableString to set
	    @return nothing  
	 */
	public void setMutableString(String mutableString) {
		this.mutableString = mutableString;
	}
}
