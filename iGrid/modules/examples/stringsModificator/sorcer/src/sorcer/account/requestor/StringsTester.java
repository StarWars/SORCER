package sorcer.account.requestor;

import java.rmi.RMISecurityManager;
import java.util.logging.Logger;

import sorcer.account.provider.Money;
import sorcer.account.provider.ServiceStrings;
import sorcer.core.context.ServiceContext;
import sorcer.core.exertion.NetJob;
import sorcer.core.exertion.NetTask;
import sorcer.core.signature.NetSignature;
import sorcer.service.Context;
import sorcer.service.Job;
import sorcer.util.Log;
import sorcer.util.Sorcer;

@SuppressWarnings("rawtypes")
public class StringsTester {

	private static Logger logger = Log.getTestLog();

	String CPS = "/";
	
	public static void main(String[] args) throws Exception {
		System.setSecurityManager(new RMISecurityManager());
		Job result = new StringsTester().test();
		logger.info("job context: \n" + result.getJobContext());
	}

	private Job test() throws Exception {
		Job result = (Job)getJob().exert();
		return result;
	}

	private Job getJob() throws Exception {
		NetTask task1 = getDepositTask();
		NetTask task2 = getWithdrawalTask();
		NetJob job = new NetJob("account");
		job.addExertion(task1);
		job.addExertion(task2);
		return job;
	}

	private NetTask getDepositTask() throws Exception {
		ServiceContext context = new ServiceContext(ServiceStrings.ACCOUNT);
		context.putValue(ServiceStrings.DEPOSIT + CPS + ServiceStrings.AMOUNT,
				new Money(10000)); // $100.00
		context.putValue(ServiceStrings.BALANCE + CPS + ServiceStrings.AMOUNT,
				Context.none);
		NetSignature signature = new NetSignature("makeDeposit",
				ServiceStrings.class, Sorcer.getActualName("Strings1"));
		NetTask task = new NetTask("account-deposit", signature);
		task.setContext(context);
		return task;
	}

	private NetTask getWithdrawalTask() throws Exception {
		ServiceContext context = new ServiceContext(ServiceStrings.ACCOUNT);
		context.putValue(ServiceStrings.WITHDRAWAL + CPS + ServiceStrings.AMOUNT,
				new Money(10000)); // $100.00
		context.putValue(ServiceStrings.BALANCE + CPS + ServiceStrings.AMOUNT,
				Context.none);
		NetSignature signature = new NetSignature("makeWithdrawal",
				ServiceStrings.class, Sorcer.getActualName("Strings2"));
		NetTask task = new NetTask("account-withdrawal", signature);
		task.setContext(context);
		return task;
	}
}
