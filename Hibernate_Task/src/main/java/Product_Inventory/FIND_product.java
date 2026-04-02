package Product_Inventory;

import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class FIND_product {
public static void main(String[] args) {
	EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
	EntityManager manager = factory.createEntityManager();
	EntityTransaction transaction = manager.getTransaction();
	
	Scanner scan = new Scanner(System.in);
	System.out.println("Enter the ID to search the item :");
	int id = scan.nextInt();
	
	
	Product product = manager.find(Product.class, id);
	if(id !=0)
	{
		System.out.println("Found the item");
		System.out.println();
		System.out.println("Hibernate says : The item details are given below :");
		System.out.println("Product Name : "+ product.getProductname());
		System.out.println("Product Category : "+ product.getCategory());
		System.out.println("Product Price : "+ product.getPrice());
		System.out.println("Product Quantity : "+ product.getQuantity());
		System.out.println("Product Staus : "+product.getStatus());
	}
	else {
		System.out.println("Hibernate says : Enter ID does not match with the Primary Key");
	}
	scan.close();
	
}
}
