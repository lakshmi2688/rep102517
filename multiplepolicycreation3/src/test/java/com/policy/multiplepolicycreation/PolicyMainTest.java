package com.policy.multiplepolicycreation;
import java.util.List;

import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.collections.Lists;




public class PolicyMainTest {

	
	public static void main(String[] args) {
		
		TestListenerAdapter tla = new TestListenerAdapter();
		TestNG testng = new TestNG();
		//testng.setTestClasses(new Class[] { PolicyTestNGTest.class });
		List<String> suites = Lists.newArrayList();
	
		suites.add("C:\\Users\\N0290327\\workspace1\\multiplepolicycreation\\src\\test\\java\\PolicyCreation.xml");
		testng.setTestSuites(suites);
		testng.run();

		

	}

}
