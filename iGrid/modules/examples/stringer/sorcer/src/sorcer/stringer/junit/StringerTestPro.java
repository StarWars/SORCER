package sorcer.stringer.junit;

import static sorcer.eo.operator.context;
import static sorcer.eo.operator.in;
import static sorcer.eo.operator.sig;
import static sorcer.eo.operator.task;
import static sorcer.eo.operator.exert;
import static sorcer.eo.operator.job;
import static sorcer.eo.operator.get;
import static sorcer.eo.operator.value;

import java.rmi.RMISecurityManager;
import java.util.logging.Logger;

import org.junit.Test;

import sorcer.core.SorcerConstants;
import sorcer.core.exertion.ObjectJob;
import sorcer.service.Exertion;
import sorcer.service.Job;
import sorcer.service.ServiceExertion;
import sorcer.service.Task;
import sorcer.stringer.provider.StringerSetOne;
import sorcer.stringer.provider.StringerSetThree;
import sorcer.stringer.provider.StringerSetTwo;
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
		Sorcer.setCodeBase(new String[] { "StringerSetOneProvider.jar", "StringerSetTwoProvider.jar", "StringerSetThreeProvider.jar"});
		System.out.println("CLASSPATH :" + System.getProperty("java.class.path"));
		System.setProperty("java.protocol.handler.pkgs", "sorcer.util.url|org.rioproject.url");
	}
	
	@Test
	public void toLowerTest() throws Exception{
		Task t1 = task("to lower", 
				sig("toLowerCase", StringerSetOne.class, "StringerSetOneProvider"), 
				context("tolower", 
						in("tolower/string1", "QwEsDaQ")));
		
		logger.info("to lower "+t1.getContext());
		t1 = exert(t1);
		logger.info("to lower context: "+context(t1));
		logger.info("to lower value: "+get(t1, "tolower/string1/result"));
	}
	
	@Test
	public void firstToUpperTest() throws Exception{
		Task t1 = task("first to upper", 
				sig("firstToUpper", StringerSetOne.class, "StringerSetOneProvider"), 
				context("firsttoupper", 
						in("firsttoupper/string1", "qWERTS")));
		
		logger.info("first to upper "+t1.getContext());
		t1 = exert(t1);
		logger.info("first to upper context: "+context(t1));
		logger.info("first to upper value: "+get(t1, "firsttoupper/string1/result"));
	}
	
	@Test
	public void Test() throws Exception {
		
		logger.info("Test stringer set one'a");
		
		Task t1 = task("to upper", 
				sig("toUpperCase", StringerSetOne.class, "StringerSetOneProvider"), 
				context("toupper", 
						in("toupper/string1", "kuku2")));
		
		//logger.info("Task1: "+t1.getContext());
		
		Task t2 = task(
				"space split",
				sig("getSpaceSplit", StringerSetTwo.class, "StringerSetTwoProvider"),
				context("spacesplit", in("spacesplit/string2")));
		//logger.info("Task2: "+t2.getContext());
		
		Task t3 = task("make reverse",
				sig("makeReverse", StringerSetThree.class, "StringerSetThreeProvider"),
				context("makereverse", in("makereverse/string3")));
		//logger.info("Task3: "+t3.getContext());
		
		Task t4 = task("to lower", 
				sig("toLowerCase", StringerSetOne.class, "StringerSetOneProvider"), 
				context("tolower", 
						in("tolower/string1")));
		Task t5 = task("have a great day",
					sig("addSmiley", StringerSetTwo.class, "StringerSetTwoProvider"),
					context("smiley",
							in("smiley/string2")));
		
		//logger.info("Task4: "+t4.getContext());
		t1 = exert(t1);
		t1.getContext().connect("toupper/string1/result", "spacesplit/string2", t2.getContext());
		//t2.getContext().connect("spacesplit/string2/result", "makereverse/string3", t3.getContext());
		t2.getContext().connect("spacesplit/string2/result", "tolower/string1", t4.getContext());
		t4.getContext().connect("tolower/string1/result", "smiley/string2", t5.getContext());
		
		Exertion job = new ObjectJob("Jobs");
		job.addExertion(t1);
		job.addExertion(t2);
		job.addExertion(t4);
		job.addExertion(t5);
		//job.addExertion(t3);

		job = job.exert();
		logger.info("JOB :"+((Job)job).getJobContext());
		
	
		
	}
	
}