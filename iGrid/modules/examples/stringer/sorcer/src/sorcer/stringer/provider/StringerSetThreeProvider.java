package sorcer.stringer.provider;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import com.sun.jini.start.LifeCycle;

import sorcer.core.provider.ServiceTasker;
import sorcer.service.Context;
import sorcer.service.ContextException;
import sorcer.stringer.provider.StringerSetThree;

@SuppressWarnings("rawtypes")
public class StringerSetThreeProvider extends ServiceTasker implements StringerSetThree {

	private static Logger logger = Logger
			.getLogger(StringerSetThree.class.getName());
	
	public StringerSetThreeProvider(String[] args, LifeCycle lifeCycle) throws Exception {
		super(args, lifeCycle);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Context makeReverse(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetThree.MAKEREVERSE);
	}	

	@Override
	public Context separateWithComma(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetThree.SEPARATEWITHCOMMA);
	}

	@Override
	public Context makeHalf(Context context) throws RemoteException,
			ContextException {
		return doIt(context, StringerSetThree.MAKEHALF);
	}

	
	private Context doIt(Context context, String selector) throws RemoteException, ContextException {
		try {
			String strResult = "";
			logger.info("########");
			if (selector.equals(StringerSetThree.MAKEREVERSE)) {
				strResult = (String) context.getValue(StringerSetThree.MAKEREVERSE + CPS
						+ StringerSetThree.STRING3);
				logger.info("Przed operacji upper: "+strResult);
				
				StringBuilder builder = new StringBuilder();
				for(int i=strResult.length()-1;i>=0;i--){
					builder.append(strResult[i]);
				}
				
				strResult = builder.toString();
				logger.info("Po operacja upper: "+strResult);
				
			} else if (selector.equals(StringerSetThree.SEPARATEWITHCOMMA)) {
				strResult = (String) context.getValue(StringerSetThree.SEPARATEWITHCOMMA + CPS
						+ StringerSetThree.STRING3);
				
				StringBuilder builder = new StringBuilder();
				for(int i=0;i<strResult.length();i++){
					builder.append(strResult[i]+",");
				}
				
				strResult = builder.toString();
			} else if (selector.equals(StringerSetThree.MAKEHALF)) {
				strResult = (String) context.getValue(StringerSetThree.MAKEHALF + CPS
						+ StringerSetThree.STRING3);
				
				String strResult = strResult.substring(0,strResult.length());
				
			} 
			// set return value
			if (context.getReturnPath() != null) {
				context.setReturnValue(strResult);
			}
			logger.info(selector + " result: \n" + strResult);
			
			// np. TOUPPER/STRING1/RESULT : wartosc zmiennej strResult
			// u calkow jest tylko SELECTOR
			context.putValue(selector + CPS +
					StringerSetThree.STRING3 + CPS + StringerSetThree.RESULT, strResult);
		
		} catch (Exception ex) {
			// ContextException, UnknownHostException
			ex.printStackTrace();
			context.reportException(ex);
			throw new ContextException(" stringerSetThree doit exception", ex);
		}
		return (Context) context;
	}
	
	
	private String getHostname() throws UnknownHostException {
		return InetAddress.getLocalHost().getHostName();
	}

}
