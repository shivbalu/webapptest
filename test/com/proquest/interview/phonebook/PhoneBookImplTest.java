package com.proquest.interview.phonebook;

import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.junit.Test;

public class PhoneBookImplTest extends TestCase{
	//@Test
	public void shouldAddFindPerson() {
		testWhenSearchUserIsNull();
		testWhenSearchUserNameMatches();
		testWhenSearchUserNameNotMatches();
	}
	//@Test
	public void testWhenSearchUserIsNull() {
		PhoneBookImpl impl=new PhoneBookImpl();
		assertNull(impl.findPerson(null, null));
	}
	//@Test
	public void testWhenSearchUserNameMatches() {
		PhoneBookImpl impl=new PhoneBookImpl();
		Person newPerson=new Person();
		String firstName="Cynthia";
		String lastName="Smith";
		newPerson=impl.findPerson(firstName, lastName);
		assertEquals(newPerson.getPhoneNumber(), "(824) 128-8758");
	}
	//@Test
	public void testWhenSearchUserNameNotMatches() {
		PhoneBookImpl impl=new PhoneBookImpl();
		String firstName="Cynthia";
		String lastName="Test";
		Person newPerson=impl.findPerson(firstName, lastName);
		assertNull(newPerson.getPhoneNumber());
	}
	
	PhoneBookImplTest(String name){
		super(name);
	}
	
	public static Test suite(){
		TestSuite suite = new TestSuite();
		suite.addTest(new PhoneBookImplTest("testWhenSearchUserIsNull"));
		suite.addTest(new PhoneBookImplTest("testWhenSearchUserNameMatches"));
		suite.addTest(new PhoneBookImplTest("testWhenSearchUserNameNotMatches"));
		return (Test) suite;
	}
	
}

