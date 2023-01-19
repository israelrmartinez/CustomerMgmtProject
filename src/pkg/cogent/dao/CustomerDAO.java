package pkg.cogent.dao;

import java.text.ParseException;

public interface CustomerDAO {
	public void create() throws ParseException;

	public void read();

	public void update(String customerId) throws Throwable;

	public void delete(String customerId);
	
	public void findCustomerById(String cID);
	public void findYongestCustomer(); 

}
