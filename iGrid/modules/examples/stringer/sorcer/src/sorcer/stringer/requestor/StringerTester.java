package sorcer.stringer.requestor;

import java.rmi.RMISecurityManager;
import java.util.logging.Logger;

import sorcer.stringer.provider.ServiceStringer;
import sorcer.core.context.ServiceContext;
import sorcer.core.exertion.NetJob;
import sorcer.core.exertion.NetTask;
import sorcer.core.signature.NetSignature;
import sorcer.service.Context;
import sorcer.service.Job;
import sorcer.util.Log;
import sorcer.util.Sorcer;

@SuppressWarnings("rawtypes")
public class StringerTester {

	private static Logger logger = Log.getTestLog();

	String CPS = "/";
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new RMISecurityManager());
		Job result = new StringerTester().test();
		logger.info("job context: \n" + result.getJobContext());
	}

	private Job test() throws Exception {
		Job result = (Job)getJob().exert();
		return result;
	}

	private Job getJob() throws Exception {
		NetTask task1 = getConcatenationTask();
		NetJob job = new NetJob("stringer");

		job.addExertion(task1);
		return job;
	}

	private NetTask getConcatenationTask() throws Exception {
		ServiceContext context = new ServiceContext(ServiceStringer.CONCATENATION);
		context.putValue(ServiceStringer.CONCATENATION, "alaMaKota");
		context.putValue(ServiceStringer.CONCATENATION, Context.none);
		NetSignature signature = new NetSignature("makeConcatenation", ServiceStringer.class, Sorcer.getActualName("Stringer1"));
		NetTask task = new NetTask("stringer-concatenation", signature);
		task.setContext(context);
		return task;
	}
	
	private NetTask getDepositTask() throws Exception {
		ServiceContext context = new ServiceContext(ServiceStringer.ACCOUNT);
		context.putValue(ServiceStringer.DEPOSIT + CPS + ServiceStringer.AMOUNT,
				new Money(10000)); // $100.00
		context.putValue(ServiceStringer.BALANCE + CPS + ServiceStringer.AMOUNT,
				Context.none);
		NetSignature signature = new NetSignature("makeDeposit",
				ServiceStringer.class, Sorcer.getActualName("Stringer1"));
		NetTask task = new NetTask("account-deposit", signature);
		task.setContext(context);
		return task;
	}

}
