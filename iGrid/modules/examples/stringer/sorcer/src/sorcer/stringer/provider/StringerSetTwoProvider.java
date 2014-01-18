package sorcer.stringer.provider;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import com.sun.jini.start.LifeCycle;

import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.stringer.provider.StringerSetTwo;

@SuppressWarnings("rawtypes")
public class StringerSetTwoProvider extends ServiceTasker implements StringerSetTwo {

	private static Logger logger = Logger
			.getLogger(StringerSetTwo.class.getName());
	
	public StringerSetTwoProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Context getLength(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetTwo.LENGTH);
	}	

	@Override
	public Context getSpaceSplit(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetTwo.SPACESPLIT);
	}
	public Context getSmiley(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetTwo.SMILEY);
	}
	
	
	private Context doIt(Context context, String selector) throws RemoteException, ContextException {
		try {
			String strResult = "";
			logger.info("########");
			if (selector.equals(StringerSetTwo.LENGTH)) {
				strResult = (String) context.getValue(StringerSetTwo.LENGTH + CPS
						+ StringerSetTwo.STRING2);
				strResult = strResult.length()+"";
				
			} else if (selector.equals(StringerSetTwo.SPACESPLIT)) {
				strResult = (String) context.getValue(StringerSetTwo.SPACESPLIT + CPS
						+ StringerSetTwo.STRING2);
				String first = strResult.substring(0, strResult.length()/2);
				String second = strResult.substring(strResult.length()/2,strResult.length());
								
				strResult = first+" "+second;
			} else if (selector.equals(StringerSetTwo.SMILEY)) {
				strResult = (String) context.getValue(StringerSetTwo.SMILEY + CPS
						+ StringerSetTwo.STRING2);
								
				strResult = strResult + " :-)" ;
			} 
			// set return value
			if (context.getReturnPath() != null) {
				context.setReturnValue(strResult);
			}
			logger.info(selector + " result: \n" + strResult);
			
			// np. TOUPPER/STRING1/RESULT : wartosc zmiennej strResult
			// u calkow jest tylko SELECTOR
			context.putValue(selector + CPS +
					StringerSetTwo.STRING2 + CPS + StringerSetTwo.RESULT, strResult);
		
		} catch (Exception ex) {
			// ContextException, UnknownHostException
			ex.printStackTrace();
			context.reportException(ex);
			throw new ContextException(" stringersetTWO doit exception", ex);
		}
		return (Context) context;
	}
	
	
	private String getHostname() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}

}
