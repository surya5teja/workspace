package com.test.custom;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;



public class PojoToResponseDTOConvertor implements
ConstraintValidator<PojoToResposeDTO, Object>{

	TdpResponseWsDTO rs;

	
	public void initialize(PojoToResposeDTO arg0) {
		// TODO Auto-generated method stub
		System.out.println("surya"+arg0.value());
	}

	
	public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
		// TODO Auto-generated method stub
		rs = new TdpResponseWsDTO();
		rs.setData(arg0);
		rs.setSuccess(Boolean.TRUE);
		return false;
	}
	

}
