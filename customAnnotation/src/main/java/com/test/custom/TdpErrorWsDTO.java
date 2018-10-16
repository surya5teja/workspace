/* THIS PROGRAM IS CONFIDENTIAL AND PROPRIETARY TO AMTRAK
 * AND MAY NOT BE REPRODUCED, PUBLISHED OR DISCLOSED TO OTHERS WITHOUT AUTHORIZATION.
 * COPYRIGHT © AMTRAK. THIS WORK IS UNPUBLISHED.
 */
package com.test.custom;

import java.io.Serializable;


public class TdpErrorWsDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String code;

	private String cause;

	private String message;

	private String type;
	
	public TdpErrorWsDTO(String code, String cause, String message, String type) {
		this.code = code;
		this.cause = cause;
		this.message = message;
		this.type = type;
	}

	public TdpErrorWsDTO() {
		// empty 
	}

	public void setCode(final String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCause(final String cause) {
		this.cause = cause;
	}

	public String getCause() {
		return cause;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setType(final String type) {
		this.type = type;
	}

	public String getType() {
		return type;
	}

}