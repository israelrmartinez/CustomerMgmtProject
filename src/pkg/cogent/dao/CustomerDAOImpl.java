package pkg.cogent.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Stream;

import pkg.cogent.exception.MandatoryFieldExcpetion;
import pkg.cogent.model.Customer;

public class CustomerDAOImpl implements CustomerDAO {

	Customer customers[];
	Customer existingCustomers[];
	Customer youngestCustomer;
	Scanner sc=new Scanner(System.in);
	SimpleDateFormat sdformat = new SimpleDateFormat("yyyy-MM-dd");
	Date youngestDob;
	@Override
	public void create() {
		if (customers != null) {
			existingCustomers = customers;
			System.out.println("How many customers do you want to add?");
		} else {
			System.out.println("How many customer you want to store?");
		}
		
		int size=sc.nextInt();
		
		customers=new Customer[size];
		for(int i=0;i<customers.length;i++)
		{
			Customer cust=new Customer();
			System.out.println("Please enter customer ID:");
			String cid=sc.next();		
			System.out.println("Please enter customer name:");
			String cname=sc.next();
			System.out.println("Please enter customer email ");
			String eMail=sc.next();
			System.out.println("Please enter DOB (yyyy-MM-dd)");
			String cDob = sc.next();
			
			cust.setcId(cid);
			cust.setcName(cname);
			cust.setcEmail(eMail);
			cust.setcDob(cDob);
			validateMandatoryField(cust);
			customers[i]=cust;
		}
		
		if (existingCustomers != null) {
			customers = Stream.concat(Arrays.stream(customers), Arrays.stream(existingCustomers)).toArray(Customer[]::new);
		}
		existingCustomers = customers;
	}
	
	public void validateMandatoryField(Customer customer)
	{
		if(customer==null)
		{
			throw new MandatoryFieldExcpetion("Customer object can not be left blank");
		}
		else if(customer.getcId()==null)
		{
			throw new MandatoryFieldExcpetion("Customer ID can not be left blank");
		}
	}

	@Override
	public void read() {
		for(int i=0;i<customers.length;i++)
		{
			System.out.println("Customer ID:"+customers[i].getcId());
			System.out.println("Customer  name:"+customers[i].getcName());
			System.out.println("Customer e-mail:"+customers[i].getcEmail());
			System.out.println("Customer DOB:"+customers[i].getcDob());
			System.out.println();
		}
	}

	@Override
	public void update(String customerId) {
		// TODO Auto-generated method stub
		System.out.println();
		Customer cust = new Customer();
		int index = -1;
		
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getcId().equals(customerId)) {
				cust = customers[i];
				index = i;
				break;
			}
		}
		
		System.out.println("What do you want to update for the customer?");
		System.out.println("1-ID");
		System.out.println("2-Name");
		System.out.println("3-Email");
		System.out.println("4-Date of birth");
		System.out.println("Please enter your choice...");
		int choice=sc.nextInt();
		System.out.println();
		
		switch (choice) {
		case 1:
			System.out.println("Please enter the new ID");
			String cId = sc.next();
			cust.setcId(cId);
			break;
		case 2:
			System.out.println("Please enter the new name");
			String cName = sc.next();
			cust.setcName(cName);
			break;
		case 3:
			System.out.println("Please enter the new email");
			String cEmail = sc.next();
			cust.setcEmail(cEmail);
			break;
		case 4:
			System.out.println("Please enter new DOB (yyyy-MM-dd)");
			String cDob = sc.next();
			cust.setcDob(cDob);;
			break;
		}
		customers[index] = cust;
		System.out.println();
	}

	@Override
	public void delete(String customerId) {
		int removeIndex = -1;
		
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getcId().equals(customerId)) {
				removeIndex = i;
				break;
			}
		}
		
		customers=new Customer[existingCustomers.length - 1];
		for (int i = 0, k = 0; i < existingCustomers.length; i++) {
			if (i != removeIndex) {
				customers[k] = existingCustomers[i];
				k++;
			}
		}
		existingCustomers = customers;
		System.out.println();
	}
	@Override
	public void findCustomerById(String cID) {
		// TODO Auto-generated method stub
		System.out.println();
		Customer cust = new Customer();
		int index = -1;
		
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].getcId().equals(cID)) {
				cust = customers[i];
				index = i;
				break;
			}
		}
		
		System.out.println("Customer ID:"+cust.getcId());
		System.out.println("Customer  name:"+cust.getcName());
		System.out.println("Customer e-mail:"+cust.getcEmail());
		System.out.println("Customer DOB:"+cust.getcDob());
		System.out.println();
	}
	
	@Override
	public void findYongestCustomer() {
		// TODO Auto-generated method stub
		try {
			youngestDob = sdformat.parse("1900-01-01");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
		Customer cust = new Customer();
		int index = -1;
		for (int i = 0; i < customers.length; i++) {
			if (customers[i].cDobObj.after(youngestDob)) {
				cust = customers[i];
				youngestDob = customers[i].cDobObj;
			}
		}
		this.findCustomerById(cust.getcId());
	}

}
