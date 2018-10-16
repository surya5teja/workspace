package com.test.custom;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;




@Documented
@Constraint(validatedBy = PojoToResponseDTOConvertor.class)
@Target( {ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PojoToResposeDTO {
	 Class<? extends Payload>[] payload() default {};
	 String value() default "";
}