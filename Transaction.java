package ATM;
import java.time.DayOfWeek;
import java.time.LocalTime;

public class Transaction {
	private Person person;
	private Account account1, account2;
	private String type;
	private int amount;
	//accessors
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	
	public Account getAccount1() {
		return account1;
	}
	public void setAccount1(Account account1) {
		this.account1 = account1;
	}
	public Account getAccount2() {
		return account2;
	}
	public void setAccount2(Account account2) {
		this.account2 = account2;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	//Constructor
	public Transaction(Person person,  Account acc, String type, int amount) {
		this.person = person;
		account1 =acc;
		this.type = type;
		this.amount = amount;
	}
	//Overloaded constructor
	public Transaction(Person person,  Account acc1,Account acc2, String type, int amount) {
		this.person = person;
		account1 =acc1;
		account2 = acc2;
		this.type = type;
		this.amount = amount;
	}
	
}
