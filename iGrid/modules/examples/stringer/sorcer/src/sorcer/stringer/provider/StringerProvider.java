package sorcer.stringer.provider;

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
public class StringerProvider extends ServiceTasker implements Stringer,
		ServiceStringer, SorcerConstants {

	private static Logger logger = Log.getTestLog();

	private String mutableString = new String();

	/**
	    <p> Add purpose of method here </p>
	   
	    @param none
	    @return the mutableString
	 */
	public String getMutableString() {
		return mutableString;
	}
	public void setMutableString(String string) {
		mutableString = string;
	}

	/**
	 * Constructs an instance of the SORCER stringer provider implementing
	 * SorcerStringer and Stringer. This constructor is required by Jini 2 life
	 * cycle management.
	 * 
	 * @param args
	 * @param lifeCycle
	 * @throws Exception
	 */
	public StringerProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
//		String cents = getProperty("provider.balance");
//		mutableString = getProperty("provider.mutableString");
//		balance = new Money(Integer.parseInt(cents));
	}
	
	public Context makeConcatenation(Context context) throws RemoteException, StringerException {
		return process(context, ServiceStringer.CONCATENATION);
	}


	private Context process(Context context, String selector)
			throws RemoteException, StringerException {
		try {
			logger.info("input context: \n" + context);

			String strResult = "", string1 = "", string2 = "";
			
			if (selector.equals(ServiceStringer.CONCATENATION)){
				string1 = (String) context.getValue(ServiceStringer.CONCATENATION + CPS + ServiceStringer.STRING1);
				string2 = (String) context.getValue(ServiceStringer.CONCATENATION + CPS + ServiceStringer.STRING2);
				strResult = makeConcatenation(string1, string2);
			}

			logger.info(selector + " result: \n" + strResult);
			String outputMessage = "processed by " + getHostname();			
			context.putValue(ServiceStringer.CONCATENATION, strResult);

		} catch (Exception ex) {
			throw new StringerException(ex);
		}
		return context;
	}

	public String makeConcatenation(String string1, String string2) throws RemoteException {
		setMutableString(getMutableString() + string1 + string2);
		return getMutableString();
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
