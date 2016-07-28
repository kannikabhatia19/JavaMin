/*
 * Kannika Bhatia ID 200332992 COMP1030
 * 
 * */


package Models;
import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;


public class DataContext {
	
	public List<BaseModel> BaseModels = new ArrayList<BaseModel>();
	//List of Product Objects
	public List<Product> Products = new ArrayList<Product>();
	//List of InvoiceItem Objects
	public List<InvoiceItem> InvoiceItems = new ArrayList<InvoiceItem>();
	//List of Invoice Objects
	public List<Invoice> Invoices = new ArrayList<Invoice>();
	
	public void intialize(){
		File file = new File(String.format("%ss.csv", Product.class.getName()));
		if(!file.exists()){
			List<Product> Products = new ArrayList<Product>();
			Products.add(new Product(1,"Apple",1.15f));
			Products.add(new Product(2,"Broccoli",5.50f));
			Products.add(new Product(3,"Cucumber",1.00f));
			Products.add(new Product(4,"Banana",0.5f));
			Products.add(new Product(5,"Cherry",4.00f));
			Products.add(new Product(6,"Walnut",2.00f));
			Products.add(new Product(7,"Milk", 3.00f));
	
			Save(Products);
		}
	}
	
	//Return subtotal of all bill(invoice) in the invoice list
	public float invoiceSubtotal(){
		float subtotal = 0.00f;
		for(Invoice item: Invoices){
			subtotal += item.getSubTotal(item.getId());
		}
		return(subtotal);
	}
	
	//Return totaltaxes of all invoice in the invoice list
	public float invoiceTotalTaxes(){
		float totaltaxes = 0.00f;
		for(Invoice item: Invoices){
			totaltaxes += item.getTaxes(item.getId());
		}
		return(totaltaxes);
	}
	
	//Return total of all invoices
	public float invoiceTotal(){
		float totalofallinvoice = 0.00f;
		for(Invoice item: Invoices){
			totalofallinvoice += item.getTotal(item.getId());
		}
		return(totalofallinvoice);
	}
	
	public String toSummaryString(){
		String ret = "";
		
		ret = String.format("Products: %d, Invoices: %d, Items Sold: %d ", 
				Products.size(),
				Invoices.size(),
				InvoiceItems.size());
		
		return(ret);
	}
	
	
	
	public Boolean Read(Class<? extends BaseModel> c){
		
		try{
			//add s because all file are invoices, products, invoiceitems with s
			File file = new File(String.format("%ss.csv", c.getName()));
			if(!file.exists()){
				return false;
			}
			FileReader fr = new FileReader(file.getAbsoluteFile());
			BufferedReader br = new BufferedReader(fr);
			String line ="";

			do{
				line = br.readLine();
				if(line!=null && line.isEmpty()==false){
					try{
						//cast to basemodel
						BaseModel item = (BaseModel)c.newInstance();
						item.setFileOutput(line);
						if (c.getName().contains("Product")) {
							Products.add((Product)item);
						} else if (c.getName().contains("InvoiceItem")) {
							InvoiceItems.add((InvoiceItem)item);
						} else if (c.getName().contains("Invoice")) {
							Invoices.add((Invoice)item);
						} 
					}catch(InstantiationException e){
						e.printStackTrace();
						br.close();
						return false;
					}catch(IllegalAccessException e){
						e.printStackTrace();
						br.close();
						return false;
					}
				}
			}while(line!=null && line.isEmpty()==false);
			br.close();
			return true;
		}catch(IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	@SuppressWarnings("unchecked")
	public Boolean Save(List<? extends BaseModel>data){
		//Boolean ret = true;
		FileWriter fw = null;
		BufferedWriter bw = null;
		try{
			//Check if there's data in the List before start writing file
			if(data != null && data.size()>0)
			{
				Class<BaseModel> c = (Class<BaseModel>)data.get(0).getClass();
			
				try{
					File file = new File(String.format("%ss.csv", c.getName()));
					//check if file exist or not, if not, create new file
					if(!file.exists())
					{
						file.createNewFile();
					}
					
					fw = new FileWriter(file.getAbsoluteFile());
					bw = new BufferedWriter(fw);
					
					for(BaseModel item : data){
						bw.write(item.getFileOutput() +"\n");
					}
				}catch(IOException e){
					e.printStackTrace();
					bw.close();
					return false;
				}	
				bw.close();
			}
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	//This method is used to initialize test data
		/*public void Initialize(){
			
			//sample of products in store
			Products.add(new Product(1,"Apple",1.15f));
			Products.add(new Product(2,"Broccoli",5.50f));
			Products.add(new Product(3,"Cucumber",1.00f));
			Products.add(new Product(4,"Banana",0.5f));
			Products.add(new Product(5,"Cherry",4.00f));
			Products.add(new Product(6,"Walnut",2.00f));
			Products.add(new Product(7,"Milk", 3.00f));
			
			
			//for example there are 2 invoices
			Invoice invoice = new Invoice(1);
			Invoice invoice2 = new Invoice(2);
			
			//Create InvoiceItems id, invoiceid, productid
			InvoiceItem item1 = new InvoiceItem(1,1,2);// Create an invoiceitem with
			item1.setQuantity(10);//Set the quantity to 10 units
			InvoiceItems.add(item1);
			InvoiceItem item2 = new InvoiceItem(2,1,1);
			item2.setQuantity(10);
			InvoiceItems.add(item2);
			InvoiceItem item3 = new InvoiceItem(3,1,3);
			item3.setQuantity(10);
			InvoiceItems.add(item3);
			InvoiceItem item4 = new InvoiceItem(4,1,4);
			item4.setQuantity(10);
			InvoiceItems.add(item4);
			
			invoice.AddInvoiceItem(item1); //Add the Broccoli to the Invoice
			invoice.AddInvoiceItem(item2);
			invoice.AddInvoiceItem(item3);
			invoice.AddInvoiceItem(item4);
			
			Invoices.add(invoice);
			
			
			//InvoiceItem in invoice2
			InvoiceItem item5 = new InvoiceItem(5,2,5);// Create an invoiceitem with
			item5.setQuantity(2);//Set the quantity to 2 units
			InvoiceItems.add(item5);
			InvoiceItem item6 = new InvoiceItem(6,2,6);
			item6.setQuantity(2);
			InvoiceItems.add(item6);
			InvoiceItem item7 = new InvoiceItem(7,2,7);
			item7.setQuantity(2);
			InvoiceItems.add(item7);
			InvoiceItem item8 = new InvoiceItem(8,2,1);
			item8.setQuantity(2);
			InvoiceItems.add(item8);
			
			invoice2.AddInvoiceItem(item5); //Add the Broccoli to the Invoice
			invoice2.AddInvoiceItem(item6);
			invoice2.AddInvoiceItem(item7);
			invoice2.AddInvoiceItem(item8);
			
			Invoices.add(invoice2);
			
		}*/
	
		/*
		 * 
			//StringBuilder sb = new StringBuilder();
		 * 
		 * //Concatenate String by using StringBuilder object
					//method that is used to concatenate string is append
					//This is a better approach compare to + string
					//sb.append(line);
					//sb.append(System.lineSeparator());
					//read next line
					 * 
					 * 
			//for(int i=0; i < InvoiceItems.size();i++){					{
				if(InvoiceItems.get(i).getInvoiceId() == i+1 ){
					item.AddInvoiceItem(InvoiceItems.get(i));
				}
			}*/
	
	/*//Save data from the invoice list into CSV file
	public Boolean SaveInvoice(){
		Boolean ret = true;
		try{
			//Create a reference to a file
			File file = new File("invoices.csv");
			
			//Does the file exist
			if(!file.exists())
			{
				//Does not exist, create a new file
				file.createNewFile();
			}
			
			//Create a writer to send information to the stream
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			//BufferedWriter is a character streams class to handle the character data
			BufferedWriter bw = new BufferedWriter(fw);
			
			//Write in file
			for(Invoice item:Invoices){
				bw.write(item.getFileOutput() + "\n");
			}
			bw.close();
		}catch(IOException e){
			ret = false;
			e.printStackTrace();
		}
		return ret;
	}
	//Read Invoice data from invoices.csv file and initialize them to Invoices List in Program
	public Boolean ReadInvoice(){
		Boolean ret = true;
		BufferedReader br;
		Invoice item =null;
		try{
			
			File file = new File("invoices.csv");
			
			// FileReader reads text files in the default encoding. 
			// Use BufferedReader to read line by line or many line at a time instead of character by character
			br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			
			//read first line of text
			String line = br.readLine();
			if(line != null && line.isEmpty()==false){
				//Clear invoice list
				Invoices.clear();
				//Check that there's a string and string is not an empty string before start reading
				do{
					
					item = new Invoice();
					item.setFileOutput(line);
					Invoices.add(item);
					line = br.readLine();
					
				}while(line != null && line.isEmpty()==false);
			}
			br.close();
		}catch(IOException e){
			ret = false;
			e.printStackTrace();
		}
		return(ret);
	}
	//Save all the InvoiceItem in list to invoiceitems.csv file
	public Boolean SaveInvoiceItem(){
		Boolean ret = true;
		try{
			File file = new File("invoiceitems.csv");
			//If file doesn't exist, create new file
			if(!file.exists()){
				file.createNewFile();
			}
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for(InvoiceItem item : InvoiceItems){
				bw.write(item.getFileOutput()+"\n");
			}
			bw.close();
			fw.close();
		}
		catch(IOException e){
			e.printStackTrace();
			ret = false;
		}
		return ret;
	}
	
	public Boolean ReadInvoiceItem(){
		Boolean ret = true;
		BufferedReader br;
		InvoiceItem item = null;
		try{
			File file = new File("invoiceitems.csv");
			//FileReader reader = null;
			br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			//read first line
			String line = br.readLine();
			if(line != null && line.isEmpty()==false){
				InvoiceItems.clear();
				do{
					item = new InvoiceItem();
					item.setFileOutput(line);
					InvoiceItems.add(item);
					line = br.readLine();
				}while(line != null && line.isEmpty()==false);
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{

		}
		return(ret);
	}
	//Save product data from Product List to CSV file
	public Boolean SaveProduct(){
		Boolean ret = true;
		
		try{
			File file = new File("products.csv");
			if(!file.exists()){
				file.createNewFile();
			}
			
			//Create FileWriter 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			//Create BufferedWriter from FileWriter object
			BufferedWriter bw = new BufferedWriter(fw);
			
			//Retrieve all data from Products List and write to file
			for(Product item: Products){
				bw.write(item.getFileOutput()+"\n");
			}
			//close BufferedWriter and FileWriter after finish writing
			bw.close();
			fw.close();
		}
		catch(IOException e){
			e.printStackTrace();
			ret = false;
		}
		return(ret);
	}
	
	public Boolean ReadProduct(){
		Boolean ret = true;
		Product item = null;
		BufferedReader br;
		try{
			File file = new File("products.csv");
			br = new BufferedReader(new FileReader(file.getAbsoluteFile()));
			
			//Read the first line
			String line = br.readLine();
			if(line != null && line.isEmpty()==false)
			{
				//Clear Product List because the data will be read from file
				Products.clear();
				do{
					item = new Product();
					item.setFileOutput(line);
					Products.add(item);
					line = br.readLine();
				}while(line != null && line.isEmpty()==false);
			}
			br.close();
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			
		}
		return(ret);
	 * 
	 * //Boolean ret = true;
		/*Class<BaseModel> c;
		
		if(data!=null && data.size() > 0){
			c = (Class<BaseModel>) data.get(0).getClass();
		}
		else
		{
			return false;
		}
		data.clear();*/
	
}
