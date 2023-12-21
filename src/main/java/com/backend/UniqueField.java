package com.backend;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = UniqueConstraintValidator.class)
public @interface UniqueField {
    public String message() default "Invalid user";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};

    public String fieldName();

    Class<?> repository();
}
