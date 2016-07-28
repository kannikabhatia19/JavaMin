package Models;
import java.time.LocalDateTime;

public abstract class BaseModel {
	private int _Id;
	private LocalDateTime _CreateDate;
	private LocalDateTime _EditDate;
	
	public BaseModel(){
		_CreateDate = LocalDateTime.now();
		_EditDate = LocalDateTime.now();
	}
	public BaseModel(int id){
		this();
		setId(id);
	}
	
	public int getId() {
		return _Id;
	}
	public void setId(int value) {
		_Id = value;
	}
	public LocalDateTime getCreateDate() {
		return _CreateDate;
	}
	public void setCreateDate(LocalDateTime value) {
		_CreateDate = value;
	}
	public LocalDateTime getEditDate() {
		return _EditDate;
	}
	public void setEditDate(LocalDateTime value) {
		_EditDate = value;
	}
	abstract public String getFileOutput();
	abstract public void setFileOutput(String line);

}
