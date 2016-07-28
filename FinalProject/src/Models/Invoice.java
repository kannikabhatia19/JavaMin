/*
 * Kannika Bhatia ID 200332992 COMP1030
 * 
 * */


package Models;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import Assignments.Assignment04;

public class Invoice extends BaseModel {
	
	//private int _InvoiceId;
	//private LocalDateTime _CreateDate;
	
	
	public Invoice(){
		//_CreateDate = LocalDateTime.now();
		super();
	}
	
	public Invoice(int value)
	{
		//this();
		//setId(id);
		this();
		super.setId(value);
	}
	

	//This method summarise all subtotal in one invoice(bill) by calling
	//the getSubTotal of each invoiceitem in invoice instead 
	public float getSubTotal(int id){
		float ret = 0.00f;
		
		for(InvoiceItem item : Assignment04.context.InvoiceItems){
			if(item.getInvoiceId()==id)
			{
				ret+=item.getSubTotal();
			}
		}
		return(ret);
	}
	//getTaxes of all the item in the invoice instead of repeat calculation of common.TaxRate*SubTotal
	//we can call item.getTaxes() which do this calculation already in invoiceitem class
	public float getTaxes(int id){
		float ret = 0.00f;

		for(InvoiceItem item : Assignment04.context.InvoiceItems){
			if(item.getInvoiceId()==id)
			{
				ret+=item.getTaxes();
			}
		}
		return(ret);
	}
	public float getTotal(int id){
		float ret = 0.00f;
		ret = getSubTotal(id) + getTaxes(id);
		return(ret);
	}
	
	public String getInvoiceDateString(){
		return(String.format("Current Date: %s", this.getCreateDate().toString()));
	}
	
	public String toSummaryString(int id){
		String ret = "";
		int count = 0;
		for(InvoiceItem item: Assignment04.context.InvoiceItems){
			if(item.getInvoiceId() == id){
				count++;
			}
		}
		ret = String.format(
				"Invoice %d\nSold on %s\nNumber of items: %d\n"
				+"Sub Total: %10.2f\nTaxes: %10.2f\nTotal: %10.2f",
				getId(),
				getInvoiceDateString(),
				count,
				getSubTotal(id),
				getTaxes(id),
				getTotal(id));
		return(ret);
	}
	
	public String toDetailString(int id){
		String ret = "";
		int i = 1;
		
		ret = toSummaryString(id);
		ret = String.format("%s\n\n",ret);
		
		for(InvoiceItem item: Assignment04.context.InvoiceItems){
			if(item.getInvoiceId()==id){
				ret+= String.format("Item %d -%s\n",i, item.toString());
				i++;
			}
		}
		return(ret);
	}
	@Override
	public String getFileOutput(){
		String ret = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		//The the file output String 
		//<InvoiceId>,<CreateDate>
		//1,2016-02-08
		ret = String.format("%d,%s", 
				getId(), 
				getCreateDate().format(formatter));
		
		return (ret);
	}
	
	@Override
	public void setFileOutput(String input){
		String fileline[];

		//Split the input line into an Array 
		//based on the , character
		fileline = input.split(",");
		int tempid = Integer.parseInt(fileline[0]);
		//Take the first element and set the Invoice Id
		setId(tempid);

		//Take the second element and set the CreateDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		setCreateDate(LocalDateTime.parse(fileline[1],formatter));			
	}

	//OLD VERSION
	/*public String toSummaryString(){
	
	String s = getId() + ",";
	s+= getSubTotal() + ",";
	s+= getTaxes() + ",";
	s+= getTotal();
	return(s);
	}*/
	
	/*public String toDetailString(){
		String s = " ";
		for(InvoiceItem item : InvoiceItems){
			s += item.toString() + "\n";
		}
		return(s);
		
	}*/
	
	/*public int getId(){
	return(_InvoiceId);
	}
	
	public void setId(int value){
		_InvoiceId = value;
	}
	
	public LocalDateTime getCreateDate(){
		return(_CreateDate);
	}
	public void setCreateDate(LocalDateTime value)
	{
		_CreateDate = value;
	}*/
	
	/*public void AddInvoiceItem(InvoiceItem item){
	InvoiceItems.add(item);
	}*/
}
