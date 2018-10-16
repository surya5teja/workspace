/**
 * 
 */
package com.test.custom;

import java.util.List;



/**
 * @author surteja
 *
 */
@PojoToResposeDTO(value="surya")
public class TdpResponseWsDTO {
	//private Object data;
	private List<TdpErrorWsDTO> errors;
	private Boolean success;
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public List<TdpErrorWsDTO> getErrors() {
		return errors;
	}
	public void setErrors(List<TdpErrorWsDTO> errors) {
		this.errors = errors;
	}
	public Boolean getSuccess() {
		return success;
	}
	public void setSuccess(Boolean success) {
		this.success = success;
	}
	
	
	
	
}
