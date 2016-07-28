/*
 * Kannika Bhatia ID 200332992 COMP1030
 * 
 * */



package Models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import Assignments.Assignment04;

public class InvoiceItem extends BaseModel {
	//private int _InvoiceItemId;
	
	private int _InvoiceId;
	
	private Invoice _Invoice;
	
	private int _ProductId;
	//readonly Product
	private Product _Product;
	
	private int _Quantity;
	
	//private LocalDateTime _CreateDate;
	
	public InvoiceItem(){
		//_CreateDate = LocalDateTime.now();
	}
	
	public InvoiceItem(int id, int invoiceid, int productid)
	{
		//this();
		//_CreateDate = LocalDate.now();
		setId(id);
		setProductId(productid);
		//setInvoiceId(invoiceid);
		setInvoiceId(invoiceid);
	}
	
	public Invoice getInvoice()
	{
		return(_Invoice);
	}

	private Invoice setInvoice(int value)
	{
		if(value >0)
		{
			for(Invoice p: Assignment04.context.Invoices)
			{
				if(p.getId() == value)
				{
					return p;
				}
			}
		}
		return(null);
	}
	
	private Product getProduct()
	{
		return(_Product);
	}
	
	private Product setProduct(int value)
	{
		if(value>0)
		{
			for(Product p : Assignment04.context.Products)
			{
				if(p.getId() == value)
					return p;
			}
		}
		return(null);
	}
	
	
	
	public void setInvoiceId(int value){
		
		_InvoiceId=value;
		_Invoice = setInvoice(_InvoiceId);
	}
	public int getInvoiceId(){
		return(_InvoiceId);
	}
	//set Product when we have a valid Id
	public void setProductId(int value)
	{
		_ProductId = value;
		_Product = setProduct(_ProductId);
	}
	
	public int getProductId()
	{
		return(_ProductId);
	}
	
	public void setQuantity(int value)
	{
		_Quantity = value;
	}
	
	public int getQuantity()
	{
		return(_Quantity);
	}
	public float getSubTotal(){
		return(getQuantity() * getProduct().getPrice());
	}
	public float getTaxes(){
		float subtotal = getSubTotal();
		if(subtotal> 0){
			return (subtotal * Common.TaxRate);
		}
		return 0;
	}
	public float getTotal(){
		return (getSubTotal() + getTaxes());
	}
	
	
	public String toString()
	{
		String ret = "";
		
		ret = String.format("%d Product: %d - %s, Quantity: %d, Subtotal: %.2f",
				getId(),
				getProductId(),
				//getProduct so we will have Product object that we can call getProduct.getName()
				getProduct().getName(),
				getQuantity(), 
				getSubTotal() 
				);
		return(ret);
	}
	//InvoiceItemId, InvoiceId, ProductId, Quantity, CreateDate
	@Override
	public String getFileOutput(){
		String ret = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		//The the file output String 
		//<InvoiceItemId>,<InvoiceId>,<ProductId>,<Quantity>,<CreateDate>
		//1,2016-02-08
		ret = String.format("%d,%d,%d,%d,%s", 
				getId(), 
				getInvoiceId(),
				getProductId(),
				getQuantity(),
				getCreateDate().format(formatter));

		return (ret);
	}
	//InvoiceItemId, InvoiceId, ProductId, Quantity, CreateDate
	@Override
	public void setFileOutput(String input){
		String fileline[];

		//Split the input line into an Array 
		//based on the , character
		fileline = input.split(",");

		//Take the first element and set the InvoiceItemId
		setId(Integer.parseInt(fileline[0]));
		
		//Take the second element and set the InvoiceId
		setInvoiceId(Integer.parseInt(fileline[1]));
		
		//Take the third element and set ProductId
		setProductId(Integer.parseInt(fileline[2]));
		
		//Take the fourth element and set Quantity
		setQuantity(Integer.parseInt(fileline[3]));
	
		//Take the fifth element and set the CreateDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		//LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
		setCreateDate(LocalDateTime.parse(fileline[4],formatter));
	}
	
	/*public void setId(int value)
	{
		_InvoiceItemId = value;
	}
	
	public int getId()
	{
		return(_InvoiceItemId);
	}*/
	
	
	/*public void setCreateDate(LocalDateTime value){
	_CreateDate = value;
	}
	public LocalDateTime getCreateDate(){
		return(_CreateDate);
	}*/
	
	
	
	//OLD VERSION
	/*public String toString()
	{
		String s = _Product.getName() + ",";
		s += getQuantity() + ",";
		s += getSubTotal() + ",";
		s += getTaxes() + ",";
		s += getTotal();
		return(s);
		//return(_Product.getName(),_Quantity,getSubTotal(),getTaxes(),getTotal());
	}*/
}
