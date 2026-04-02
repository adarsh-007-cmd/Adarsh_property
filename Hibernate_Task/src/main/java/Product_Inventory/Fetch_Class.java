package Product_Inventory;


import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;



public class Fetch_Class {
	
	private int m;

	public void fetch_all()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
		EntityManager manager = factory.createEntityManager();
		
		Query q = manager.createQuery("select p from Product p");
		List<Product> productList = q.getResultList();
		
		if(productList.isEmpty())
		{
			System.out.println("No Product Data Found");
		}
		else {
			for(Product p:productList)
			{
				System.out.println("Product ID			: "+p.getProductid());
				System.out.println("Product Name			: "+p.getProductname());
				System.out.println("Product Category		: "+p.getCategory());
				System.out.println("Product Price        : "+p.getPrice());
				System.out.println("Product Quantity     : "+p.getQuantity());
			}
		}
	}
	public void fetch_product_with_category()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
		EntityManager manager = factory.createEntityManager();
		
		Scanner scan = new Scanner(System.in);
		 
		
		Query q = manager.createQuery("select p from Product p where p.category = :cat");
		
		
		System.out.println("Enter the category");
		String category = scan.next();
		
		q.setParameter("cat", category);
		
		List<Product> productList = q.getResultList();
		
		if(productList.isEmpty())
		{
			System.out.println("Hibernate says: Category Enter "+"'"+category+"'" +" doesn't exists ");
		}
		else {
			for(Product p : productList)
			{
				
				System.out.println("Product ID			: "+p.getProductid());
				System.out.println("Product Name			: "+p.getProductname());
				System.out.println("Product Category		: "+p.getCategory());
				System.out.println("Product Price        : "+p.getPrice());
				System.out.println("Product Quantity     : "+p.getQuantity());
			}
			System.out.println();
			System.out.println("Above are the details of all Products in the database.");
		}
		scan.close();
	}
	
	public void fetch_less_than_n()
	{
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
		EntityManager manager = factory.createEntityManager();
		
		Scanner scan = new Scanner(System.in);
		Query q = manager.createQuery("select p from Product p where p.quantity < :quant ");
		
		System.out.println("Enter the Quantity Criteria");
		int n = scan.nextInt();
		
		q.setParameter("quant", n);
		
		List<Product> productList = q.getResultList();
		
		
		
		if(productList.isEmpty())
		{
			System.out.println("Hibernate says: Product with Quantity "+n +"doesn't exists ");
		}
		
		else {
			
			for(Product p : productList)
			{
				
				System.out.println("Product ID			: "+p.getProductid());
				System.out.println("Product Name			: "+p.getProductname());
				System.out.println("Product Category		: "+p.getCategory());
				System.out.println("Product Price        : "+p.getPrice());
				System.out.println("Product Quantity     : "+p.getQuantity());
			}
			System.out.println();
			System.out.println("Above are the details of all Products in the database.");
		}
		scan.close();
		
	}
	public void increase_by_price_for_category()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		Scanner scan = new Scanner(System.in);
		
		
		Query q = manager.createQuery("update Product p set p.price = p.price + 50 where p.category = :cat");
	
		
		System.out.println("Enter the Category :");
		String category = scan.next(); 
		
		
		q.setParameter("cat", category);
		
		
		transaction.begin();
		int rows = q.executeUpdate();
		transaction.commit();
		
		
		if(rows == 0)
		{
			System.out.println("Hibernate says: Product with category "+category +"doesn't exists ");
		}
		else {
		
			System.out.println("hibernate says : The product under category"+category+" has been updated with increase price by 50 ");
		}
		scan.close();
		
	}
	public void Delete_zero_quantity_item()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the Category :");
		String category = scan.next(); 
		
		
		
		
		Query q = manager.createQuery("delete from Product p where p.quantity = 0");
		

		
		transaction.begin();
		int rows = q.executeUpdate();
		transaction.commit();
		
		
		if(rows == 0)
		{
			System.out.println("Hibernate says: Product with category "+category +"doesn't exists ");
		}
		else {
		
			System.out.println("hibernate says : The product under category"+category+" has been updated ");
		}
		scan.close();
	}
	public void count_and_display()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
		EntityManager manager = factory.createEntityManager();
		
		Query q = manager.createQuery("select count(p) from Product p");
		System.out.println("Total number of Products = "+q.getSingleResult());
	}
	public void fetch_by_name()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
		EntityManager manager = factory.createEntityManager();
		System.out.println("Enter the Product name :");
		Scanner scan = new Scanner(System.in);
		String name = scan.next(); 
		
		
		Query q = manager.createQuery("select p from Product p where p.productname = :name");
		
		q.setParameter("name", name);
		List<Product> productList = q.getResultList();
		
		if(productList.isEmpty())
		{
			System.out.println("The product Not found.Try again.");
		}
		else {
			for(Product p : productList)
			{
			System.out.println("The product is 		: "+p.getProductname());
			System.out.println("Product Category		: "+p.getCategory());
			System.out.println("Product Price        : "+p.getPrice());
			System.out.println("Product Quantity     : "+p.getQuantity());
		}
		}
		
	}
	public void markOutOfStockWhenQuantityZero() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
		EntityManager manager = factory.createEntityManager();
		EntityTransaction transaction = manager.getTransaction();
		
      
       Query q = manager.createQuery("update Product p set p.status = 'OUT_OF_STOCK' where p.quantity = 0");
       
       
       transaction.begin();
       q.executeUpdate();
       transaction.commit();
       System.out.println("Hibernate says : The table has been updated : Please check the database");
       
	
        
    }
	public void display_all_available()
	{
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev1");
		EntityManager manager = factory.createEntityManager();
		
		
		Query q = manager.createQuery("select p from Product p where p.status = 'AVAILABLE'");
		
		
		List<Product> productList = q.getResultList();
		
		if(productList.isEmpty())
		{
			System.out.println("The product Not found.Try again.");
		}
		else {
			for(Product p : productList)
			{
			System.out.println("The product is 		: "+p.getProductname());
			System.out.println("Product Category		: "+p.getCategory());
			System.out.println("Product Price        : "+p.getPrice());
			System.out.println("Product Quantity     : "+p.getQuantity());
			System.out.println("Product Status       : "+p.getStatus());
		}
		}
		
	}
		
public static void main(String[] args) {
	
	Fetch_Class fetch = new Fetch_Class();
	//fetch.fetch_all(); // ----> Fetch all products
	//fetch.fetch_product_with_category(); // ----> Fetch products belonging to a specific category(dynamic)
	//fetch.fetch_less_than_n(); // ----> Fetch products with quantity less than n.(dynamic)
	//fetch.increase_by_price_for_category(); // ----> Increase price by 50 for a category(dynamic)
	//fetch.Delete_zero_quantity_item(); // ----> Delete products with zero quantity
	//fetch.count_and_display(); // ---> Count total products and display.
	//fetch.fetch_by_name(); // ----> Fetch a specific product based on product name(dynamic)
	//fetch.markOutOfStockWhenQuantityZero(); // ----> Mark(update) Product status as OUT_OF_STOCK When quantity becomes 0
	//fetch.display_all_available(); // ----> Display all the products whose status is available
	
}
}
