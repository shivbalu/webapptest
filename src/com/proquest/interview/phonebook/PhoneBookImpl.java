package com.proquest.interview.phonebook;

import java.util.ArrayList;
import java.util.List;

import com.proquest.interview.util.DatabaseUtil;

/**
 * This class is uses to insert and retrieve the person details from phone book
 * @author Bala Amirthalingam
 * @version 1.0
 */
public class PhoneBookImpl implements PhoneBook {
	public static List<Person> people;
	
	public List<Person> getPeople() {
		return people;
	}

	public void setPeople(List<Person> people) {
		this.people = people;
	}
	/*
	 *Desc: Below method uses to add the person details into DB
	 *Param: Person object
	 *Return type: void 
	 * */
	@Override
	public void addPerson(Person newPerson) {
		System.out.println("Inserting into DB.....");
		int cnt=0;
		if(newPerson!=null ){
			cnt=DatabaseUtil.insertInfoDB(newPerson);
		}
		if(cnt==0){
			System.out.println("Person details are not inserted. Please check the vlaues");
		}else{
			System.out.println("Person is inserted");	
		}
		
	}
	
	
	@Override
	/*
	 *Desc: Below method uses to retrieve the person details into DB
	 *Param: String firstName and String lastName
	 *Return type: Person object 
	 * */
	public Person findPerson(String firstName, String lastName) {
		System.out.println("Searching..........");		
		Person personToRetrieve=new Person();
		Person searchResult=new Person();
		if(firstName != null && lastName !=null){
			personToRetrieve.setName(firstName+" "+lastName);
			searchResult=DatabaseUtil.retrieveInfoFromDB(personToRetrieve);
		}else{
			return null;
		}
		return searchResult;
	}
	
	public static void main(String[] args) {
		//DatabaseUtil.initDB();  //You should not remove this line, it creates the in-memory database

		/* TODO: create person objects and put them in the PhoneBook and database
		 * John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
		 * Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI
		*/ 
		// TODO: print the phone book out to System.out
		// TODO: find Cynthia Smith and print out just her entry
		// TODO: insert the new person objects into the database
		
		
		//Set the value in Person object
		PhoneBookImpl mainObject=new PhoneBookImpl();
		//1st person
		people=new ArrayList<Person>();
		Person newPerson=new Person();
		newPerson.setAddress("1234 Sand Hill Dr, Royal Oak, MI");
		newPerson.setName("John Smith");
		newPerson.setPhoneNumber("(248) 123-4567");
		mainObject.addPerson(newPerson);
		//2nd person
		newPerson=new Person();
		newPerson.setAddress("875 Main St, Ann Arbor, MI");
		newPerson.setName("Cynthia Smith");
		newPerson.setPhoneNumber("(824) 128-8758");
		mainObject.addPerson(newPerson);
		
		//System.out.println("Displaying..........");		
		//System.out.println(mainObject.toString());
		
		String firstName="Cynthia";
		String lastName="Smith";
		System.out.println("Find below search result for user name"+ firstName+" "+lastName);
		Person searchResult=(Person)mainObject.findPerson(firstName,lastName);
		if(searchResult!=null){
			System.out.println(searchResult.getName());
			System.out.println(searchResult.getAddress());
			System.out.println(searchResult.getPhoneNumber());
		}else{
			System.out.println("User record not found");
		}
		
		
	}
}
