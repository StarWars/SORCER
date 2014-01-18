package sorcer.stringer.junit;

import static sorcer.eo.operator.context;
import static sorcer.eo.operator.exert;
import static sorcer.eo.operator.get;
import static sorcer.eo.operator.in;

import static sorcer.eo.operator.sig;
import static sorcer.eo.operator.task;
import static sorcer.eo.operator.srv;
import static sorcer.eo.operator.value;

import java.rmi.RMISecurityManager;
import java.util.logging.Logger;

import org.junit.Test;

import sorcer.stringer.provider.StringerSetOneProvider;
import sorcer.core.SorcerConstants;
import sorcer.service.ServiceExertion;
import sorcer.service.Task;
import sorcer.util.Sorcer;


@SuppressWarnings("unchecked")
public class StringerTestPro implements SorcerConstants {

	private final static Logger logger = Logger
			.getLogger(StringerTestPro.class.getName());
	
	static {
		ServiceExertion.debug = true;
		System.setProperty("java.security.policy", Sorcer.getHome()
				+ "/configs/policy.all");
		System.setSecurityManager(new RMISecurityManager());
		Sorcer.setCodeBase(new String[] { "StringerSetOneProvider.jar"});
		System.out.println("CLASSPATH :" + System.getProperty("java.class.path"));
		System.setProperty("java.protocol.handler.pkgs", "sorcer.util.url|org.rioproject.url");
	}
	
	
	
	@Test
	public void addTest() throws Exception {
		
		logger.info("Test stringer set one'a");
		
		Task t1 = task(
				"t1",
				sig("toUpperCase", StringerSetOneProvider.class, "StringerSetOneProvider"),
				context("toupper", in("toupper/string1", "kuku")));
		logger.info("Task1: "+t1.getContext());
		t1 = exert(t1);
		logger.info("t1 context: "+context(t1));
		logger.info("t1 value: "+get(t1, "toupper/string1/result"));
		/*
		Task t2 = task(
				"ToLower",
				sig("toLowerCase", StringerSetOneProvider.class, "StringerSetOneProvider"),
				context("toLowerCase"));
		logger.info("Task2: "+t2.getContext());
		
		Exertion job = new ObjectJob("Jobs");
		job.addExertion(t1);
		job.addExertion(t2);
		
		t1.getContext().connect("val1", "a", t2.getContext());
		t1.getContext().connect("val2", "b", t2.getContext());
		t1.getContext().connect("val3", "c", t2.getContext());
		t1.getContext().connect("val4", "d", t2.getContext());
		t1.getContext().connect("val5", "e", t2.getContext());

		t2.getContext().connect("avg", "a", t3.getContext());
		
		job = job.exert();
		logger.info("JOB :"+((Job)job).getJobContext());
		*/
	
		
	}
	
}