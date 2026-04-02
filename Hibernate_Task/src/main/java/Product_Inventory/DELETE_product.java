package Product_Inventory;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class DELETE_product {
public static void main(String[] args) {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	Scanner scan = new Scanner(System.in);
	System.out.println("Enter the ID to search the item :");
	int id = scan.nextInt();
	
	Product product = manager.find(Product.class, id);
	if(id != 0)
	{
		System.out.println("Found: " + product.getProductname());
		transaction.begin();
		manager.remove(product);
		transaction.commit();
		System.out.println("Hibernate says : The product details has been removed from the database. Please check the database again.");
	}
	else {
		System.out.println("Cannot find the product. Please try again");
	}
    
    scan.close();
    
}
}
