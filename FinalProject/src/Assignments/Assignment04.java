/*
 * Kannika Bhatia ID 200332992 COMP1030
 * 
 * */


package Assignments;

import Models.DataContext;
import Models.Invoice;
import Models.InvoiceItem;

//import Models.Invoice;
import java.util.Scanner;
import Models.Menu;
import Models.Product;

public class Assignment04 {
	public static DataContext context = new DataContext();
	
	public static void main(String[]args)
	{
		context.intialize();
		context.Read(Product.class);
		context.Read(InvoiceItem.class);
		context.Read(Invoice.class);
		
		Menu item = new Menu();
		int menuselect = 0;
		do{
			item.DisplayMenuPanel();
			Scanner userinput = new Scanner(System.in);
			menuselect = userinput.nextInt();
			
			switch(menuselect){
				case 1:
					item.DailySummary();
					break;
				case 2:
					item.InvoiceRecall();
					break;
				case 3:
					item.AddNewPurchase();
					break;
				case 0:
					break;
			}
		}while(menuselect!=0);
	
		//Save all data to files
		context.Save(context.Products);
		context.Save(context.InvoiceItems);
		context.Save(context.Invoices);
		
		
	}
	/*context.SaveInvoice();
	context.SaveInvoiceItem();
	context.SaveProduct();*/
	
	//context.Initialize();
			//Initialize Lists from File
			
			//Preparing data by reading from files
			/*context.ReadProduct();
			context.ReadInvoiceItem();
			context.ReadInvoice();*/
	//System.out.println(context.toSummaryString());
	
			/*for(Invoice item : context.Invoices){
				System.out.println(item.toDetailString(item.getId()));
			}*/
	
}
