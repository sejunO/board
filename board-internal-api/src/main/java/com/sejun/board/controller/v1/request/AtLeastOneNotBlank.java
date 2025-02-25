package com.sejun.board.controller.v1.request;

import jakarta.validation.Constraint;

import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AtLeastOneNotBlankValidator.class)
@Documented
public @interface AtLeastOneNotBlank {
}
