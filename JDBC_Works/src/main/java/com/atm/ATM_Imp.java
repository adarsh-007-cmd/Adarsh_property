package com.atm;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;


public class ATM_Imp implements Iatm {
	
	public Connection create_connection() {
		Connection connection = null;
		try {
			
			Class.forName("org.postgresql.Driver");
			 connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jdbc_atm", "postgres", "12345");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("Connection Created");
		return connection;
	}

	@Override
	public void createAccount() {
		
		Connection connection = create_connection();
		try {
			Scanner scanner = new Scanner(System.in);
			PreparedStatement preparedStatement = connection.prepareStatement("insert into atm(account_number,name,email,phone,branch,balance,pin) values(?,?,?,?,?,?,?)");
			
			System.out.println("Enter Account Number");
			int account_number = scanner.nextInt();
			
			System.out.println("Enter name");
			String name = scanner.next();
			
			System.out.println("Enter email");
			String email = scanner.next();
			
			System.out.println("Enter Phone Number");
			int phone = scanner.nextInt();
			
			System.out.println("Enter branch name ");
			String branch = scanner.next();
			
			System.out.println("Enter Pin");
			int pin = scanner.nextInt();
			
			preparedStatement.setInt(1, account_number);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, email);
			preparedStatement.setInt(4, phone);
			preparedStatement.setString(5, branch);
			preparedStatement.setInt(6, 0);
			preparedStatement.setInt(7, pin);
			
			int rows = preparedStatement.executeUpdate();
			
			if(rows>0) {
				System.out.println("Account Created Successfully");
			}
			else {
				System.out.println("Account Creation Failed");
			}
			connection.close();
			scanner.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void deposit() {

		 Connection connection = create_connection();
		 try {
			 Scanner scanner = new Scanner(System.in);
			 System.out.println("Enter Account Number");
			 int account_number = scanner.nextInt();
			 
			 System.out.println("Enter Amount to Deposit");
			 int amount = scanner.nextInt();
			 
			 PreparedStatement preparedStatement = connection.prepareStatement("update atm set balance=balance+? where account_number=? and pin=?");
			 preparedStatement.setInt(1, amount);
			 preparedStatement.setInt(2, account_number);
			 System.out.println("Enter Pin");
			 int pin = scanner.nextInt();
			 preparedStatement.setInt(3, pin);
			 
			 
			 int rows = preparedStatement.executeUpdate();
			 
			 if(rows>0) {
				 System.out.println("Amount Deposited Successfully");
				 System.out.println("Updated Balance: please check your balance");
			 }
			 else {
				 System.out.println("Deposit Failed");
			 }
			 connection.close();
			 scanner.close();
		 } catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void withdraw() {
		 Connection connection = create_connection();
		 try {
			 
			 Scanner scanner = new Scanner(System.in);
			 	PreparedStatement fetchbalance = connection.prepareStatement("select balance from atm where account_number=? and pin=? "); 
			 	
			 	System.out.println("Enter the account number :");
			 	int account_number = scanner.nextInt();
			 	
			 	System.out.println("Enter the pin :");
			 	int pin = scanner.nextInt();
			 	
			 	System.out.println("Enter the amount to be withdrawn : ");
			 	double amount = scanner.nextDouble();
			 	
			 	fetchbalance.setInt(1, account_number);
			 	fetchbalance.setInt(2, pin);
			 	
			 	ResultSet resultSet = fetchbalance.executeQuery();
			 	if(resultSet.next())
			 	{
			 		double current_balance = resultSet.getDouble("balance");
			 		if(current_balance>0)
			 		{
			 			PreparedStatement withdraw = connection.prepareStatement("update atm set balance = balance-? where account_number=? and pin=?");
			 			withdraw.setDouble(1, amount);
			 			withdraw.setInt(2, account_number);
			 			withdraw.setInt(3, pin);
			 			
			 			withdraw.executeUpdate();
			 			System.out.println("Withdraw Successful :");
			 			System.out.println("Available balance : "+(current_balance-amount));
			 		}
			 		else {
						System.out.println("Insufficient Balance :");
					}
			 	}
			 	scanner.close();
		 } catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	@Override
	public void checkanddisplaybalance()
	{
		Connection connection = create_connection();
		try {
			
			Scanner scanner = new Scanner(System.in);
			PreparedStatement checkbalance = connection.prepareStatement("select balance from atm where account_number=? and pin=?");
			
			System.out.println("Enter the Account Number : ");
			int account_number = scanner.nextInt();
			
			System.out.println("Enter the pin :");
			int pin = scanner.nextInt();
			
			checkbalance.setInt(1, account_number);
			checkbalance.setInt(2, pin);
			
			ResultSet resultSet = checkbalance.executeQuery();
			if(resultSet.next())
			{
				double current_balance = resultSet.getDouble("balance");
				System.out.println("Your Current Balance is : "+current_balance);
			}
			else {
				System.out.println("Invalid Account number or pin");
			}
			scanner.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updation() {
		Connection connection = create_connection();
		Scanner scanner = new Scanner(System.in);
		System.out.println("1.Name\n2.Email\n3.Phone_Number\n4.Branch\5.Pin");
		System.out.println("Choose an option :");
		int choice = scanner.nextInt();
		
		System.out.println("Enter the Account Number :");
		int account_number = scanner.nextInt();
		
		try {
			switch (choice) {
			case 1:
				//update Name
				PreparedStatement updatename = connection.prepareStatement("Update set name=? where account_number=?");
				System.out.println("Enter the new name :");
				String newname = scanner.next();
				
				updatename.setString(1, newname);
				updatename.setInt(2, account_number);
				
				int row1 = updatename.executeUpdate();
				
				if(row1!=0)
				{
					System.out.println("The Name has been updated :");
				}
				else {
					System.out.println("Invalid Account Number : Please Try Again");
				}
				break;
			case 2:
				//update email
				PreparedStatement updateemail = connection.prepareStatement("Update set email=? where account_number=?");
				System.out.println("Enter the new email :");
				String email = scanner.next();
				
				updateemail.setString(1, email);
				updateemail.setInt(2, account_number);
				
				int row2 = updateemail.executeUpdate();
				if(row2!=0)
				{
					System.out.println("The email has been updated :");
				}
				else {
					System.out.println("Invalid Account Number : Please Try Again");
				}
				break;
			case 3:
				//Phone Number
				PreparedStatement updatephone = connection.prepareStatement("Update set phone=? where account_number=?");
				System.out.println("Enter the new Phone Number :");
				int phone = scanner.nextInt();
				
				updatephone.setInt(1, phone);
				updatephone.setInt(2, account_number);
				
				int row3 = updatephone.executeUpdate();
				if(row3!=0)
				{
					System.out.println("The Phone Number has been updated :");
				}
				else {
					System.out.println("Invalid Account Number : Please Try Again");
				}
				break;
			case 4:
				//Branch
				PreparedStatement updatebranch = connection.prepareStatement("Update set branch=? where account_number=?");
				System.out.println("Enter the new branch :");
				String branch = scanner.next();
				
				updatebranch.setString(1, branch);
				updatebranch.setInt(2, account_number);
				
				int row4 = updatebranch.executeUpdate();
				if(row4!=0)
				{
					System.out.println("The Branch has been updated :");
				}
				else {
					System.out.println("Invalid Account Number : Please Try Again");
				}
				break;
			case 5:
				PreparedStatement updatepin = connection.prepareStatement("Update set pin=? where account_number=?");
				System.out.println("Enter the new name :");
				int pin = scanner.nextInt();
				
				updatepin.setInt(1, pin);
				updatepin.setInt(2, account_number);
				
				int row5 = updatepin.executeUpdate();
				if(row5!=0)
				{
					System.out.println("The Pin has been updated :");
				}
				else {
					System.out.println("Invalid Account Number : Please Try Again");
				}
				break;
			default:
				try {
					throw new CustomException("Please Choose From The Given Option");
				} catch (Exception e) {
					e.printStackTrace();
				}
				connection.close();
				break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		scanner.close();
	}
	
	@Override
	public void deleteaccount()
	{
		Connection connection = create_connection();
		Scanner scanner = new Scanner(System.in);
		try {
			
		PreparedStatement deleteaccount = connection.prepareStatement("delete from atm where account_number=?");
		System.out.println("Enter the account number you want to delete :");
		int account_number = scanner.nextInt();
		
		deleteaccount.setInt(1, account_number);
		
		int rows = deleteaccount.executeUpdate();
		if(rows!=0)
		{
			System.out.println("The Account with Account Number"+account_number +" has been deleted : Please check the database");
		}
		else {
			System.out.println("Invalid Account Number : Please Try Again");
		}
		scanner.close();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void displaydetails() {
		Connection connection = create_connection();
		try {
			Scanner scanner = new Scanner(System.in);
			PreparedStatement displaydetail = connection.prepareStatement("select *from atm where account_number=?");
			System.out.println("Enter the account number you want the details of:");
			int account_number = scanner.nextInt();
			displaydetail.setInt(1, account_number);
			
			ResultSet resultSet = displaydetail.executeQuery();
			while(resultSet.next())
			{
				
				 	System.out.println("The details based on account number are given below:");
		            System.out.println();

		            int account_number1 = resultSet.getInt("account_number");
		            String name = resultSet.getString("name");
		            String email = resultSet.getString("email");
		            int phone = resultSet.getInt("phone");
		            double balance = resultSet.getDouble("balance");
		            int pin = resultSet.getInt("pin");

		            System.out.println("Account Number: " + account_number1);
		            System.out.println("Name: " + name);
		            System.out.println("Email ID: " + email);
		            System.out.println("Balance: " + balance);
		            System.out.println("Mobile Number: " + phone);
		            System.out.println("Pin : "+pin);
		            System.out.println();

		        } 
			

			
			
			scanner.close();
		} catch (Exception e) {
			
		}
		
	}
	

}
