
/**
 * @author GROUP 22
 * 
 * Customer Class is a custom object of type Customer
 * containing Customer details.
 *
 */
public class Customer
{
	//Customer name of type String
	private String name;
	// CustomerID of type int
	private int customerID;
	
	/**
	 * default customer constructor
	 */
	public Customer()
	{
		
	}
	
	/**
	 * Customer constructor that takes in name and id
	 * for the default values
	 * @param toName string value to assign to Customer object
	 * @param toID int value to assign to Customer object
	 */
	public Customer(String toName, int toID)
	{
		this.setName(toName);
		this.setCustomerID(toID);
	}

	/**
	 * Customer constructor that will create a copy
	 * of the given customer object 
	 * @param oldCustomer Customer object to duplicate
	 */
	public Customer(Customer oldCustomer)
	{
		this.setCustomerID(oldCustomer.getID());
		this.setName(oldCustomer.getName());
	}

	/**
	 * Call this method to assign a new value to Customer name
	 * @param newName string value for the new Customer name
	 */
	public void setName(String newName)
	{
		this.name = newName;
	}
	
	/**
	 * Call this method to assign a new value to Customer ID
	 * @param newCustomerID int value for the new Customer ID
	 */
	public void setCustomerID(int newCustomerID)
	{
		this.customerID = newCustomerID;
	}
	
	/**
	 * Call this method to get the name of the customer
	 * @return String value of the customer name variable
	 */
	public String getName()
	{
		return this.name;
	}
	
	/**
	 * Call this method to get the Customer's ID
	 * @return int value of the customer ID variable
	 */
	public int getID()
	{
		return this.customerID;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * Call this method to obtain a pre-formatted string containing
	 * the values of this Customer object.
	 */
	@Override
	public String toString()
	{
		return this.getName() + " " + this.getID();
	}

}
