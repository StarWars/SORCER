package sorcer.stringer.provider;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import com.sun.jini.start.LifeCycle;

import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.stringer.provider.StringerSetOne;

@SuppressWarnings("rawtypes")
public class StringerSetOneProvider extends ServiceTasker implements StringerSetOne {

	private static Logger logger = Logger
			.getLogger(StringerSetOne.class.getName());
	
	public StringerSetOneProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Context toUpperCase(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetOne.TOUPPER);
	}	

	@Override
	public Context toLowerCase(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetOne.TOLOWER);
	}

	@Override
	public Context firstToUpper(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetOne.FIRSTTOUPPER);
	}

	@Override
	public Context lastToUpper(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetOne.LASTTOUPPER);
	}
	
	private Context doIt(Context context, String selector) throws RemoteException, ContextException {
		try {
			String strResult = "";
			logger.info("########");
			if (selector.equals(StringerSetOne.TOUPPER)) {
				strResult = (String) context.getValue(StringerSetOne.TOUPPER + CPS
						+ StringerSetOne.STRING1);
				strResult = strResult.toUpperCase();
				
			} else if (selector.equals(StringerSetOne.TOLOWER)) {
				strResult = (String) context.getValue(StringerSetOne.TOLOWER + CPS
						+ StringerSetOne.STRING1);
				
				strResult = strResult.toLowerCase();
			} else if (selector.equals(StringerSetOne.FIRSTTOUPPER)) {
				strResult = (String) context.getValue(StringerSetOne.FIRSTTOUPPER + CPS
						+ StringerSetOne.STRING1);
				
				String firstSign = strResult.substring(0, 1);
				firstSign = firstSign.toUpperCase();
				strResult = firstSign + strResult.substring(1,strResult.length()-1);
			} else if (selector.equals(StringerSetOne.LASTTOUPPER)) {
				strResult = (String) context.getValue(StringerSetOne.LASTTOUPPER + CPS
						+ StringerSetOne.STRING1);
				
				String lastSign = strResult.substring(strResult.length()-2, strResult.length()-1);
				lastSign = lastSign.toUpperCase();
				strResult = strResult.substring(0,strResult.length()-2) + lastSign;
			} 
			// set return value
			if (context.getReturnPath() != null) {
				context.setReturnValue(strResult);
			}
			logger.info(selector + " result: \n" + strResult);
			
			// np. TOUPPER/STRING1/RESULT : wartosc zmiennej strResult
			// u calkow jest tylko SELECTOR
			context.putValue(selector + CPS +
					StringerSetOne.STRING1 + CPS + StringerSetOne.RESULT, strResult);
		
		} catch (Exception ex) {
			// ContextException, UnknownHostException
			ex.printStackTrace();
			context.reportException(ex);
			throw new ContextException(" stringersetone doit exception", ex);
		}
		return (Context) context;
	}
	
	
	private String getHostname() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}

}
