package sorcer.stringer.provider;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.logging.Logger;

import net.jini.admin.Administrable;
import net.jini.core.transaction.Transaction;
import net.jini.core.transaction.TransactionException;
import sorcer.core.SorcerConstants;
import sorcer.core.provider.Provider;
import sorcer.core.proxy.Partnership;
import sorcer.core.proxy.RemotePartner;
import sorcer.service.Context;
import sorcer.service.Exertion;
import sorcer.service.ExertionException;
import sorcer.util.Log;

public class ServiceStringerImpl implements Stringer, ServiceStringer, SorcerConstants {

	private static Logger logger = Log.getTestLog();

	public Context makeConcatenation(Context context) throws RemoteException, StringerException {
		return process(context, ServiceStringer.CONCATENATION);
	}
	public ServiceStringerImpl(){
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
	
	public String makeConcatenation(String string1, String string2){
		return string1 + string2;
	}
	
	private Provider partner;

	private Administrable admin;

	/*
	 * (non-Javadoc)
	 * 
	 * @see sorcer.core.provider.proxy.Partnership#getPartner()
	 */
	public Remote getInner() throws RemoteException {
		return (Remote) partner;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see sorcer.base.Service#service(sorcer.base.Exertion)
	 */
	public Exertion service(Exertion exertion, Transaction transaction)
			throws RemoteException, ExertionException, TransactionException {
		System.out.println("exertion service - service string implementation\n\n\n");
		return partner.service(exertion, null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.jini.admin.Administrable#getAdmin()
	 */
	public Object getAdmin() throws RemoteException {
		return admin;
	}

	public void setInner(Object provider) {
		partner = (Provider) provider;
	}

	public void setAdmin(Object admin) {
		this.admin = (Administrable) admin;
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
