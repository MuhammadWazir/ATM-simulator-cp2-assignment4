package ATM;
import java.util.Vector;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.time.LocalDateTime;
import java.time.DayOfWeek;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;
public class ATM {
	private Vector users;
	private FileInputStream user_info;
	private Queue transactions;
	
	/**
	 * Displays the main menu and handles user interactions.
	 */
	public void menu() {
		Person current;
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter your user ID: ");
		int id = scan.nextInt();
		
		System.out.println("Enter your password: ");
		String password = scan.nextLine();
		Person person;
		
		// Find the person with the given ID and password
		for (Object p : users) {
			if (p instanceof Person) {
				person = (Person) p;
				if (person.getPassword().equals(password) && person.getClient_nb() == id);
				current = (Person) p;
				int choice, hour;
				LocalDateTime currentTime;
				
				do {
					currentTime = LocalDateTime.now();
					DayOfWeek dayOfWeek = currentTime.getDayOfWeek();
					hour = currentTime.getHour();
					
					boolean isBefore6pm = hour < 18;
					boolean isNotWeekend = !dayOfWeek.equals(DayOfWeek.SATURDAY) && !dayOfWeek.equals(DayOfWeek.SUNDAY);
					
					System.out.println("1. Withdraw money");
					System.out.println("2. Deposit money");
					System.out.println("3. Transfer of Money");
					System.out.println("4. Display account info");
					System.out.println("5. Exit");
					System.out.println("Enter your choice: ");
					choice = scan.nextInt();
					
					switch (choice) {
						case 1:
							// Withdraw money
							if (isBefore6pm && isNotWeekend) {
								person.withdrawMoney();
							} else {
								System.out.println("Enter your account number: ");
								int account_number = scan.nextInt();
								Account a;
								
								// Find the account with the given account number
								for (Node n = person.getAccounts().getHeader(); n.getNext() != null; n = n.getNext()) {
									if (n.getInfo().getAcc_number() == account_number) {
										a = n.getInfo();
										
										System.out.println("Enter the withdrawal amount: ");
										int withdrawal_amount = scan.nextInt();
										
										// Create a transaction for the withdrawal and enqueue it
										Transaction t = new Transaction(person, a, "credited", withdrawal_amount);
										transactions.enqueue(t);
										break;
									}
								}
								System.out.println("Account not found.");
							}
							break;
						
						case 2:
							// Deposit money
							if (isBefore6pm && isNotWeekend) {
								person.depositMoney();
							} else {
								System.out.println("Enter your account number: ");
								int account_number = scan.nextInt();
								Account a;
								
								// Find the account with the given account number
								for (Node n = person.getAccounts().getHeader(); n.getNext() != null; n = n.getNext()) {
									if (n.getInfo().getAcc_number() == account_number) {
										a = n.getInfo();
										
										System.out.println("Enter the deposit amount: ");
										int withdrawal_amount = scan.nextInt();
										
										// Create a transaction for the deposit and enqueue it
										Transaction t = new Transaction(person, a, "debited", withdrawal_amount);
										transactions.enqueue(t);
										break;
									}
								}
								System.out.println("Account not found.");
							}
							break;
						
						case 3:
							// Transfer money
							if (isBefore6pm && isNotWeekend) {
								person.transferMoney();
							} else {
								System.out.println("Enter the number of account to transfer money from: ");
								int account1_number = scan.nextInt();
								System.out.println("Enter the number of account to transfer money to: ");
								int account2_number = scan.nextInt();
								Account acc1, acc2;
								
								// Find the source and destination accounts for the transfer
								for (Node n = person.getAccounts().getHeader(); n.getNext() != null; n = n.getNext()) {
									if (n.getInfo().getAcc_number() == account1_number) {
										acc1 = n.getInfo();
										
										for (Node m = person.getAccounts().getHeader(); m.getNext() != null; m = m.getNext()) {
											if (m.getInfo().getAcc_number() == account1_number) {
												acc2 = m.getInfo();
												
												System.out.println("Enter transfer amount: ");
												int transfer_amount = scan.nextInt();
												
												// Create a transaction for the transfer and enqueue it
												Transaction t = new Transaction(person, acc1, acc2, "transferred", transfer_amount);
												transactions.enqueue(t);
												break;
											}
										}
									}
								}
								System.out.println("Account not found.");
							}
							break;
						
						case 4:
							// Display account info
							if (isBefore6pm && isNotWeekend) {
								System.out.println("Enter account number: ");
								int number = scan.nextInt();
								boolean flag = false;
								
								// Find the account with the given account number and display its balance
								for (Node n = person.getAccounts().getHeader(); n.getNext() != null; n = n.getNext()) {
									if (n.getInfo().getAcc_number() == number) {
										System.out.println("Balance: " + n.getInfo().getBalance());
										flag = true;
									}
								}
								
								if (!flag) {
									System.out.println("Account number invalid.");
								}
							} else {
								System.out.println("Cannot display info right now. Please try again later.");
							}
							break;
						
						case 5:
							// Exit the menu
							break;
						
						default:
							break;
					}
				} while (choice != 5);
				
				LocalDateTime current_time = LocalDateTime.now();
				int current_hour = currentTime.getHour();
				
				boolean isBefore11_59 = hour < 00;
				
				// Check if it's before 11:59 PM and display the menu again
				if (isBefore11_59) {
					menu();
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
	    // Create an instance of ATM
	    ATM atm = new ATM();
	    BufferedReader br;

	    try {
	        // Read user information from the file
	        atm.user_info = new FileInputStream("users.txt");
	        InputStreamReader ir = new InputStreamReader(atm.user_info);
	        br = new BufferedReader(ir);
	        String line = br.readLine();

	        // Process each line of user information
	        while (line != null) {
	            String[] current_user = line.split(",");
	            String name = current_user[0];
	            int client_number = Integer.parseInt(current_user[1]);
	            String password = current_user[2];
	            
	            // Create a new Person object with the extracted information
	            Person p = new Person(name, client_number, password);
	            
	            // Process the account details for the person
	            for (int i = 4; i < current_user.length; i += 3) {
	                int acc_nb = Integer.parseInt(current_user[i]);
	                int balance = Integer.parseInt(current_user[i + 1]);
	                String type = current_user[i + 2];
	                
	                // Create a new Account object and add it to the person's accounts
	                Account a = new Account(acc_nb, balance, type);
	                p.getAccounts().add(new Node(a));
	            }
	            
	            // Add the person to the ATM's users
	            atm.users.add(p);
	            
	            // Read the next line
	            line = br.readLine();
	        }
	    } catch (FileNotFoundException e) {
	        System.out.println("File not found.");
	    }

	    // Process transactions in the queue
	    while (!atm.transactions.empty()) {
	        Transaction t = atm.transactions.dequeue();
	        
	        // Update the account balances based on the transaction type
	        if (t.getType().equals("credited")) {
	            t.getAccount1().setBalance(t.getAccount1().getBalance() + t.getAmount());
	        } else if (t.getType().equals("debited")) {
	            t.getAccount1().setBalance(t.getAccount1().getBalance() - t.getAmount());
	        } else {
	            t.getAccount1().setBalance(t.getAccount1().getBalance() - t.getAmount());
	            t.getAccount2().setBalance(t.getAccount2().getBalance() + t.getAmount());
	        }
	    }

	    // Display the ATM menu and handle user interactions
	    atm.menu();

	    try {
	        // Write user information back to the file
	        FileWriter fileWriter = new FileWriter("users.txt");
	        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

	        // Iterate over the users and write their information
	        for (Object user : atm.users) {
	            if (user instanceof Person) {
	                Person person = (Person) user;
	                StringBuilder userData = new StringBuilder();
	                
	                // Append name, client_nb, and password
	                userData.append(person.getName()).append(",")
	                        .append(person.getClient_nb()).append(",")
	                        .append(person.getPassword()).append(",");
	                
	                LinkedList accounts = person.getAccounts();
	                Node node = accounts.getHeader();

	                // Iterate over the accounts and append their details
	                while (node.getNext() != null) {
	                    Account account = (Account) node.getNext().getInfo();
	                    
	                    // Append account details: acc_id, acc_balance, acc_type
	                    userData.append(account.getAcc_number()).append(",")
	                            .append(account.getBalance()).append(",")
	                            .append(account.getType()).append(",");

	                    node = node.getNext();
	                }

	                bufferedWriter.write(userData.toString());
	                bufferedWriter.newLine();
	            }
	        }

	        bufferedWriter.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
