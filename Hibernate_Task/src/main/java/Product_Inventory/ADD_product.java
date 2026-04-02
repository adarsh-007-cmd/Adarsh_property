package Product_Inventory;

import java.util.Scanner;

import javax.persistence.EntityManager;
//import javax.persistence.Entity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Product_Inventory.Product.Status;

public class ADD_product {
public static void main(String[] args) {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	Scanner scan = new Scanner(System.in);
	
	System.out.println("Enter the Product Name: ");
	String productname = scan.next();
	
	System.out.println("Enter the Category :");
	String category = scan.next();
	
	System.out.println("Enter the Price (.in Rupees/-) : ");
	double price = scan.nextDouble();
	
	System.out.println("Enter the Quantity :");
	int quantity = scan.nextInt();
	
	Product product = new Product();
	
	product.setProductname(productname);
	product.setCategory(category);
	product.setPrice(price);
	product.setQuantity(quantity);
	
	
	if(quantity == 0)
	{
		product.setStatus(Status.OUT_OF_STOCK);
	}
	else {
		product.setStatus(Status.AVAILABLE);
	}
	
	transaction.begin();
	manager.persist(product);
	transaction.commit();
	
	System.out.println("Hibernate Says : a new item has been added to the table ");
	scan.close();
}
}
