/*
 * Kannika Bhatia ID 200332992 COMP1030
 * 
 * */


package Models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Product extends BaseModel {
	//private LocalDateTime _CreateDate;
	public Product()
	{
		setTaxable(true);
		//_CreateDate = LocalDateTime.now();
	}
	
	public Product(int id, String name)
	{
		this();
		setId(id);
		setName(name);
	}
	
	public Product(int id, String name, float price)
	{
		this();
		setId(id);
		setName(name);
		setPrice(price);
	}
	//private int _ProductId;
	
	private float _Price;
	
	private String _Name;

	private Boolean _Taxable;
	
	public void setPrice(float value)
	{
		_Price = value;
	}
	public float getPrice()
	{
		return(_Price);
	}
	public void setName(String value)
	{
		_Name = value;
	}
	public String getName()
	{
		return(_Name);
	}
	public void setTaxable(Boolean value)
	{
		_Taxable = value;
	}
	public Boolean getTaxable()
	{
		return(_Taxable);
	}
	
	//Format each line of data that will be written to file
	@Override
	public String getFileOutput(){
		String ret = "";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		//The the file output String 
		//<ProductId>,<ProductName>,<Price>,<Taxable>,<CreateDate>
		//1,2016-02-08
		ret = String.format("%d,%s,%.2f,%s,%s", 
				getId(), 
				getName(),
				getPrice(),
				String.valueOf(getTaxable()),
				getCreateDate().format(formatter));

		return (ret);
	}
	
	//set Product from data that has been read from file (id, name,price, taxable, CreateDate)
	@Override
	public void setFileOutput(String input){
		String fileline[];

		//Split the input line into an Array 
		//based on the , character
		fileline = input.split(",");

		//Take the first element and set the Product Id
		setId(Integer.parseInt(fileline[0]));
		
		//Take the second element and set the Product Name
		setName(fileline[1]);
		
		//Take the third element and set the Product Price
		setPrice(Float.parseFloat(fileline[2]));
		
		//Take the fourth element and set the Taxable
		setTaxable(Boolean.valueOf(fileline[3]));
		
		//Take the fourth element and set the CreateDate
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		setCreateDate(LocalDateTime.parse(fileline[4],formatter));
	}
	
	/*public void setId(int value)
	{
		super.setId(value);
	}
	public int getId()
	{
		return(super.getId());
	}
	
	public LocalDateTime getCreateDate(){
		return(super.getCreateDate());
	}
	public void setCreateDate(LocalDateTime value){
		super.setCreateDate(value);
	}*/
}
