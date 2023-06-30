package com.demo.dto;

public class ErrorDto implements MyDto{
	
	private String errorMsg;
	
	public ErrorDto(String errorMsg) {
        super();
        this.errorMsg = errorMsg;
	} 

    public String getErrorMsg() {
        return errorMsg;
    }

 

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}