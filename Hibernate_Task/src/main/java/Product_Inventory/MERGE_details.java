package Product_Inventory;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Product_Inventory.Product.Status;

public class MERGE_details {
public static void main(String[] args) {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	

	
	Scanner scan = new Scanner(System.in);
	System.out.println("Enter the ID to search the item :");
	int id = scan.nextInt();
	
	 Product product = manager.find(Product.class, id);
     System.out.println("Found: " + product.getProductname());
	
	
	System.out.println("Enter the updated value for Price :");
	Double price = scan.nextDouble();
	
	System.out.println("Enter the updated value for Quantity :");
	int Quantity = scan.nextInt();
	
	if(product!=null)
	{
		product.setPrice(price);
		product.setQuantity(Quantity);
		if(Quantity == 0)
		{
			product.setStatus(Status.OUT_OF_STOCK);
		}
		else {
			product.setStatus(Status.AVAILABLE);
		}
		manager.merge(product);
	}
	
	transaction.begin();
	
	transaction.commit();
	
	System.out.println("Hibernate says : The table has been updated in the fields : Please check the details Again");
	scan.close();
	
}
}
