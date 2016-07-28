package Models;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.*;

import Assignments.Assignment04;


public class Menu {
	
	//Displays a summary of the daily sales (for Today) - Requires LocalDateTime as a reference to today's date
	
	public void DisplayMenuPanel(){
		
		System.out.println("ABC Groceries Corperation");
		System.out.println("Please Select the Menu by Entering one digit number of each menu");
		System.out.println("1: Display Daily Sale's Summary");
		System.out.println("2: Invoice Recalls");
		System.out.println("3: Add New Purchase");
		System.out.println("0: Exit");
	}
	
	public void DailySummary(){
		String date = "";
		Scanner input;
		List<Invoice> tmpInvoices = new ArrayList<Invoice>();
		
		do{
			tmpInvoices.clear();
			System.out.println("Enter a date (MM/DD/YYYY) or Enter 0 to Exit");
			input = new Scanner(System.in);
			
			date = input.nextLine();
			if(date.equals("0"))
			{
				break;
			}
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
			LocalDate ldt = LocalDate.parse(date,formatter);
			
			
			
			for(BaseModel invoice : Assignment04.context.Invoices){
				if((invoice.getCreateDate().getYear() == ldt.getYear())&&
					(invoice.getCreateDate().getMonthValue() == ldt.getMonthValue())&&
						(invoice.getCreateDate().getDayOfYear() == ldt.getDayOfYear()))
				{
					
							tmpInvoices.add((Invoice)invoice);
				}
			}
			if(tmpInvoices.size()>0){
				for(Invoice item : tmpInvoices){
					System.out.println(item.toSummaryString(item.getId()));
				}
			}
		}while(!date.isEmpty());
	}
	//Invoice Recall - Asks for an invoice number then displays the Invoice Detail
	public void InvoiceRecall(){
		Scanner input = new Scanner(System.in);
		Boolean found;
		int id=0;
		do{
			found = false;
			System.out.println("Please Enter Invoice Id or Press 0 to return to Exit");
			
			id = Integer.parseInt(input.nextLine());
			if(id!=0){
				for(Invoice item: Assignment04.context.Invoices)
				{
					if(item.getId() == id){
						System.out.println(item.toDetailString(id));
						found = true;
						break;
					}
				}
				if(found == false){
					System.out.println("This Invoice Id is not found in the Database");
				}
			}
			if(found==true)
				break;
		}while(id != 0);
	}
	//Add new purchase
	//Enter a Product (choose # - #)
	//Enter Quantity
	//Complete - Print Receipt
	public void AddNewPurchase()
	{
		int productid = 0;
		int invoiceid = 0;
		int numberofitem = 0;
		int quantity = 0;
		int invoiceitemid =0;
		Scanner input = new Scanner(System.in);
		if(Assignment04.context.Invoices != null && Assignment04.context.Invoices.size()!=0){
			invoiceid = Assignment04.context.Invoices.size();
		}
		else{
			invoiceid = 1;
		}
		if(Assignment04.context.InvoiceItems != null && Assignment04.context.InvoiceItems.size()!=0){
			invoiceitemid = Assignment04.context.InvoiceItems.size();
		}
		else
		{
			invoiceitemid = 1;
		}
		do{
			System.out.println("Please Enter Product Id or Press 0 to Print Receipt");
			productid = Integer.parseInt(input.nextLine());
			if(productid != 0){
				do{
					System.out.println("Please Enter Quantity");
					quantity = Integer.parseInt(input.nextLine());
					if(quantity != 0){
						break;
					}
				}while(quantity==0);
				InvoiceItem item = new InvoiceItem(invoiceitemid+numberofitem,invoiceid,productid);
				item.setQuantity(quantity);
				Assignment04.context.InvoiceItems.add(item);
				numberofitem++;
			}
		}while(productid!=0);
		
		if(productid == 0 && numberofitem>0){
			//print receipt and back to main menu
			Invoice item = new Invoice(invoiceid);
			Assignment04.context.Invoices.add(item);
			System.out.println(item.toDetailString(invoiceid));
		}
		else{
			System.out.println("No item purchase comes no Receipt");
		}
	}
	
	/*if((invoice.getCreateDate().getYear() == ldt.getYear())||
	(invoice.getCreateDate().getMonth() == ldt.getMonth())||
	(invoice.getCreateDate().getDayOfYear() == ldt.getDayOfYear())
		){*/
}
