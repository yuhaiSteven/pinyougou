package entity;

import java.io.Serializable;

public class Result implements Serializable{
	
	
	private Boolean flag;//执行成功/错误标识
	
	private String message;

	public Boolean getFlag() {
		return flag;
	}

	public void setFlag(Boolean flag) {
		this.flag = flag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Result() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Result(Boolean flag, String message) {
		super();
		this.flag = flag;
		this.message = message;
	}
	
	
	
	

}
