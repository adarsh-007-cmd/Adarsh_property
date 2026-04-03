package com.atm;

import java.util.Scanner;

public class Main {
public static void main(String[] args) {
	try {
		
	
	Scanner scanner = new Scanner(System.in);
	
	ATM_Imp atm = new ATM_Imp();
	
	while(true) {
		System.out.println(" WELCOME TO YOUR NEAREST ATM");
		System.out.println("1. CREATE ACCOUNT");
		System.out.println("2. DEPOSIT");
		System.out.println("3. WITHDRAW");
		System.out.println("4. CHECK BALANCE");
		System.out.println("5. UPDATION");
		System.out.println("6. DISPLAY ALL THE DETAILS");
		System.out.println("7. DELETE ACCOUNT");
		System.out.println("8. EXIT");
		
		System.out.println("Enter your choice");
		
		int choice = scanner.nextInt();
		
		switch(choice) {
		case 1:
		{
			atm.createAccount();
			break;
		}
			
		case 2:
		{
			atm.deposit();
			break;
	
		}
		case 3:
		{
			atm.withdraw();
			break;
		}
		case 4:
		{
			atm.checkanddisplaybalance();
			break;
		}
		case 5:
		{
			atm.updation();
		}
		case 6:
		{
			atm.displaydetails();
			break;
		}
		case 7:
			atm.deleteaccount();
			break;
		//
		case 8:
		{
			System.out.println("Thank you ! Visit Again");
			System.exit(0);
			break;
		}
		default :
			try {
				throw new CustomException("Please Choose From The Given Option");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		
	}
	// atm.createAccount();
	}
	} catch (Exception e) {
		
	}
}
}

